package rf.co.rogerioaraujo.questao02.cliente;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Cliente {

    public static void main(String[] args) {

        Map<String, Integer> data = new HashMap<>();

        data.put("x", 7);
        data.put("y", 13);
        data.put("op", 1);

        try (Socket client = new Socket("localhost", 3383)) {

            ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
            outputStream.writeObject(data);

            ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
            int result = inputStream.readInt();

            System.out.println("Resultado é: " +result);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
