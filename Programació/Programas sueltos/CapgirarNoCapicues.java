
import java.util.*;

/**
 * L'usuari introdueix una frase acabada en '.' per teclat, realitza un programa
 * que només capgiri les paraules de la frase que NO son capicues. Les capicues
 * les imprimeix com una seqüència d'asteriscs.
 */

public class CapgirarNoCapicues {
    public static void main(String args[]) {
        String s = "a", sReverse = "";
        boolean trobat = false;
        Scanner sc = new Scanner(System.in);

        // Identificació de la seqüència principal: sequència de paraules entrades per
        // teclat
        //
        // - Primer(): s = sc.next()
        // - Següent(s): s = sc.next()
        // - FinSeq(s): s.endsWith(".")
        //
        // Esquema a aplicar: recorregut

        System.out.println("Introdueixi una frase acabada en .");
        while (!trobat) {
            s = sc.next(); // Primer() i Següent(s)
            sReverse = "";

            // FinSeq(s)
            if (s.endsWith(".")) {
                s = s.substring(0, s.length() - 1);
                trobat = true;
            }
            // Identificació de la seqüència principal: sequència de paraules entrades per
            // teclat
            //
            // - Primer(): s = sc.next()
            // - Següent(s): s = sc.next()
            // - FinSeq(s): s.endsWith(".")
            //
            // Esquema a aplicar: recorregut
            for (int i = s.length() - 1; i >= 0; i--) {
                sReverse += s.charAt(i);
            }

            if (s.equals(sReverse)) {
                for (int i = 0; i < s.length(); i++) {
                    System.out.print("*");
                }
            } else
                System.out.print(sReverse);
            if (!trobat)
                System.out.print(" ");
        }
        sc.close();
        System.out.print(".");
    }
}
