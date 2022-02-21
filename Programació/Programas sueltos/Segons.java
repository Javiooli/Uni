
/**
 * author: Javier Pedragosa
 * data: 21/09/2021
 * versio: 1.0
 */

/**
 * Aquest programa rep un numero, en segons, i el converteix
 * en dies, hores, minuts i segons.
 */

/** Taula de tests
 * Entrada | Sortida
 * ------------------------------------------
 * 89999   | 89999 segons son: 1d 0h 59m 59s
 * 45      | 45 segons son: 0d 0h 0m 45s
 * 121     | 121 segons son: 0d 0h 2m 1s
 */
import java.util.Scanner;

public class Segons {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Scanner entrada;
        int ent, d, h, m, s;

        entrada = new Scanner(System.in);

        System.out.print("Dona'm una quantitat de segons: ");
        ent = entrada.nextInt();

        s = ent % 60;
        m = (ent / 60) % 60;
        h = (ent / 3600) % 24;
        d = (ent / 3600) / 24;

        System.out.println(ent + " segons son: " + d + " dies, " + h + " hores, " + m + " minuts i " + s + " segons");

        entrada.close();
    }
}
