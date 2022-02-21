import java.util.*;

public class ParaulesIguals {
    public static void main(String args[]) {
        String paraula1, paraula2;
        Scanner ent = new Scanner(System.in);
        System.out.println("Introdueix una paraula: ");
        paraula1 = ent.nextLine();
        System.out.println("Introdueix una altra paraula: ");
        paraula2 = ent.nextLine();

        ent.close();

        if (paraulesIguals(paraula1, paraula2))
            System.out.println("Les paraules son iguals");
        else
            System.out.println("Les paraules no son iguals");

    }

    static boolean paraulesIguals(String paraula1, String paraula2) {
        boolean iguals = false, sentinelFound = false;
        int i = 0;
        if (paraula1.length() == paraula2.length()) {
            /**
             * Identificacio de la sequencia: sequencies de caracters (String) que s'han
             * introduit anteriorment. Primer: paraula1.charat(i) on i = 0 Seguent():
             * paraula1.charat(i) FinalSeq(): i = paraula1.length() Esquema: Cerca.
             * Condicio: !sentinelFound
             */
            while (i < paraula1.length() && !sentinelFound) {
                if (paraula1.charAt(i) == paraula2.charAt(i))
                    i++;
                else
                    sentinelFound = true;

            }
            if (sentinelFound)
                iguals = false;
            else
                iguals = true;
        } else
            iguals = false;
        return iguals;
    }
}