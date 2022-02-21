
/**
 * Author: Javier Pedragosa
 * Date: 27/09/2021
 * Version: 1.0
 */

import java.util.*;

public class Fibonacci {

    public static void main(String args[]) {
        int i = 0, n, a = 0, b = 1, c = 1;

        Scanner ent = new Scanner(System.in);

        System.out.println("Quants nombres de la successio de Fibonacci vols visualitzar?");
        System.out.println("(max 46.)");

        n = ent.nextInt();
        ent.close();

        n--;

        System.out.println(c);

        while (i < n) {

            if (i == 45) {
                System.out.println("S'ha arribat al nombre maxim que aquest programa pot processar.");
                break;
            } // Aquest if es necessari perque per limitacions de les variables int, no
              // podem superar el nombre donat per un input major a 45.

            c = a + b;

            System.out.println(c);

            a = b;
            b = c;

            i++;

        } // while

    }// main

}// class
