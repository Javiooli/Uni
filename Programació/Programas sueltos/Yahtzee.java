package p2;
/**
 * @author Javier Pedragosa
 * @version 1.0
 * Date: 01/11/2021
 */

/**
 * Feu un programa per a jugar a una versió simple del Yahtzee.
 * El programa ha de simular la tirada de cinc daus i imprimeix
 * "Yahtzee" si els cinc daus són iguals; en cas contrari,
 * s'hauria d'imprimir "Torna a intentar-ho".
 */

public class Yahtzee {
    public static void main(String args[]) {
        int NUM_DAUS = 5, NUM_TIRADES = 5;
        int [][] daus = new int [NUM_TIRADES][NUM_DAUS];
        mostrarPartides(daus, NUM_DAUS, NUM_TIRADES);

        
        
    }

    /**
     * Aquest mètode simula les nT tirades de nD daus i retorna una matriu amb els valors.
     * @param daus és la matriu nT x nD a omplir amb nombres aleatoris entre 1 i 6.
     * @param nT és la quantitat de tirades que fem, és a dir, la quantitat de vegades que generem
     *          nD daus.
     * @param nD és la quantitat de daus que tirem per tirada, és a dir, la quantitat de nombes
     *          aleatoris entre 1 i 6 que hem de generar.
     * @return daus és la matriu amb els nD x nT nombres aleatoris entre 1 i 6.
     */
    static int [][] tiraDaus(int [][] daus, int nT, int nD) {
        /**
         * Identificació de la sequència: Nombre de daus. i.
         * Primer: i = 0.
         * Seguent(): i + 1.
         * FinalSeq(): i = nD.
         * Esquema: Recorregut.
         */
        for (int i = 0; i < (nT); i++){
            /**
             * Identificació de la sequència: Nombre de tirades. j.
             * Primer: j = 0.
             * Seguent(): j + 1.
             * FinalSeq(): j = nT.
             * Esquema: Recorregut.
             */
            for (int j = 0; i < (nD); j++){
                daus[i][j] = (int) (Math.random() * (6 - 1 + 1) + 1);
            }
        }
        return daus;
    }

    /**
     * Aquest mètode retorna true tots els daus són iguals comparant-los amb el primer.
     * @param daus és la matriu amb els valors dels daus.
     * @param nT és el nombre de fil·les de la matriu.
     * @param nD és el nombre de col·lumnes de la matriu.
     * @return tirada és la tirada on hi ha hagut una parella consecutiva o -1 si no n'hi ha cap.
     */
    static int esYahtzee(int [][] daus, int nT, int nD) {
        int i = 0, j = 0;
        int dau, antDau, tirada = -1;
        boolean parella = false;
        while (i < nT && !parella){
            while (j < nD-1 && !parella){
                antDau = daus[i][j];
                dau = daus[i][j+1];
                if (antDau == dau){
                    parella = true;
                    tirada = i;
                }
            }
            i++;
        }
        return tirada;
    }

    static void mostrarPartides(int [][] daus, int nT, int nD){
        for (int i = 0; i < nT; i++){
            System.out.println("Tirada " + (i+1) + ": ");
            for (int j = 0; j < nD; j++){
                System.out.print(daus[i][j]);
            }
        }
    }
}
