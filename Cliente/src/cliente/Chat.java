package cliente;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import static javax.swing.JOptionPane.*;

public class Chat extends javax.swing.JFrame {

    private String nome;
    private Socket socket;
    private BufferedReader bufferedreader;
    private InputStreamReader inputstreamreader;
    private boolean rodando;

// construtor    
    public Chat(String nome) {

        initComponents();

        rodando = true;
        this.nome = nome;

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

    private Chat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        msgRecebida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        msgEnviada = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

        msgRecebida.setEditable(false);
        msgRecebida.setColumns(20);
        msgRecebida.setRows(5);
        jScrollPane1.setViewportView(msgRecebida);

        msgEnviada.setColumns(20);
        msgEnviada.setRows(5);
        msgEnviada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                msgEnviadaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(msgEnviada);

        jButton1.setText("Enviar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String mensagem = nome + " disse: ";

        try {
            PrintStream ps = new PrintStream(socket.getOutputStream());
            mensagem += msgEnviada.getText();

            ps.println(mensagem);
            ps.flush();

            msgEnviada.setText("");

        } catch (IOException e) {
            showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void msgEnviadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_msgEnviadaKeyPressed

        msgEnviada(evt);
    }//GEN-LAST:event_msgEnviadaKeyPressed

    public void msgEnviada(KeyEvent evt) throws HeadlessException {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            String mensagem = nome + " disse: ";
            
            try {
                PrintStream ps = new PrintStream(socket.getOutputStream());
                mensagem += msgEnviada.getText();
                
                ps.println(mensagem);
                ps.flush();
                
                msgEnviada.setText("");
                
            } catch (IOException e) {
                showMessageDialog(null, "Não conseguiu enviar a mensagem", "", ERROR_MESSAGE);
            }
            
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        botaoSair();


    }//GEN-LAST:event_jButton2ActionPerformed

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
                new Chat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea msgEnviada;
    private javax.swing.JTextArea msgRecebida;
    // End of variables declaration//GEN-END:variables
}
