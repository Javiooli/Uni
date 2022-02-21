package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;

import java.awt.image.BufferedImage;
import java.awt.*;

import java.util.*;

/**                                 
 * Representa la nostra Arqueologa.
 */
public class Arqueologa extends AbstractActor {
    public static final int AMPLADA = 40;
    public static final int ALCADA = 56;
    private static final int FRAMES_CHANGE = 1;
    private static final float DANY_PER_SEGON = 1;
    private static final int TEMPS_INVULNERABILITAT = 60;
    int lastImage;
    private Image[] imatges;
    private boolean bPosChanged;
    private int nFramesToChange = FRAMES_CHANGE;
    private int direccio = -1;
   
    // ******************************************
    // TODO alumnes: afegir a sota ATRIBUTS PROPIS DEL PROBLEMA A SOLUCIONAR
    // ******************************************

    private float vida = 100;
    private int contacte = 0; //si és 1, contacte amb soldat
                              // si és 2, contacte amb diamant
    private int energia = 0;
    private int tempsSenseContacte = TEMPS_INVULNERABILITAT;
    private int dmg = 0;
    private static final int DIAMANTS_TOTALS = 5;
    private boolean[] diamantsTrobats = {false, false, false, false, false};
    private boolean tocantPorto = false;
    private boolean hasKey = false;
    private boolean tocantCofreSenseClau = false;
    private boolean palanca = false;
    private boolean teTotsElsDiamants = false;
    private boolean haGuanyat = false;
    //guarda les armes que va trobant, màxim 10
    private Arma[] armas = new Arma[10];
    //nombre actual d’armes, quan utilitza un arma aquest valor es decrementa
    private int countArmas;
    //nombre de soldats que ha guanyat
    private int soldatsGuanyats;
   
    /**
     * Constructor.
     */
    public Arqueologa() {
        init();      
    }

    public void inicialitzar() {
        super.inicialitzar();
        reiniciarStats();          
    }  
  
   
    /**
     * Obte un rectangle ambs el limits de l'Arqueologa.
     *
     * @return els limits, x,y, amplada i alcada
     */
    public Rectangle getLimits() {
        return new Rectangle(x, y-15, AMPLADA, ALCADA);
    }

    public void actualitzar(Context ctx) {
        calcularNivellVida(ctx);
        int desX = 0;
        int desY = 0;
        bPosChanged = false;
        if (ctx.isKeyPressed(Context.KEY_UP)) {
            desY = -1;
            direccio = Constants.DIRECCIO_NORD;
            bPosChanged = true;
        }
        else if (ctx.isKeyPressed(Context.KEY_DOWN)) {
            desY = 1;
            direccio = Constants.DIRECCIO_SUD;
            bPosChanged = true;
        }
        else if (ctx.isKeyPressed(Context.KEY_LEFT)) {
            desX = -1;
            direccio = Constants.DIRECCIO_OEST;
            bPosChanged = true;
        }
        else if (ctx.isKeyPressed(Context.KEY_RIGHT)) {
            desX = 1;
            direccio = Constants.DIRECCIO_EST;
            bPosChanged = true;
        }

        int deltaX = 8;
        int auxX = x + deltaX * desX;
        int deltaY = 8;
        int auxY = y + deltaY * desY;

        Porta porta = testPorta(ctx, auxX, auxY);
        if (porta != null && porta.getNumNivellDesti() != -1 &&
                porta.getNumCambraDesti() != -1) {

            Temple temple = ctx.getJoc().getTemple();
            temple.setNivell(porta.getNumNivellDesti());
            temple.setNumCambra(porta.getNumCambraDesti());
            int[] posicio = porta.getPosicioCambraDesti();
            if (posicio != null) {
                x = posicio[0];
                y = posicio[1];
            }
        }
        else if (tocantPorto) y += 1;
        else if (tocantCofreSenseClau) y -= 1;
        else if (!testMur(ctx, auxX, auxY)) {
            x = auxX;
            y = auxY;
        }
        contacte = 0;
        energia = 0;
        dmg = 0;
        tocantPorto = false;
        tocantCofreSenseClau = false;
        if (tempsSenseContacte < TEMPS_INVULNERABILITAT) tempsSenseContacte++;
        teTots();
    }

    public void tractarColisio(Colisio colisio) {
    }

    public void render(Graphics2D g) {
        int nImg = lastImage;

        if (bPosChanged) {
        	nFramesToChange--;
        	if (nFramesToChange == 0) {
        		nFramesToChange = FRAMES_CHANGE;
        	}
        }
        
        if (direccio == Constants.DIRECCIO_EST) {
        	nImg = 1;
        } else if(direccio == Constants.DIRECCIO_OEST) {
        	nImg = 0;
        }

        Image image = imatges[nImg];
        g.drawImage(image, x, y, observer);
        lastImage = nImg;
    }
    
