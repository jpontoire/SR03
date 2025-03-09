package sr03;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        int port = 20000;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur started");
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected :" + socket.getInetAddress());

                OutputStream output = socket.getOutputStream();
                InputStream input = socket.getInputStream();

                byte[] buffer = new byte[1024];
                input.read(buffer);
                System.out.println("Message reçu du client : " + new String(buffer));

                output.write("ouais c'est nickel".getBytes());
                System.out.println("Message envoyé au client");

                socket.close();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}
