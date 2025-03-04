package sr03;

import java.util.Arrays;
import java.util.Scanner;

public class tableau {
    public static int min(int[] tab) {
        int min = tab[0];
        for (int nb : tab) {
            if (nb < min) {
                min = nb;
            }
        }
        return min;
    }

    public static int max(int[] tab) {
        int max = tab[0];
        for (int nb : tab) {
            if (nb > max) {
                max = nb;
            }
        }
        return max;
    }

    public static int mean(int[] tab) {
        return Arrays.stream(tab).sum() / tab.length;
    }

    public static int std(int[] tab) {
        float variance = 0;
        int sum = 0;
        for (int nb : tab) {
            sum += nb;
            variance += (float)sum / (float)tab.length;
        }
        variance = variance / tab.length;
        return (int)Math.round(variance);
    }

    public static void main(String[] args) {
        int[] tableau = new int[10];
        Scanner scan = new Scanner(System.in);
        for (int i = 0; i < 10; i++) {
            System.out.println("Veuillez saisir un nombre :");
            int nb = scan.nextInt();
            tableau[i] = nb;
        }
        System.out.println("min =" + min(tableau));
        System.out.println("max =" + max(tableau));
        System.out.println("mean =" + mean(tableau));
        System.out.println("std =" + std(tableau));
    }
}