    public void randomTeleport(Context context) {
        Temple temple = context.getJoc().getTemple();
        Actor arqueologa = context.getJoc().getArqueologa();
        Cambra novaCambra;
        Cambra h = context.getCambra();
        Cambra[] cambres = temple.getCambres(temple.getNivell());

        if (cambres.length > 1) {
            boolean trobada = false;
            int fila = 0;
            int col = 0;
            int numCambra = 0;
            int[] posicio = null;

            while (!trobada) {
                numCambra = (int) (Math.random() * cambres.length);
                while (h == cambres[numCambra]) {
                    numCambra = (int) (Math.random() * cambres.length);
                }
                novaCambra = temple.getCambra(temple.getNivell(), numCambra);
                boolean lliure = false;
                while (!lliure) {
                    fila = (int) Math.max(0, (Math.random() * Constants.NUM_CELES_VERTICALS - 2));
                    col = (int) Math.max(0, (Math.random() * Constants.NUM_CELES_HORIZONTALS - 2));
                    char c1 = novaCambra.getValor(fila, col);
                    char c2 = novaCambra.getValor(fila + 1, col);
                    char c3 = novaCambra.getValor(fila, col + 1);
                    char c4 = novaCambra.getValor(fila + 1, col + 1);
                    lliure = (c1 == Constants.SIMBOL_TERRA &&
                            c2 == Constants.SIMBOL_TERRA &&
                            c3 == Constants.SIMBOL_TERRA &&
                            c4 == Constants.SIMBOL_TERRA);

                    // comprovar que cap actor esta dins la cela
                    Actor[] actors = novaCambra.getActorsAsArray();
                    int i = 0;

                    int cela[];
                    while (i < actors.length && lliure) {
                        cela = novaCambra.getCela(actors[i].getPosicioInicial()[0],
                                actors[i].getPosicioInicial()[1]);
                        lliure = fila != cela[0] || col != cela[1];
                        i++;
                    }
                }

                posicio = novaCambra.getPosicioCela(fila, col);
                trobada = !testMur(context, posicio[0], posicio[1], arqueologa.getLimits());
            }
            temple.setNumCambra(numCambra);
            arqueologa.setPosicio(posicio[0], posicio[1]);
        }
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float v){
        this.vida = v;
    }

    public int getContacte(){
        return contacte;
    }

    public void setContacte (int c){
        this.contacte = c;
    }

    public void setEnergia(float e){
        this.energia = (int) e;
    }

    public void setDmg(float d){
        this.dmg = (int) d;
    }

    public void setTocantPorto(boolean a){
        this.tocantPorto = a;
    }

    public void setHasKey(boolean h){
        this.hasKey = h;
    }

    public boolean getHasKey(){
        return this.hasKey;
    }

    public void setTocantCofreSenseClau(boolean s){
        this.tocantCofreSenseClau = s;
    }

    public boolean getPalanca(){
        return this.palanca;
    }

    public void setPalanca(boolean we){
        this.palanca = we;
    }

    public void addDiamant(Diamant d){
        int i = d.getColor().getColorNum();
        this.diamantsTrobats[i] = true;
    }

    public boolean[] getDiamants(){
        return this.diamantsTrobats;
    }

    public boolean[] perdreDiamant(){
        boolean enTe = false;
        boolean elTe = false;
        Random r = new Random();
        for (int i = 0; i < DIAMANTS_TOTALS; i++){
            if (this.diamantsTrobats[i]){
                enTe = true;
            }
        }
        int rand = r.nextInt(2);
        int rand2 = r.nextInt(5);
        if (enTe && rand == 0){
            while (!elTe){
                if (this.diamantsTrobats[rand2]){
                    elTe = true;
                    this.diamantsTrobats[rand2] = false;
                }
                else {
                    rand2 = r.nextInt(5);
                }
            }
        }
        return this.diamantsTrobats;
    }

    public boolean getTeTotsElsDiamants(){
        return this.teTotsElsDiamants;
    }

    public String haTrobatElsDiamants(){
        if (this.teTotsElsDiamants) return "Si";
        else return "No";
    }

    public boolean getHaGuanyat(){
        return this.haGuanyat;
    }

    public void setHaGuanyat(boolean a){
        this.haGuanyat = a;
    }

    public int getDireccio(){
        return this.direccio;
    }

