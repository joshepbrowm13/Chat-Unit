package cliente;

import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;

public class Cliente {

    public static void main(String[] args) {

        String nome = JOptionPane.showInputDialog(null, "Digite seu nome: ", "", PLAIN_MESSAGE);

        Chat chat = new Chat(nome);
        chat.setVisible(true);

    }

}
