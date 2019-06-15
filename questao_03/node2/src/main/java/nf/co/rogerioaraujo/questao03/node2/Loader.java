package nf.co.rogerioaraujo.questao03.node2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Loader {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int port = 3322;
        ServerSocket server = new ServerSocket(port);//3322
        System.out.println("Node 2 na porta " + port);
        executar(server);
    }

    public static void executar(ServerSocket serverSocket) throws IOException, ClassNotFoundException {
        Socket reqSocket = serverSocket.accept();
        ObjectInputStream inputStream = new ObjectInputStream(reqSocket.getInputStream());
        //
        Map<String, Integer> dados = (Map<String, Integer>) inputStream.readObject();
        int node = dados.get("node");
        int resultado = -1;
        Socket cliente = null;
        dados.replace("node", 2);
        //
        if (node == 1) {
            cliente = new Socket("127.0.0.1", 3323);
        } else if (node == 3) {
            cliente = new Socket("127.0.0.1", 3324);
        }
        //
        ObjectOutputStream outputStream1 = new ObjectOutputStream(cliente.getOutputStream());
        outputStream1.writeObject(dados);
        ObjectInputStream inputStream1 = new ObjectInputStream(cliente.getInputStream());
        resultado = inputStream1.readInt();
        //
        inputStream1.close();
        outputStream1.close();
        cliente.close();
        //
        ObjectOutputStream outputStream = new ObjectOutputStream(reqSocket.getOutputStream());
        outputStream.writeInt(resultado);
        outputStream.flush();
        outputStream.close();
    }

}
