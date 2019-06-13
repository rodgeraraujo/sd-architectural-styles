package rf.co.rogerioaraujo.questao02.node1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Node1 {

    private static Integer serverPort;

    public static void main(String[] args) {

        int port = 3381;
        try (ServerSocket server = new ServerSocket(port)) {
            System.out.println("Node1 na porta " + port);
            serverPort = 3381;

            executar(server);

        } catch (BindException e) {

            try (ServerSocket server = new ServerSocket(3382)) {
                System.out.println("Node2 na porta 3382");
                serverPort = 3382;

                executar(server);

            } catch (ClassNotFoundException | IOException ex) {
                ex.printStackTrace();
            }

        } catch (ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }

    private static void executar(ServerSocket server) throws IOException, ClassNotFoundException {

        Socket request = server.accept();
        ObjectInputStream inputStreamServer = new ObjectInputStream(request.getInputStream());

        Map<String, Integer> data = (Map<String, Integer>) inputStreamServer.readObject();

        Integer op = data.get("op");
        Integer result = -1;

        ObjectOutputStream outputStreamServer = new ObjectOutputStream(request.getOutputStream());

        if (op == 1) {
            Integer x = data.get("x");
            Integer y = data.get("y");

            result = 2 * y * x;
        } else if (serverPort == 3381) {
            Socket cliente = new Socket("localhost", 3383);

            ObjectOutputStream outputStreamClient = new ObjectOutputStream(cliente.getOutputStream());
            outputStreamClient.writeObject(data);

            ObjectInputStream inputStreamClient = new ObjectInputStream(cliente.getInputStream());
            result = inputStreamClient.readInt();

            inputStreamClient.close();
            outputStreamClient.close();
            cliente.close();

        }

        outputStreamServer.writeInt(result);
        outputStreamServer.flush();

        outputStreamServer.close();

    }

}
