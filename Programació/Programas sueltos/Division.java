/*
 * Author: Javier Pedragosa
 * Data: 23/09/2021
 * Version: 1.0
 */

/**
 * Aquest programa rebra com a entrada dos nombres enters (dividend i divisor).
 * Retornara els termes de la operacio sencera de la seguent manera: 
 * "dividend = divisor * quocient + residu."
 */

/**
 * Taula de tests
 * Entrada | Sortida esperada
 * -----------------------------
 * 41, 2   | "41 = 20 * 2 + 1"
 */

import java.util.*;

public class Division {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Scanner ent = new Scanner(System.in);

        int dividend, divisor;

        System.out.println("Introdueix el dividend: ");
        dividend = ent.nextInt();
        System.out.println("Introdueix el divisor: ");
        divisor = ent.nextInt();

        System.out.println(dividend + " = " + dividend / divisor + " * " + divisor + " + " + dividend % divisor);

        ent.close();
    }// main
}// class
