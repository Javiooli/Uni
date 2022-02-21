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
 *      Entrada           |        Sortida esperada
 * ---------------------------------------------------------
 * "ATCGAT", "CGATAT"     |  "Les dues cadenes són iguals."
 * "ATCAG", "ATGAC"       |  "Les cadenes no són iguals."
 * "ATGCGTAT", "ATATGCGT" |  "Les dues cadenes són iguals."
 */

import java.util.*;
public class AdnIgualCopy {
    public static void main(String args[]) {
        boolean iguals = true, end = false;
        int pos = 0, i = 0, j = 1;
        String cadena1 = cadenaScan(0), cadena2 = cadenaScan(1);
        Character [] arrCadena1 = cadenaArray(cadena1), arrCadena2 = cadenaArray(cadena2);
        int len1 = arrCadena1.length, len2 = arrCadena2.length;
        if (len1 != len2) iguals = false;
        else iguals = arrayComp(arrCadena1, arrCadena2);
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
        int cursor = 0, i = 0, j = 0, len = array1.length;
        boolean iguals = false;
            /**
             * Identificació de la sequència: Llargada de les cadenes/arrays. i.
             * Primer: cursor = 0.
             * Seguent(): cursor + 1.
             * FinalSeq(): cursor = len (len = array1.length)
             * Esquema: Cerca.
             * Condició: !iguals, que es torna true quan trobem que un caràcter
             * a la primera array és igual a un altre caràcter de l'altra array.
             */
        while (!iguals && cursor < len){
            /** 
             * No sé si és la manera correcta de fer aquests dos bucles anidats.
             * Ambdós tenen la mateixa seqüència, els mateixos elements i la mateixa
             * condició, però m'interessa sobre tot per la condició ja que pot patir
             * diversos canvis dins del bucle niu (el de dalt), llavors m'interessa
             * que només afecti al bucle anidat.
             */
            while (!iguals && cursor < len){
                if (array1[cursor] == array2[0]){
                    iguals = true;
                }
                else cursor++;
            }
            
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
                if (i + cursor < len){
                    if (array2[i] != array1[cursor+i]) iguals = false;
                    i++;
                }
                else{
                    if (array2[i] != array1[j]) iguals = false;
                    i++;
                    j++;
                }
            }
            i = 0;
            j = 0;
            cursor++;
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