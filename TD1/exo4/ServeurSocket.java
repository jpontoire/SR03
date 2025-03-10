package sr03.exo4;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class ServeurSocket {
    public static void main(String[] args) {
        try {
            ServerSocket conn = new ServerSocket(10080);
            Socket comm = conn.accept();
            OutputStream out = comm.getOutputStream();
            InputStream in = comm.getInputStream();
            byte b[] = new byte[20];
            String chaine;

            do {
                in.read(b);
                chaine = new String(b).trim();
                System.out.println("reçu : " + chaine);
                out.write(("suivant ?").getBytes());
                b = new byte[20];
                comm.close();
            } while (!chaine.startsWith("END"));

            System.out.println("Fin");
            in.close();
            out.close();

        } catch (IOException ex) {
            Logger.getLogger(ServerSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
