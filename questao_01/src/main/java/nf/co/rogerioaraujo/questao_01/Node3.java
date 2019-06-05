package nf.co.rogerioaraujo.questao_01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        int num1, num2;
        Double result;
        List<Integer> numeros;

        //instance the serverSocket and socket client
        ServerSocket serverSocket = new ServerSocket(9633);
        Socket socket = serverSocket.accept();

        //get values, with an output stream
        ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
        numeros = (List<Integer>) inputStream.readObject();

        num1 = numeros.get(0);
        num2 = numeros.get(1);

        //calculate the equation
        result = Math.pow(num1, num1) + Math.pow(num2, num2);
        System.out.printf("Result value: "+ result);

        //send value, with an output stream
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeDouble(result);
        outputStream.flush();
    }

}
