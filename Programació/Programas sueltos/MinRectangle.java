
/**
 * Feu un programa que donats dos rectangles (amb costats paral·lels als eixos)
 * definits com al problema anterior, calcula el rectangle mínim que els conté.
 * Si els rectangles no intersequen, s’hauria de mostrar un missatge “No intersequen, no hi ha rectangle mínim”.
 * Per exemple, si l’usuari introdueix el primer rectangle amb les coordenades (1.0, 3.0)
 * (3.0, 5.0) i el segon amb les coordenades (2.0, 1.0) (4.0, 4.0), la sortida del programa ha de ser (2.0, 3.0) (3.0 , 4.0).
 * Si l’usuari introdueix el primer rectangle amb les coordenades (1.0, 1.0) (1.0, 2.0)
 * i el segon amb les coordenades (4.0, 4.0) (6.0, 6.0), la sortida del programa ha de ser “No intersequen, no hi ha rectangle mínim”.
 */
import java.util.*;

public class MinRectangle {
    public static void main(String args[]) {
        Scanner ent = new Scanner(System.in);
        double[] rectangle1 = { 0, 0, 0, 0 }, rectangle2 = { 0, 0, 0, 0 };
        String[] rect1, rect2;
        String ent1, ent2;
        while (rectangle1[0] == 0 && rectangle1[1] == 0 && rectangle1[2] == 0 && rectangle1[3] == 0) {
            System.out
                    .println("Defineix dos punts d'un rectangle amb les coordenades separades nomes per una coma (,)");
            System.out.println("(El format seria 'x1,y1,x2,y2', per exemple: '4,2.5,6,8'): ");
            ent1 = ent.nextLine();
            try {
                rect1 = ent1.split(",");
                rectangle1[0] = Double.valueOf(rect1[0]);
                rectangle1[1] = Double.valueOf(rect1[1]);
                rectangle1[2] = Double.valueOf(rect1[2]);
                rectangle1[3] = Double.valueOf(rect1[3]);
            } catch (NumberFormatException ex) {
                rectangle1[0] = 0;
                rectangle1[1] = 0;
                rectangle1[2] = 0;
                rectangle1[3] = 0;
                System.out.println("Format incorrecte.");
            } catch (ArrayIndexOutOfBoundsException ex) {
                rectangle1[0] = 0;
                rectangle1[1] = 0;
                rectangle1[2] = 0;
                rectangle1[3] = 0;
                System.out.println("Format incorrecte.");
            }
        }
        while (rectangle2[0] == 0 && rectangle2[1] == 0) {
            System.out.println("Defineix el segon rectangle: ");
            ent2 = ent.nextLine();
            try {
                rect2 = ent2.split(",");
                rectangle2[0] = Double.valueOf(rect2[0]);
                rectangle2[1] = Double.valueOf(rect2[1]);
                rectangle2[2] = Double.valueOf(rect2[2]);
                rectangle2[3] = Double.valueOf(rect2[3]);
            } catch (NumberFormatException ex) {
                rectangle2[0] = 0;
                rectangle2[1] = 0;
                rectangle2[2] = 0;
                rectangle2[3] = 0;
                System.out.println("Format incorrecte.");
            } catch (ArrayIndexOutOfBoundsException ex) {
                rectangle2[0] = 0;
                rectangle2[1] = 0;
                rectangle2[2] = 0;
                rectangle2[3] = 0;
                System.out.println("Format incorrecte.");
            }

        }

        if (doOverlap(rectangle1, rectangle2)) {
            double minRectangle[] = { 0, 0, 0, 0 };
            if (rectangle2[0] > rectangle1[0] && rectangle2[0] < rectangle1[2])
                minRectangle[0] = rectangle2[0];
            else
                minRectangle[0] = rectangle1[0];

            if (rectangle2[1] > rectangle1[1] && rectangle2[1] < rectangle1[3])
                minRectangle[1] = rectangle2[1];
            else
                minRectangle[1] = rectangle1[1];

            if (rectangle2[2] > rectangle1[2] && rectangle2[2] < rectangle1[0])
                minRectangle[2] = rectangle2[2];
            else
                minRectangle[2] = rectangle1[2];

            if (rectangle2[3] > rectangle1[3] && rectangle2[3] < rectangle1[1])
                minRectangle[0] = rectangle2[0];
            else
                minRectangle[3] = rectangle1[3];
            System.out.println("(" + minRectangle[0] + " , " + minRectangle[1] + ") (" + minRectangle[2] + " , "
                    + minRectangle[3] + ")");

        } else {
            System.out.println("No intersequen, no hi ha rectangle mínim.");
        }

        ent.close();
    }

    public static boolean doOverlap(double[] rectangle1, double[] rectangle2) {
        boolean overlapped = false;
        if (rectangle2[0] > rectangle1[2] || rectangle2[1] > rectangle1[3] || rectangle2[2] < rectangle1[0]
                || rectangle2[3] < rectangle1[1])
            overlapped = false;
        else
            overlapped = true;
        return overlapped;
    }

}
