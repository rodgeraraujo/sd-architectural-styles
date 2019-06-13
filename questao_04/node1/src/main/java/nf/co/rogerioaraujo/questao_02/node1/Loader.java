package nf.co.rogerioaraujo.questao_02.node1;

import nf.co.rogerioaraujo.questao_02.node1.domain.Usuario;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Loader {
    public static void main(String[] args) throws IOException {

        Usuario user = new Usuario();
        user.setNome("Fulano");

        long tempoInicial = System.currentTimeMillis();

        for (int i = 0; i < 1000; i++) {
            user.setCodigo(i);
            Socket socket = new Socket("localhost", 9898);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.writeObject(user);
            socket.close();
        }

        long tempoFinal = System.currentTimeMillis();
        long segundos = (tempoInicial - tempoFinal) / 1000;
        System.out.println("Duraçao total foi de " + segundos + "s");

    }
}
