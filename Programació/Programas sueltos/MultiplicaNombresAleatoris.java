
/**
 * Author: Javier Pedragosa
 * Date: 24/09/2021
 * Version: 1.0
 */

/**
 * Aquest programa generara dos enters aleatoris i preguntara a l'usuari quin es el producte
 * d'aquests dos nombres. Si l'usuari respon correctament, el felicitara, si respon
 * de manera incorrecta, l'incitara a estudiar les taules de multiplicar.
 */

import java.util.*;

public class MultiplicaNombresAleatoris {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        int n0, n1, resp;
        Scanner ent = new Scanner(System.in);

        n0 = (int) (Math.random() * 10);
        n1 = (int) (Math.random() * 10);

        System.out.println("Digues el resultat de " + n0 + " * " + n1 + ".");

        resp = ent.nextInt();

        ent.close();

        if (resp == n0 * n1) {
            System.out.println("Ben fet!");
        } else {
            System.out.println("Has d’estudiar la taula de multiplicar.");
        }

    }// main
}// class
