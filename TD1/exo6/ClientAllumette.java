package sr03.exo6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientAllumette {
    public static void afficher_allu(int n) {
        int i;
        System.out.print("\n");
        for (i = 0; i < n; i++)
            System.out.print("  o");
        System.out.print("\n");
        for (i = 0; i < n; i++)
            System.out.print("  |");
        System.out.print("\n");
        for (i = 0; i < n; i++)
            System.out.print("  |");
        System.out.print("\n");

    }

    public static void main(String[] args) {
        String IP = "127.0.0.1";
        int PORT = 20000;

        try(Socket socket = new Socket(IP, PORT)) {
            System.out.println("Client connected on serv " + IP + "on the port " + PORT);

            int nb_max_d = 0; /*nbre d'allumettes maxi au départ*/
            int nb_allu_max = 0; /*nbre d'allumettes maxi que l'on peut tirer au maxi*/
            int qui = 0; /*qui joue? 0=Nous --- 1=PC*/
            int prise = 0; /*nbre d'allumettes prises par le joueur*/
            int nb_allu_rest = 0; /*nbre d'allumettes restantes*/
            byte[] buffer = new byte[1024];

            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();
            Scanner sc = new Scanner(System.in);


            input.read(buffer);
            System.out.println(new String(buffer));
            nb_max_d = sc.nextInt();
            output.write(nb_max_d);

            input.read(buffer);
            System.out.println(new String(buffer));
            nb_allu_max = sc.nextInt();
            output.write(nb_allu_max);

            input.read(buffer);
            System.out.println(new String(buffer));
            qui = sc.nextInt();
            output.write(qui);

            do {
                input.read(buffer);
                nb_allu_rest = (int)buffer[0];
                System.out.println("\nNombre d'allumettes restantes :" + nb_allu_rest);
                afficher_allu(nb_allu_rest);

                if (qui == 0) {
                    do {
                        input.read(buffer);
                        prise = sc.nextInt();
                        if ((prise > nb_allu_rest) || (prise > nb_allu_max)) {
                            System.out.println("Erreur !\n");
                        }
                    } while ((prise > nb_allu_rest) || (prise > nb_allu_max));
                    output.write(prise);
                    /* On répète la demande de prise tant que le nombre donné n'est pas correct */
                } else {
                    input.read(buffer);
                    prise = (int)buffer[0];
                    System.out.println("\nPrise de l'ordi :" + prise);
                }

                qui = (qui + 1) % 2;

                nb_allu_rest = nb_allu_rest - prise;
                System.out.println("\n" + nb_allu_rest);

            }while (nb_allu_rest > 0);


            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

