
/**
 * Author: Javier Pedragosa
 * Date: 2/10/2021
 * Version: 1.0
 */

/**
 * Feu un programa per jugar al següent joc. Un jugador serà l’ordinador i l’altre l’usuari.
 * Cada jugador comença amb 0 punts i tira un dau. Suma el nombre de punts obtingut al total de punts.
 * A partir d’aquesta primera tirada, cada jugador decideix successivament si tira un dau més o si es planta.
 * Qui s’apropi més a 13 punts (sense superar-los) guanya la partida.
 * Si algú té més de 13 punts perd automàticament. L’ordinador sempre juga primer, i demana sempre més tirades
 * de daus sempre que la diferència entre 13 i el total de punts sigui major que 3. Quan acabi l’ordinador,
 * és el torn de l’usuari.
 */

import java.util.*;

public class TiraDaus {
    public static void main(String args[]) {
        int looper = 0;
        while (looper == 0) {
            int compPoints = 0, playerPoints = 0; // Set both players' points to 0.

            System.out.println("Torn de l'ordinador.");
            while (compPoints <= 10) {
                compPoints = tirada(compPoints);
            } // L'ordinador fa tirades fins que la seva puntuació sigui major de 10.
            if (compPoints > 13) // Si l'ordinador passa de 13 punts, es notifica
                System.out.println("L'ordinador ha passat de 13 amb " + compPoints + " punts.");
            else // Si no, diu amb quants punts s'ha plantat.
                System.out.println("L'ordinador es planta amb " + compPoints + " punts.");
            wait(500);

            System.out.println("Torn de l’usuari.");

            playerPoints = tornJugador(playerPoints); // La puntuació del jugador es modifica amb el mètode tornJugador
                                                      // fins que
                                                      // es talla el bucle que crea aquest mètode.

            if (playerPoints > 13) // Si l'usuari es passa de 13 punts, es notifica
                System.out.println("L’usuari es passa amb " + playerPoints + " punts.");
            else // Si no, es diu amb quants punts s'ha plantat
                System.out.println("L'usuari es planta amb " + playerPoints + " punts.");
            wait(500);

            if (!endGame(playerPoints, compPoints)) // endGame retorna si el jugador vol jugar una altra partida o no.
                break;
        }
    }

    // Genera un número aleatori entre 1 i 6 i notifica la tirada amb la suma, i la
    // retorna.
    public static int tirada(int points) {
        int tirada = (int) (Math.random() * (6 - 1 + 1) + 1);
        System.out.println(points + " + " + tirada + " = " + (points + tirada));
        wait(500);
        return points + tirada;
    }

    // Mètode invocat a tornJugador(), pregunta a l'usuari si vol tirar un dau.
    public static boolean stand() {
        Scanner ent = new Scanner(System.in);
        String input;
        boolean stand = false;
        int looper = 0;
        System.out.println("Tirar dau (S/N)?");
        input = ent.nextLine();
        while (looper == 0) {
            if (input.equals("S") || input.equals("s")) {
                stand = false;
                break;
            } else if (input.equals("N") || input.equals("n")) {
                stand = true;
                break;
            } else {
                wait(500);
                System.out.println("Resposta no vàlida, respon 's' o 'n'.");
                input = ent.nextLine();
                System.out.println("");
            }
        }
        ent.close();
        return stand;
    }

    public static int tornJugador(int playerPoints) {
        playerPoints = tirada(playerPoints);
        boolean jugadorPlantat;
        while (playerPoints <= 13) {
            jugadorPlantat = stand();
            wait(500);
            if (!jugadorPlantat)
                playerPoints = tirada(playerPoints);
            else
                break;
        }
        return playerPoints;
    }

    // Envia el missatge pertinent de victòria o derrota al final de la partida, i
    // comprova si el jugador vol jugar una altra.
    public static boolean endGame(int playerPoints, int compPoints) {
        boolean restart = true;
        Scanner ent = new Scanner(System.in);
        String input;
        int looper = 0;

        if (playerPoints <= 13 && playerPoints > compPoints) // Guanya el jugador per tenir més punts que l'ordinador
            System.out.println("Guanya l'usuari amb " + playerPoints + " punts!");
        else if (playerPoints <= 13 && compPoints > 13) // Guanya el jugador perquè l'ordinador s'ha passat però ell no
            System.out.println("Guanya l'usuari amb " + playerPoints + " punts!");
        else if (compPoints <= 13 && playerPoints > 13) // Guanya l'ordinador perquè el jugador s'ha passat però ell no
            System.out.println("Guanya l'ordinador amb " + playerPoints + " punts!");
        else if (compPoints <= 13 && compPoints > playerPoints) // Guanya l'ordinador per tenir més punts que el jugador
            System.out.println("Guanya l'ordinador " + compPoints + " punts!");
        else if (playerPoints == compPoints && playerPoints <= 13) // Empaten per tenir la mateixa quantitat de punts
            System.out.println("Empaten l'usuari i l'ordinador amb " + playerPoints + " punts!");
        else {// Perden els dos per haver-se passat.
            System.out.println("Tant l'usuari com l'ordinador han passat de 13 punts.");
            System.out.println("No hi ha cap guanyador.");
        }
        wait(500);
        System.out.println("");
        System.out.println("Vols tornar a jugar? (S/N)");
        input = ent.nextLine();
        System.out.println("");
        while (looper == 0) {
            if (input.equals("S") || input.equals("s")) {
                wait(1000);
                System.out.println("------------------------------------------------");
                wait(500);
                restart = true;
                break;
            } else if (input.equals("N") || input.equals("n")) {
                restart = false;
                break;
            } else {
                wait(500);
                System.out.println("Resposta no vàlida, respon 's' o 'n'.");
                input = ent.nextLine();
                System.out.println("");
            }
        }
        System.out.println("");
        ent.close();
        return restart;
    }

    // Espera ms milisegons abans d'executar la següent sentència.
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
