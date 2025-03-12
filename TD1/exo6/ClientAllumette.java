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
        int PORT = 27845;

        try(Socket socket = new Socket(IP, PORT)) {
            System.out.println("Client connected on serv " + IP + "on the port " + PORT);

            byte[] buffer = new byte[1024];
            int nb_max_d = 0; /*nbre d'allumettes maxi au départ*/
            int nb_allu_max = 0; /*nbre d'allumettes maxi que l'on peut tirer au maxi*/
            int qui = 0; /*qui joue? 0=Nous --- 1=PC*/
            int prise = 0; /*nbre d'allumettes prises par le joueur*/
            int nb_allu_rest = 0; /*nbre d'allumettes restantes*/
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("Nombre d'allumettes disposées entre les deux joueurs (entre 10 et 60) :");
                nb_max_d = sc.nextInt();
            }
            while ((nb_max_d < 10) || (nb_max_d > 60));

            do {
                System.out.println("\nNombre maximal d'allumettes que l'on peut retirer : ");
                nb_allu_max = sc.nextInt();
                if (nb_allu_max >= nb_max_d)
                    System.out.println("Erreur !");
            } while ((nb_allu_max >= nb_max_d) || (nb_allu_max == 0));

            do {
                System.out.println("\nQuel joueur commence? 0--> Joueur ; 1--> Ordinateur : ");
                qui = sc.nextInt();

                if ((qui != 0) && (qui != 1))
                    System.out.println("\nErreur");
            } while ((qui != 0) && (qui != 1));

            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            input.read(buffer);
            System.out.println(new String(buffer).trim());
            nb_max_d = sc.nextInt();
            output.write(nb_max_d);
            output.flush();

            input.read(buffer);
            System.out.println(new String(buffer).trim());
            nb_allu_max = sc.nextInt();
            output.write(nb_allu_max);
            output.flush();

            input.read(buffer);
            System.out.println(new String(buffer).trim());
            qui = sc.nextInt();
            output.write(qui);
            output.flush();

            nb_allu_rest = nb_max_d;

            do {
                System.out.println("\nNombre d'allumettes restantes :" + nb_allu_rest);
                afficher_allu(nb_allu_rest);

                if (qui == 0) {
                    do {
                        input.read(buffer);
                        System.out.println(new String(buffer).trim());
                        prise = sc.nextInt();
                        if ((prise > nb_allu_rest) || (prise > nb_allu_max)) {
                            System.out.println("Erreur !\n");
                        }
                    } while ((prise > nb_allu_rest) || (prise > nb_allu_max));
                    output.write(prise);
                    output.flush();
                    /* On répète la demande de prise tant que le nombre donné n'est pas correct */
                } else {
                    input.read(buffer);
                    prise = (int)buffer[0];
                    System.out.println("\nPrise de l'ordi :" + prise);
                }

                qui = (qui + 1) % 2;

                nb_allu_rest = nb_allu_rest - prise;

            }while (nb_allu_rest > 0);

            if (qui == 0) /* Cest à nous de jouer */
                System.out.println("\nVous avez gagné!\n");
            else
                System.out.println("\nVous avez perdu!\n");


            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

