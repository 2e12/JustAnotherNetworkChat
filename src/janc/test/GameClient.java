package janc.test;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class GameClient {
    public static void main(String[] args) {
        try {
            Socket s = new Socket("192.168.52.144", 3333);
            DataOutputStream outputStream = new DataOutputStream(s.getOutputStream());
            DataInputStream inputStream = new DataInputStream(s.getInputStream());

            System.out.println(inputStream.readUTF());

            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            outputStream.writeUTF(input);
            outputStream.flush();


            System.out.println(inputStream.readUTF()); //Gibt die erste Zeile vom Server aus
            System.out.println(inputStream.readUTF());
            System.out.println(inputStream.readUTF());
            System.out.println(inputStream.readUTF());
            System.out.println(inputStream.readUTF());
            outputStream.close(); //Beendet den Input

            while (true)
            {

            }

        }catch(Exception e){System.out.println(e);}
    }
}