    public Arma[] getArmas(){
        return this.armas;
    }

    public void setArmas(Arma[] a){
        this.armas = a;
    }

    public void addArma(Arma a){
        boolean trobat = false;
        int i = 0;
        int iMenor = -1;
        /* Tipus d'esquema: Cerca
         * Inici seq.: primer element de l'array armas[] (armas[0])
         * Seg. elem.: element i+1 de l'array armas[] (armas[i])
         * Final seq.: últim element de l'array armas[] (armas[armas.length - 1])
         * Cond. cerca: element de l'array == null
        */
        while (!trobat && i < armas.length){
            if (armas[i] == null) trobat = true;
            else if (armas[i].getPotencia() < a.getPotencia()) {
                iMenor = i;
                i++;
            }
            else i++;
        }
        if (trobat){
            this.armas[i] = a;
            this.countArmas++;
        }
        else if (iMenor != -1){
            this.armas[iMenor] = a;
            this.countArmas++;
        }
    }

    public boolean haGuanyatSoldats(){
        boolean haGuanyatSoldats = false;

        if (soldatsGuanyats >= 5) haGuanyatSoldats = true;

        return haGuanyatSoldats;        
    }

    public Arma emprarArma(){
        boolean trobat = false;
            int i = 0;
            Arma armaUtilitzada = null;
        if (tempsSenseContacte == TEMPS_INVULNERABILITAT){
            //Comprovem que l'arqueologa té com a mínim una arma.
            /* Tipus d'esquema: Cerca
            * Inici seq.: primer element de l'array armas[] (armas[0])
            * Seg. elem.: element i+1 de l'array armas[] (armas[i])
            * Final seq.: últim element de l'array armas[] (armas[armas.length - 1])
            * Cond. cerca: element de l'array != null, és a dir, alguna arma.
            */
            while (!trobat && i < armas.length){
                if (armas[i] != null) trobat = true;
                i++;
            }
            //Si l'arqueologa té com a mínim una arma
            if (trobat){
                Random r = new Random();
                int rand = r.nextInt(10);
                Boolean armaSel = false;
                /* Tipus d'esquema: Cerca
                * Inici seq.: primer element de l'array armas[] (armas[0])
                * Seg. elem.: element i+1 de l'array armas[] (armas[i])
                * Final seq.: -
                * Cond. cerca: element de l'array != null, és a dir, alguna arma.
                */
                while (!armaSel){
                    if (this.armas[rand] != null) {
                        armaUtilitzada = armas[rand];
                        this.armas[rand] = null;
                        this.countArmas--;
                        armaSel = true;
                    }
                    else {
                        rand = r.nextInt(10);
                    }
                }

            }
        }
            return armaUtilitzada;
    }

    public void addSoldatsGuanyats(){
        this.soldatsGuanyats++;
    }

    public int getCountArmas(){
        return this.countArmas;
    }
    

    // *********************************************************
    // ****** private methods***********************************
    // *********************************************************

    private void init() {
        imatges = new Image[2];
        BufferedImage iTmp = Util.carregarImatge("cindy_low.png"); 
        imatges[0] = Util.flipImatgeHor(iTmp);
        imatges[1] = iTmp;
    }

    private void teTots(){
        boolean trobat = true;
        int i = 0;
        while (trobat && i < DIAMANTS_TOTALS){
            if (!this.diamantsTrobats[i]){
                trobat = false;
            }
            else i++;
        }
        this.teTotsElsDiamants = trobat;
    }

    private void calcularNivellVida(Context ctx) {
        long t = ctx.getTempsFrame();
        float dany = DANY_PER_SEGON * t / 1000.f;
        setVida(Math.max(0, getVida() - dany));
        switch (this.contacte){
            case (0): break;
            case (1): if (tempsSenseContacte == TEMPS_INVULNERABILITAT){
                        setVida(Math.max(0, getVida() - dmg));
                        diamantsTrobats = perdreDiamant();
                        tempsSenseContacte = 0;
                      }
                      break;
            case (2): setVida(Math.min(100, getVida() + energia));
            
        }
    }

    private void reiniciarStats(){
        this.vida = 100;
        this.haGuanyat = false;
        this.tocantPorto = false;
        this.hasKey = false;
        this.tocantCofreSenseClau = false;
        this.palanca = false;
        this.teTotsElsDiamants = false;
        this.haGuanyat = false;
        this.armas = new Arma[10];
        this.soldatsGuanyats = 0;
        for (int i = 0; i < DIAMANTS_TOTALS; i++){
            this.diamantsTrobats[i] = false;
        }
        
    }
    
}
