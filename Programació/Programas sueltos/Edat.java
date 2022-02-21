/*
 * Author: Javier Pedragosa
 * Data: 23/09/2021
 * Version: 1.0
 */

/**
 * Aquest programa rebra com a entrada l'edat d'una persona el 31 de desembre de 2019,
 * i calculara el seu any de naixement i en quin any tindra el doble d'edat que el
 * 31 de desembre de 2019
 */

/**
 * Taula de tests
 * Entrada | Sortida esperada
 * -----------------------------
 * 18      | "Vas neixer l'any 2001. A l'any 2037 tindras 36 anys.
 */
import java.util.*;

public class Edat {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Scanner ent = new Scanner(System.in);
        int edat, edat1, any, any1;
        System.out.println("Digue'm la teva edat el 31 de desembre de 2019");
        edat = ent.nextInt();
        any = 2019 - edat;
        edat1 = edat * 2;
        any1 = any + edat1;

        System.out.println("Vas neixer l'any " + any + ". A l'any " + any1 + " tindras " + edat1 + " anys.");

        ent.close();

    }// main
}// class
