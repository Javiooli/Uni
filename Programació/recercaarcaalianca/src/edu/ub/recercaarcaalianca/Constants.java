package edu.ub.recercaarcaalianca;

import java.awt.event.KeyEvent;

/**
 * Constants del programa utilitzades frequentment.
 *
 * @author ub.edu
 */
public interface Constants {

    /**
     * Numero de frames per segon (50).
     */
    int FRAMES_PER_SEGON = 50;

    int AMPLADA_FINESTRA =  800;
    int ALCADA_FINESTRA =  576;

    char SIMBOL_PORTA = 'P';
    char SIMBOL_MUR = 'M';
    char SIMBOL_TERRA = '_';
    char SIMBOL_PALANCA = 'E';
    char SIMBOL_COL1 = 'C';
    char SIMBOL_COL2 = 'V';
    char SIMBOL_COL3 = 'B';
    char SIMBOL_COL4 = 'N';

    short DIRECCIO_NORD = 1;
    short DIRECCIO_SUD = 2;
    short DIRECCIO_EST = 3;
    short DIRECCIO_OEST = 4;

    int KEY_UP = KeyEvent.VK_UP;
    int KEY_DOWN = KeyEvent.VK_DOWN;
    int KEY_LEFT = KeyEvent.VK_LEFT;
    int KEY_RIGHT = KeyEvent.VK_RIGHT;

    int NUM_CELES_HORIZONTALS = 25;
    int NUM_CELES_VERTICALS = 17;
    int AMPLADA_CELA = 32;
    int ALCADA_CELA = 32;

    int ESTAT_INACTIU = 0;
    int ESTAT_ACTIU = 1;
    int QUANTITAT_DIAMANTS_VERMELLS_TARONJA = 2;
    int QUANTITAT_DIAMANTS_VERD_BLAU = 3;

    int QUANTITAT_ARMES = 20;

    enum ColorDiamant {
        VERMELL(0,15),
        BLAU(1,10),
        VERD(2,5),
        GROC(3,5),
        TARONJA(4,5);
        private final int energia;
        private final int colorNum;

        ColorDiamant(int color, int energia){
            this.colorNum = color;
            this.energia = energia;
        }
        public int getEnergia() {
            return this.energia;
        }
        public int getColorNum(){
            return this.colorNum;
        }
    }

    


    

}
