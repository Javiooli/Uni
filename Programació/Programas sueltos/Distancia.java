
/**
 * Author: Javier Pedragosa
 * Date: 23/09/2021
 * Version: 1.0
 */

/**
 * Aquest programa rep com a entrada dos punts del pla real, i retorna
 * la distancia euclidiana entre ells.
 */

/**
 * Taula de tests
 * -----------------
 * Entrada       | Sortida esperada
 * "0, 0, 2, 2"  | "La distancia euclidiana entre els dos punts es de 2."
 * "0, 0, 4, 4"  | "La distancia euclidiana entre els dos punts es de 5.656854"
 * 
 */

import java.util.*;
import java.lang.Math;

public class Distancia {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Scanner ent = new Scanner(System.in);
        int p00, p01, p10, p11;
        double distx, disty, dist;

        System.out.println("Introdueix la primera coordenada del primer punt del pla real:");
        p00 = ent.nextInt();
        System.out.println("Introdueix la segona coordenada del primer punt del pla real:");
        p01 = ent.nextInt();
        System.out.println("Introdueix la primera coordenada del segon punt del pla real:");
        p10 = ent.nextInt();
        System.out.println("Introdueix la segona coordenada del segon punt del pla real:");
        p11 = ent.nextInt();

        ent.close();

        distx = (double) p00 - (double) p10;
        disty = (double) p01 - (double) p11;
        dist = Math.sqrt(distx * distx + disty * disty);

        System.out.println("La distancia euclidiana entre els dos punts es de " + (float) dist);

    }// main
}// class
