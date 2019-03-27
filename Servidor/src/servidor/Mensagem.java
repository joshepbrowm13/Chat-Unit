package servidor;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.ArrayList;

public class Mensagem {

    private final Socket s;
    private final ArrayList<PrintStream> clientes;

//CONSTRUTOR 
    public Mensagem(Socket s, ArrayList<PrintStream> clientes) {

        this.s = s;
        this.clientes = clientes;

        Thread();
    }

    private void Thread() {

        Thread t;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                String mensagem = "";
                
                try {
                    InputStreamReader isr = new InputStreamReader(s.getInputStream());
                    BufferedReader br = new BufferedReader(isr);
                    
                    while ((mensagem = br.readLine()) != null) {
                        enviarMensagem(mensagem);
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t.start();

    }

    private void enviarMensagem(String mensagem) {

        for (int a = 0; a < clientes.size(); a++) {

            clientes.get(a).println(mensagem);
            clientes.get(a).flush();

        }

    }

}
