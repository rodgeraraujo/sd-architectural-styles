package nf.co.rogerioaraujo.questao_01;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Node1 {
    public static void main(String[] args) {


        //generate a random number
        Random random = new Random();

        //received number
        int received;


        try {
            //instance the socket as null
            Socket client = null;

            client = new Socket("127.0.0.1", 9622);

            //loop
            do {
                List<Integer> numbers = new ArrayList<>();

                //generate first number - random
                int number1 = random.nextInt(101);

                //generate second number - random
                int number2 = random.nextInt(101);

                //add the numbers on arraylist
//                numbers.add(33);
//                numbers.add(33);
                numbers.add(number1);
                numbers.add(number2);

                //print the numbes
                System.out.printf("Numbers generated: %d and %d%n", number1, number2);

                //send numbers to Node2, with an output stream
                ObjectOutputStream outputStream = new ObjectOutputStream(client.getOutputStream());
                outputStream.writeObject(numbers);

                //receive the result from Node2, and print the message if is 0, with an input stream
                ObjectInputStream inputStream = new ObjectInputStream(client.getInputStream());
                //received number from Node2
                received = inputStream.readInt();

                //verify if received number from Node2 is 0
                if (received == 0) {
                    System.out.println("The numbers are equals!");
                }

            } while (received == 0);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
