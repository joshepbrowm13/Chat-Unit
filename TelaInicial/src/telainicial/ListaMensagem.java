package telainicial;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import static javax.swing.JOptionPane.*;

public class ListaMensagem extends javax.swing.JFrame {

    private Socket socket;
    private BufferedReader bufferedreader;
    private InputStreamReader inputstreamreader;
    private boolean rodando;

// construtor    
    public ListaMensagem() {

        initComponents();

        rodando = true;
     

        try {
            socket = new Socket("127.0.0.1", 7000);

        } catch (IOException e) {

            showMessageDialog(null, "Não se conectou ao servidor");
            System.exit(0);
        }

        Thread();

    }

    private void Thread() {

        Thread t = new Thread(new Runnable() {

            String mensagem;

            @Override
            public void run() {

                try {
                    inputstreamreader = new InputStreamReader(socket.getInputStream());
                    bufferedreader = new BufferedReader(inputstreamreader);

                    while ((mensagem = bufferedreader.readLine()) != null) {
                        msgRecebida.setText(msgRecebida.getText() + mensagem + "\n");

                        if (!rodando) {
                            break;
                        }
                    }

                } catch (IOException e) {
                    showMessageDialog(null, "Erro de conexão com o servidor", "", ERROR_MESSAGE);
                }

            }
        });
        t.start();
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msgRecebida = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        msgRecebida.setEditable(false);
        msgRecebida.setColumns(20);
        msgRecebida.setRows(5);
        jScrollPane1.setViewportView(msgRecebida);

        jButton2.setText("Sair");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(355, 355, 355)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>                        
        
    
        
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         

        botaoSair();


    }                                        

    private void botaoSair() {
        try {
            
            socket.close();
            
            System.exit(0);
            
        } catch (IOException e) {
            
            e.printStackTrace();
            
        }
    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ListaMensagem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea msgRecebida;
    // End of variables declaration                   
}
