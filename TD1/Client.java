package sr03;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        String IP = "127.0.0.1";
        int PORT = 20000;

        try(Socket socket = new Socket(IP, PORT)) {
            System.out.println("Client connected on serv " + IP + "on the port " + PORT);

            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            output.write("test".getBytes());
            System.out.println("Message envoyé au serveur : ");

            byte[] buffer = new byte[1024];
            input.read(buffer);
            System.out.println("Message : " + new String(buffer));


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
