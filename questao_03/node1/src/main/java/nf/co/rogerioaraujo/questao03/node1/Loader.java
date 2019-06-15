package nf.co.rogerioaraujo.questao03.node1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Loader {

    public static void main(String[] args) throws IOException {
        Socket cliente = new Socket("localhost", 3323);//3323
        Map<String, Integer> dados = new HashMap<>();

        dados.put("x", 2);
        dados.put("y", 5);
        dados.put("node", 1);

        ObjectOutputStream outputStream = new ObjectOutputStream(cliente.getOutputStream());
        outputStream.writeObject(dados);

        ObjectInputStream inputStream = new ObjectInputStream(cliente.getInputStream());
        int resultado = inputStream.readInt();

        System.out.println("Resultado: " + resultado);
    }
}
