
/**
 * Author: Javier Pedragosa
 * Date: 28/09/2021
 * Version: 1.1
 */

/**
 * Feu un programa per a jugar el joc del Nim. Dos jugadors col·loquen a un
 * tauler un nombre de fitxes major o igual a 20 i menor estricte a 30 (aquest
 * nombre es genera de forma aleatòria). Un jugador és l’ordinador, l’altre serà
 * un jugador humà. Un nombre també aleatori indicarà quin jugador comença a
 * jugar. Cada jugador en el seu torn retira una o dues fitxes. L’ordinador
 * genera un nombre aleatori entre 1 i 2 per a retirar fitxes. El tauler s’ha
 * d’anar actualitzant en cada torn. Guanya el jugador que aconsegueix retirar
 * l’última fitxa. (Nim.java)
 */

import java.util.*;

public class Nim {

    public static void main(String args[]) {
        int i = 0;
        boolean playerWon = true;
        // "Infinite" loop that only breaks if the player doesn't choose to restart at
        // the end of the match.
        while (i == 0) {
            int chips = initializeChips();// global chip count.
            int first = initializeFirst();// if 0 player draws first, if 1 computer draws first.

            // Does the player start?
            if (first == 0) {
                System.out.println("Juguem amb " + chips + " fitxes.");
                wait(500);
                System.out.println("");
                System.out.println("Comences tu.");
                wait(500);
                System.out.println("");
            } else {
                System.out.println("Juguem amb " + chips + " fitxes.");
                wait(500);
                System.out.println("");
                System.out.println("Comença l'ordinador.");
                wait(500);
                System.out.println("");
            }

            // Here runs the game if player is first.
            while (first == 0 && chips > 0) {
                chips -= playerDraw();
                if (chips > 0) {
                    int compDrawn = compDraw(chips);
                    chips -= compDrawn;
                    if (chips <= 0) {
                        if (compDrawn == 1)
                            System.out.println("L'ordinador ha agafat la última fitxa.");
                        else
                            System.out.println("L'ordinador ha agafat les últimes fitxes.");

                        wait(500);
                        System.out.println("");
                        playerWon = false;
                        break;
                    } else
                        readChips(chips);
                } else if (chips < 0) {
                    System.out.println("No pots agafar dues fitxes perquè només queda una.");
                    wait(500);
                    System.out.println("Agafes només una fitxa.");
                    wait(500);
                    System.out.println("");
                    playerWon = true;
                } else {
                    playerWon = true;
                }
            } // while (playerIsFirst)

            // Here if comp is first
            while (first == 1 && chips > 0) {
                int compDrawn = compDraw(chips);
                chips -= compDrawn;
                if (chips > 0) {
                    readChips(chips);
                    chips -= playerDraw();
                    if (chips <= 0) {
                        if (chips != 0) {
                            System.out.println("No pots agafar dues fitxes perquè només queda una.");
                            wait(500);
                            System.out.println("Agafes només una fitxa.");
                            wait(500);
                            System.out.println("");
                        }
                        playerWon = true;
                        break;
                    }
                    // else
                    // readChips(chips);
                } else {
                    if (compDrawn == 1) {
                        System.out.println("L'ordinador ha agafat la última fitxa.");
                        wait(500);
                    } else {
                        System.out.println("L'ordinador ha agafat les últimes fitxes.");
                        wait(500);
                    }
                    System.out.println("");

                    playerWon = false;
                }
            } // while (compIsFirst)

            // Sends end of the match messages and checks if player wants to replay.
            // If player decides not to replay, the main loop is broken and the game ends
            if (!endGame(playerWon)) {
                break;
            }
        }
    }// main

    // Decides the amount of chips in the game, between 20 and 30.
    public static int initializeChips() {
        int chips = (int) (Math.random() * (30 - 20 + 1) + 20);
        return chips;
    }

    // Decides if the player starts drawing or the computer does.
    public static int initializeFirst() {
        int first = (int) (Math.random());
        return first;
    }

    // Simply prints the amount of chips on the table.
    public static void readChips(int chips) {
        System.out.println("Queden " + chips + " fitxes.");
        wait(500);
    }

    // Does the act of the computer drawing a random amount of chips (1 or 2).
    public static int compDraw(int chips) {
        int draw = 0;
        // If only 1 chip is left, computer draws only 1 and leaves the notification for
        // endGame()
        if (chips != 1) {
            draw = (int) (Math.random() * (2 - 1 + 1) + 1);
            if (draw == 1) {
                System.out.println("L'ordinador agafa " + draw + " fitxa.");
                wait(500);
                System.out.println("");
            } else {
                System.out.println("L'ordinador agafa " + draw + " fitxes.");
                wait(500);
                System.out.println("");
            }
        } else
            draw = 1;

        return draw;
    }

    // Makes player draw chips.
    public static int playerDraw() {
        int draw = 0;
        String drawStr;
        boolean format = false;
        Scanner ent = new Scanner(System.in);

        // Lets the method restart in case of exception or drawing an incorrect
        // amount of chips, loop is broken when a correct amount is submitted.
        while (!format || draw > 2 || draw <= 0) {
            System.out.println("Quantes fitxes agafes (1 or 2)?");
            drawStr = ent.nextLine();
            wait(500);
            System.out.println("");
            try {
                draw = Integer.parseInt(drawStr);
                // If an unwanted amount of chips is entered.
                if (draw > 2 || draw <= 0) {
                    System.out.println("Escull 1 o 2 fitxes.");
                    format = false;
                } else
                    format = true;
            }
            // Exception caused by submitting a non-int input.
            catch (NumberFormatException ex) {
                System.out.println("Format no acceptat.");
                format = false;
            }
        }
        ent.close();
        return draw;
    }

    // Prints end of the match messages and checks if the player wants to play
    // again.
    public static boolean endGame(boolean playerWon) {
        boolean restart = true;
        int looper = 0;
        String input;
        Scanner ent = new Scanner(System.in);
        if (playerWon)
            System.out.println("Has guanyat!");
        else
            System.out.println("Has perdut.");
        wait(500);
        System.out.println("");
        System.out.println("Vols tornar a jugar? (Y/N)");
        input = ent.nextLine();
        System.out.println("");
        while (looper == 0) {
            if (input.equals("Y") || input.equals("y")) {
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
                System.out.println("Resposta no vàlida, respon 'y' o 'n'.");
                input = ent.nextLine();
                System.out.println("");
            }
        }
        System.out.println("");
        ent.close();
        return restart;
    }

    // Waits ms milliseconds before sending the next sentence.
    public static void wait(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}// class
