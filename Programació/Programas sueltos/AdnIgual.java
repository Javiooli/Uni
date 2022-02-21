/**
 * @author Javier Pedragosa
 * @version 1.0
 * Date 16/11/2021
 */

 /**
  * Escriu un programa que guardi un array que representi la seqüència d’ADN i
  * inclogui un mètode booleà que ens retorni true si dues seqüències coincideixen.
  */

/**
 * Taula de tests
 *      Entrada        |        Sortida esperada
 * ----------------------------------------------------
 * "ATCGAT", "CGATAT"  | "Les dues cadenes són iguals."
 * "ATCAG", "ATGAC"    | "Les cadenes no són iguals."
 */

import java.util.*;
public class AdnIgual {
    public static void main(String args[]) {
        boolean iguals = false, end = false;
        int pos = 0, i = 0, j = 1;
        String cadena1 = cadenaScan(0), cadena2 = cadenaScan(1);
        Character [] arrCadena1 = cadenaArray(cadena1), arrCadena2 = cadenaArray(cadena2);
        int len1 = arrCadena1.length, len2 = arrCadena2.length;
        Character [] sortedArrCadena1 = new Character[len1];
        if (len1 != len2) iguals = false;
        else {
            /**
             * Identificació de la sequència: Llargada de les cadenes/arrays. j.
             * Primer: j = 0.
             * Seguent(): j + 1.
             * FinalSeq(): i = len1 (len1 = arrCadena1.length)
             * Esquema: Cerca.
             * Condició: !end, que es torna true quan determinem que les dues
             * cadenes són iguals o hem fet len1 iteracions i no hem trobat coincidència.
             */
            while (!end && j < len1){
                /**
                 * Identificació de la sequència: Llargada de les cadenes/arrays. i.
                 * Primer: i = 0.
                 * Seguent(): i + 1.
                 * FinalSeq(): i = len1 (len1 = arrCadena1.length)
                 * Esquema: Cerca.
                 * Condició: !iguals, que es torna true quan trobem que un caràcter
                 * a la primera array és igual a un altre caràcter de l'altra array.
                 */
                while (i < len1 && !iguals){
                    if (arrCadena1[i] == arrCadena2[0]){
                        iguals = true;
                        i++;
                    }
                    else i++;
                }
                if (iguals){
                    /**
                     * Identificació de la sequència: Llargada de les cadenes/arrays. k.
                     * Primer: k = 0.
                     * Seguent(): k + 1.
                     * FinalSeq(): k = len1 (len1 = arrCadena1.length)
                     * Esquema: Recorregut.
                     */
                    for (int k = 0; k < len1; k++){
                        if (i-1 + k < len1) sortedArrCadena1[k] = arrCadena1[i-1 + k];
                        else {
                            sortedArrCadena1[k] = arrCadena1[pos];
                            pos++;
                        }
                    }
                    pos = 0;
                    iguals = arrayComp(sortedArrCadena1, arrCadena2);
                }
                if (iguals) end = true;
                j++;
            }
        }
        if (iguals) System.out.println("Les dues cadenes són iguals.");
        else System.out.println("Les cadenes no són iguals.");
    } //main


    /**
     * Aquest mètode retorna true si les dues arrays són iguals element a element, i false en cas negatiu.
     * @param array1 és una de les arrays a comparar.
     * @param array2 és la altra array a comparar.
     * @return iguals és true si array1 == array2 element a element.
     */
    public static boolean arrayComp(Character[] array1, Character[] array2){
        int i = 0, len = array1.length;
        boolean iguals = true;
        /**
         * Identificació de la sequència: Llargada de les arrays. i.
         * Primer: i = 0.
         * Seguent(): i + 1.
         * FinalSeq(): i = len (len = array1.length)
         * Esquema: Cerca.
         * Condició: iguals, que es torna false quan trobem que dos caràcters no
         * coincideixen.
         */
        while (i < len && iguals){
            if (array1[i] != array2[i]) iguals = false;
            i++;
        }
        return iguals;
    } //arrayComp

    
    /**
     * @param n és el nombre de la cadena que s'introdueix, la primera o la segona.
     * @return cadena és la cadena introduïda per teclat.
     */
    public static String cadenaScan(int n){
        Scanner ent = new Scanner(System.in);
        String [] i = {"primera", "segona"};
        String cadena;
        System.out.println("Introdueix la " + i[n] + " cadena d'ADN, tota en majúscula i sense espais:");
        cadena = ent.nextLine();
        return cadena;
    } //cadenaScan


    /**
     * Aquest mètode converteix una cadena de caràcters en array.
     * @param cadena és la cadena a convertir en array.
     * @return arrCadena és la array creada i omplerta en base a cadena.
     */
    public static Character[] cadenaArray(String cadena){
        Character [] arrCadena = new Character[cadena.length()];
        /**
         * Identificació de la sequència: Llargada de la cadena. i.
         * Primer: i = 0.
         * Seguent(): i + 1.
         * FinalSeq(): i = cadena.length()
         * Esquema: Recorregut.
         */
        for (int i = 0; i < cadena.length(); i++){
            arrCadena[i] = cadena.charAt(i);
        }
        return arrCadena;
    } //cadenaArray

} // AdnIgual