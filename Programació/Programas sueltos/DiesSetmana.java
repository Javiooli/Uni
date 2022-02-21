/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author javis
 */
import java.util.*;

public class DiesSetmana {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        Scanner ent = new Scanner(System.in);
        System.out.println("Introdueix un nombre.");
        int n = ent.nextInt();
        System.out.println("El dia que correspon al numero " + n + " es: " + nomDiesSetmana(n) + '.');
        ent.close();
    }

    static String nomDiesSetmana(int n) {
        String dia;
        switch (n) {
            case 1:
                dia = "Dilluns";
                break;
            case 2:
                dia = "Dimarts";
                break;
            case 3:
                dia = "Dimecres";
                break;
            case 4:
                dia = "Dijous";
                break;
            case 5:
                dia = "Divendres";
                break;
            case 6:
                dia = "Dissabte";
                break;
            case 7:
                dia = "Diumenge";
                break;
            default:
                dia = "Error";
                break;
        }
        return dia;
    }
}
