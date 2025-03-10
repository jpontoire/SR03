package sr03.exo6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.Math.floor;

public class ServeurAllumette {
    public static int jeu_ordi(int nb_allum, int prise_max) {
        int prise = 0;
        int s = 0;
        float t = 0;
        s = prise_max + 1;
        t = ((float) (nb_allum - s)) / (prise_max + 1);
        while (t != floor(t)) {
            s--;
            t = ((float) (nb_allum - s)) / (prise_max + 1);
        }
        prise = s - 1;
        if (prise == 0)
            prise = 1;
        return (prise);
    }

    public static void main(String[] args) {
        int port = 20000;
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Serveur started");
            while(true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected :" + socket.getInetAddress());

                int nb_max_d = 0; /*nbre d'allumettes maxi au départ*/
                int nb_allu_max = 0; /*nbre d'allumettes maxi que l'on peut tirer au maxi*/
                int qui = 0; /*qui joue? 0=Nous --- 1=PC*/
                int prise = 0; /*nbre d'allumettes prises par le joueur*/
                int nb_allu_rest = 0; /*nbre d'allumettes restantes*/
                byte[] bufferint = new byte[10];

                OutputStream output = socket.getOutputStream();
                InputStream input = socket.getInputStream();

                do {
                    output.write("\nNombre d'allumettes disposées entre les deux joueurs (entre 10 et 60) :".getBytes());
                    input.read(bufferint);
                    nb_max_d = (int) bufferint[0];
                }while (nb_max_d < 10 || nb_max_d > 60);

                do {
                    output.write("\nNombre maximal d'allumettes que l'on peut retirer : ".getBytes());
                    input.read(bufferint);
                    nb_allu_max = (int) bufferint[0];
                }
                while ((nb_allu_max >= nb_max_d) || (nb_allu_max == 0));

                do {
                    output.write("\nQuel joueur commence? 0--> Joueur ; 1--> Ordinateur : ".getBytes());
                    input.read(bufferint);
                    qui = (int)bufferint[0];
                }while ((qui != 0) && (qui != 1));

                nb_allu_rest = nb_max_d;

                do {
                    output.write(nb_allu_rest);
                    if (qui == 0) {
                        do {
                            output.write("\nCombien d'allumettes souhaitez-vous piocher ? ".getBytes());
                            input.read(bufferint);
                            prise = (int)bufferint[0];
                        }
                        while ((prise > nb_allu_rest) || (prise > nb_allu_max));
                        /* On répète la demande de prise tant que le nombre donné n'est pas correct */
                    } else {
                        prise = jeu_ordi(nb_allu_rest, nb_allu_max);
                        output.write(prise);
                    }
                    qui = (qui + 1) % 2;

                    nb_allu_rest = nb_allu_rest - prise;
                }
                while (nb_allu_rest > 0);

                input.close();
                output.close();
                socket.close();
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}

