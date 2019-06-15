package nf.co.rogerioaraujo.questao03.node4;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Loader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 3324;
        ServerSocket server = new ServerSocket(port); //3324
        System.out.println("Node 4 na porta " + port);
        executar(server);
    }

    public static void executar(ServerSocket serverSocket) throws IOException, ClassNotFoundException {
        Socket reqSocket = serverSocket.accept();
        ObjectInputStream inputStream = new ObjectInputStream(reqSocket.getInputStream());
        //
        Map<String, Integer> dados = (Map<String, Integer>) inputStream.readObject();
        int x = dados.get("x");
        int y = dados.get("y");
        int node = dados.get("node");
        int resultado = -1;
        //
        if (node == 2) {
            resultado = x + y;
        } else if (node == 3) {
            resultado = x - y;
        }
        System.out.println("Resultado:" + resultado);
        //
        ObjectOutputStream outputStream = new ObjectOutputStream(reqSocket.getOutputStream());
        outputStream.writeInt(resultado);
        outputStream.flush();
        inputStream.close();
        outputStream.close();
    }

}
