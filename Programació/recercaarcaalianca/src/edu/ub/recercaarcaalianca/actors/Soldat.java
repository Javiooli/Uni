package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;
import edu.ub.recercaarcaalianca.actors.Arma.TipusArma;

import java.awt.*;
import java.awt.image.BufferedImage;

import static edu.ub.recercaarcaalianca.Constants.*;
import static edu.ub.recercaarcaalianca.Marcador.*;

import java.math.*;

/**
 * Soldats que vigilen el Temple, decrementen la vida de l'arqueologa
 */

public class Soldat extends AbstractActor {


    private static final int INCREMENT_POSX = 1;
    private static final int INCREMENT_POSY = 1;
    private static final int[][] DIRECCIONS = {
            {1, 0},   // EST
            {-1, 0},  // OEST
            {0, 1},   // SUD
            {0, -1}   // NORD
    };
    
    private static final Rectangle DIMENSIONS_ICONA = new Rectangle(0, 0, 40, 60);
    private Image[] icones;
    private final int[] direccio = DIRECCIONS[0];
    private int targetX;
    private int targetY;
    private boolean hasTarget;
    private boolean aturat;
    private boolean aturatNotificat = false;
    
    private float forca_soldat; 
    public static enum TipusSoldat{COMMANDANT,CAPITA,RAS};
    private TipusSoldat tipus;
    private int tempsSenseContacte = 60;

    private Arma arma;
        
    public Soldat(TipusSoldat tipus) {
        this.tipus = tipus;
       
        icones = new Image[2];
        BufferedImage iTmp = null; 
        
        switch (tipus){
            case COMMANDANT:
                iTmp = Util.carregarImatge("coronel.png");
                this.forca_soldat = 20;
                break;
            case CAPITA:
                iTmp = Util.carregarImatge("soldat.png");
                this.forca_soldat = 12;
                break;
            case RAS:
                iTmp = Util.carregarImatge("soldatras.png");
                this.forca_soldat = 8;
                break;
        }
        
        icones[0] = Util.flipImatgeHor(iTmp);
        icones[1] = iTmp;
    }

    public void inicialitzar() {
        super.inicialitzar();
        int i = (int) Math.random();
        if (i == 0) this.arma = new Arma(TipusArma.GRANADA);
        else this.arma = new Arma(TipusArma.PISTOLA);
        this.aturat = false;
    }

    /**
     * Sobreescriu el metode per canviar de direccio cada cop que xoca amb un
     * mur.
     *
     * @param context
     */
    public void actualitzar(Context context) {

        if (isAturat() && !aturatNotificat){
            context.setEnemicInmovilitzat(true);
            this.aturatNotificat = true;
            return;
        }
        else if (isAturat()) return;
        Rectangle limits = new Rectangle(getX(), getY(), DIMENSIONS_ICONA.width, DIMENSIONS_ICONA.height);

        //estableix el target si no el te o l'ha trobat
        if (!hasTarget || hasReachedTarget(limits, targetX, targetY)) {
            targetX = context.getJoc().getArqueologa().getPosicio()[0];
            targetY = context.getJoc().getArqueologa().getPosicio()[1];
            hasTarget = true;
        }

        int posX = getX();
        int posY = getY();

        //d'aquesta manera no es queden totalment parats quan no poden avancar en diagonal 
        int aleatori = (int) (Math.random() * 2);

        if (aleatori == 0) {
            if (getX() > targetX)
                posX -= INCREMENT_POSX;
            else if (getX() < targetX)
                posX += INCREMENT_POSX;
            else if (getY() > targetY)
                posY -= INCREMENT_POSY;
            else if (getY() < targetY)
                posY += INCREMENT_POSY;
        } else {
            if (getY() > targetY)
                posY -= INCREMENT_POSY;
            else if (getY() < targetY)
                posY += INCREMENT_POSY;
            else if (getX() > targetX)
                posX -= INCREMENT_POSX;
            else if (getX() < targetX)
                posX += INCREMENT_POSX;
        }


        Cambra h = context.getCambra();
        limits = new Rectangle(posX, posY, DIMENSIONS_ICONA.width, DIMENSIONS_ICONA.height);

        if (getXocaContraMurs(limits, h)) {
            hasTarget = false;
        } else if (!getXocaContraMurs(limits, h)) {
            setPosicio(posX, posY);
        }

        if (tempsSenseContacte < 30) tempsSenseContacte++;

    }

    public boolean hasReachedTarget(Rectangle limits, int targetX, int targetY) {
        return limits.getX() <= targetX && limits.getX() + limits.width >= targetX &&
                limits.getY() <= targetY && limits.getY() + limits.height >= targetY;
    }

    public Rectangle getLimits() {
        return new Rectangle(getX(), getY(), DIMENSIONS_ICONA.width, DIMENSIONS_ICONA.height);
    }

    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        // TODO alumnes, gestio de la colisio amb l'arqueologa amb el Soldat 
        if (actor instanceof Arqueologa) {
            Arqueologa arqueologa = (Arqueologa) actor;
            Arma armaEmprada = arqueologa.emprarArma();
            if (armaEmprada != null && armaEmprada.getPotencia() >= this.arma.getPotencia()) {
                setAturat(true);
                this.setPosicio(-250, -250);
                arqueologa.addSoldatsGuanyats();
            }
            else {
                arqueologa.setDmg(this.arma.getPotencia());
                arqueologa.setContacte(1);   
            }
        }
    }

    public void render(Graphics2D g) {
        int currentIcona = 0;
        g.drawImage(icones[currentIcona], getX(), getY(), observer);
    }

    public boolean isAturat() {
        return aturat;
    }

    public void setAturat(boolean aturat) {
        this.aturat = aturat;
    }
    
    /**
     * Obte el nivell de forca del soldat.
     *
     * @return un numero entre 0 i 100, 0 es mort.
     */
    public float getForca() {
        return forca_soldat;
    }
    
    /**
     * Estableix el nivell de forca.
     *
     * @param Forca un numero enter 0 i 100
     */
    public void setForca(float forc) {
        this.forca_soldat = Math.max(0, Math.min(100, forc));
    }
    
    
    // *********************************************************
    // ****** private methods***********************************
    // *********************************************************

    private boolean getXocaContraMurs(Rectangle limits, Cambra h) {
        boolean xoca = false;
        int[][] celes = h.getCelesIntersectades(limits);
        int i = 0;
        while (i < celes.length && !xoca) {
            if (h.getValor(celes[i][0], celes[i][1]) == SIMBOL_MUR || h.getValor(celes[i][0], celes[i][1]) == Constants.SIMBOL_COL1
            || h.getValor(celes[i][0], celes[i][1]) == Constants.SIMBOL_COL2 || h.getValor(celes[i][0], celes[i][1]) == Constants.SIMBOL_COL3
            || h.getValor(celes[i][0], celes[i][1]) == Constants.SIMBOL_COL4)
                xoca = true;
            i++;
        }
        return xoca;
    }

 
}
