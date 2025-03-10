/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sr03;

import static java.lang.Math.floor;

import java.util.Scanner;

/**
 * @author imineyou
 */
public class Allumette {


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
        }
        while ((nb_allu_max >= nb_max_d) || (nb_allu_max == 0));
        /* On répète la demande de prise tant que le nombre donné n'est pas correct */
        do {
            System.out.println("\nQuel joueur commence? 0--> Joueur ; 1--> Ordinateur : ");
            qui = sc.nextInt();

            if ((qui != 0) && (qui != 1))
                System.out.println("\nErreur");
        }
        while ((qui != 0) && (qui != 1));
        nb_allu_rest = nb_max_d;
        do {
            System.out.println("\nNombre d'allumettes restantes :" + nb_allu_rest);
            afficher_allu(nb_allu_rest);
            if (qui == 0) {
                do {
                    System.out.println("\nCombien d'allumettes souhaitez-vous piocher ? ");
                    prise = sc.nextInt();
                    if ((prise > nb_allu_rest) || (prise > nb_allu_max)) {
                        System.out.println("Erreur !\n");
                    }
                }
                while ((prise > nb_allu_rest) || (prise > nb_allu_max));
                /* On répète la demande de prise tant que le nombre donné n'est pas correct */
            } else {
                prise = jeu_ordi(nb_allu_rest, nb_allu_max);
                System.out.println("\nPrise de l'ordi :" + prise);
            }
            qui = (qui + 1) % 2;

            nb_allu_rest = nb_allu_rest - prise;
        }
        while (nb_allu_rest > 0);


        if (qui == 0) /* Cest à nous de jouer */
            System.out.println("\nVous avez gagné!\n");
        else
            System.out.println("\nVous avez perdu!\n");
    }


}
