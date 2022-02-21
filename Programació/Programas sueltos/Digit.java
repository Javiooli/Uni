
/**
 * @author Javier Pedragosa
 * data: 23/09/2021
 * version: 1.0
 */
/**
 * Aquest programa rebra com entrada un nombre i dira si el
 * nombre inserit es d'un sol digit o no.
 */
/**
 * Taula de tests
 * Entrada | Sortida esperada
 * ----------------------------------------
 * 234     | "No es un digit"
 * 4       | "Es un digit"
 */
import java.util.*;

public class Digit {

    public static void main(String args[]) {
        Scanner ent = new Scanner(System.in);
        int num;

        System.out.println("Dona'm un enter: ");
        num = ent.nextInt();
        if (num % 10 == num) {
            System.out.println("Es un digit.");
        } else {
            System.out.println("No es un digit.");
        }

        ent.close();
    }// main
}// class
