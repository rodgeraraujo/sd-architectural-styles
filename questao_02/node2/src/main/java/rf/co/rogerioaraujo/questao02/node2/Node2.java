package rf.co.rogerioaraujo.questao02.node2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Node2 {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(3383)) {
            System.out.println("Node2\nAguardando Conexão");

            Socket request = server.accept();

            ObjectInputStream inputStreamServer = new ObjectInputStream(request.getInputStream());

            Map<String, Integer> data = (Map<String, Integer>) inputStreamServer.readObject();

            System.out.println("Dados");
            data.entrySet().stream().forEach(System.out::println);

            Integer op = data.get("op");
            Integer result = -1;

            ObjectOutputStream outputStreamServer = new ObjectOutputStream(request.getOutputStream());

            if (op == 2) {


                Integer x = data.get("x");
                Integer y = data.get("y");

                result = (2 * x) / y;
            } else {

                try (Socket cliente = new Socket("localhost", 3381)) {

                    result = redireciona(cliente, data);

                } catch (IOException ex1) {
                    try (Socket client = new Socket("localhost", 3382)) {

                        result = redireciona(client, data);


                    } catch (IOException ex2) {
                        ex2.printStackTrace();
                    }
                }

            }

            outputStreamServer.writeInt(result);
            outputStreamServer.flush();

            outputStreamServer.close();


        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

    public static Integer redireciona(Socket client, Map<String, Integer> data) throws IOException {

        ObjectOutputStream outputStreamClient = new ObjectOutputStream(client.getOutputStream());
        outputStreamClient.writeObject(data);

        ObjectInputStream inputStreamClient = new ObjectInputStream(client.getInputStream());
        Integer result = inputStreamClient.readInt();

        inputStreamClient.close();
        outputStreamClient.close();

        return result;
    }

}
