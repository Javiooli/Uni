package edu.ub.recercaarcaalianca;

import edu.ub.recercaarcaalianca.actors.*;

import static edu.ub.recercaarcaalianca.Constants.*;

/**
 * Demo.
 */
public class Practica {

    private final Joc joc;
    private final Temple temple;
    private final Arqueologa cindy_jones;

    private Practica() {

        temple = new Temple(2, 4);
        temple.addCambra(0, 0, crearCambra0Nivell0());
        temple.addCambra(0, 1, crearCambra1Nivell0());
        temple.addCambra(0, 2, crearCambra2Nivell0());
        temple.addCambra(0, 3, crearCambra3Nivell0());
        temple.addCambra(1, 0, crearCambra0Nivell1());
        temple.addCambra(1, 1, crearCambra1Nivell1());
        temple.addCambra(1, 2, crearCambra2Nivell1());
        temple.addCambra(1, 3, crearCambra3Nivell1());

        cindy_jones = new Arqueologa();
        Cambra cam = temple.getCambra(0, 0);
        int[] p = cam.getPosicioCela(10, 10);
        cindy_jones.setPosicioInicial(p[0], p[1]);

        distribuirDiamants();
        distribuirSoldats();
        distribuirArmes();
        colocarObjectes();
        

        //initialitzacio del joc
        joc = new Joc(temple, cindy_jones);
        GuiJoc gui = new GuiJoc(joc);
    }

    /**
     * Principal.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Practica();
    }

    private Cambra crearCambra0Nivell0() {
        Cambra cam = Util.carregarCambra("c0_0.txt");

        Porta porta = cam.getPorta(14, 24);
        porta.setNumNivellDesti(0);
        porta.setNumCambraDesti(1);
        porta.setPosicioCambraDesti(cam.getPosicioCela(1, 1));

        porta = cam.getPorta(0, 10);
        porta.setNumNivellDesti(0);
        porta.setNumCambraDesti(2);
        porta.setPosicioCambraDesti(cam.getPosicioCela(14, 10));

        return cam;
    }

    private Cambra crearCambra1Nivell0() {
        Cambra h = Util.carregarCambra("c0_1.txt");

        Porta porta = h.getPorta(1, 0);
        porta.setNumNivellDesti(0);
        porta.setNumCambraDesti(0);
        porta.setPosicioCambraDesti(h.getPosicioCela(13, 22));


        return h;
    }

    private Cambra crearCambra2Nivell0() {
        Cambra h = Util.carregarCambra("c0_2.txt");
        Porta porta = h.getPorta(16, 10);
        porta.setNumNivellDesti(0);
        porta.setNumCambraDesti(0);
        porta.setPosicioCambraDesti(h.getPosicioCela(1, 10));

        porta = h.getPorta(0, 11);
        porta.setNumNivellDesti(0);
        porta.setNumCambraDesti(3);
        porta.setPosicioCambraDesti(h.getPosicioCela(11, 6));

        return h;
    }

    private Cambra crearCambra3Nivell0() {
        Cambra h = Util.carregarCambra("c0_3.txt");
        Porta porta = h.getPorta(13, 6);
        porta.setNumNivellDesti(0);
        porta.setNumCambraDesti(2);
        porta.setPosicioCambraDesti(h.getPosicioCela(1, 11));

        porta = h.getPorta(0, 6);
        porta.setNumNivellDesti(1);
        porta.setNumCambraDesti(0);
        porta.setPosicioCambraDesti(h.getPosicioCela(14, 4));

        return h;
    }

    private Cambra crearCambra0Nivell1() {
        Cambra cam = Util.carregarCambra("c1_0.txt");

        Porta porta = cam.getPorta(6, 0);
        porta.setNumNivellDesti(1);
        porta.setNumCambraDesti(1);
        porta.setPosicioCambraDesti(cam.getPosicioCela(13, 22));

        porta = cam.getPorta(16, 23);
        porta.setNumNivellDesti(1);
        porta.setNumCambraDesti(2);
        porta.setPosicioCambraDesti(cam.getPosicioCela(1, 3));

        return cam;
    }

    private Cambra crearCambra1Nivell1() {
        Cambra h = Util.carregarCambra("c1_1.txt");

        Porta porta = h.getPorta(14, 24);
        porta.setNumNivellDesti(1);
        porta.setNumCambraDesti(0);
        porta.setPosicioCambraDesti(h.getPosicioCela(6, 1));

        return h;
    }

    private Cambra crearCambra2Nivell1() {
        Cambra h = Util.carregarCambra("c1_2.txt");
        Porta porta = h.getPorta(0, 3);
        porta.setNumNivellDesti(1);
        porta.setNumCambraDesti(0);
        porta.setPosicioCambraDesti(h.getPosicioCela(14, 22));

        porta = h.getPorta(0, 22);
        porta.setNumNivellDesti(1);
        porta.setNumCambraDesti(3);
        porta.setPosicioCambraDesti(h.getPosicioCela(11, 6));

        return h;
    }

    private Cambra crearCambra3Nivell1() {
        Cambra h = Util.carregarCambra("c1_3.txt");
        Porta porta = h.getPorta(13, 6);
        porta.setNumNivellDesti(1);
        porta.setNumCambraDesti(2);
        porta.setPosicioCambraDesti(h.getPosicioCela(1, 22));

        return h;
    }

      
    private Cambra getRandCambra() {
        int nivell;
        int numCambra;
        Cambra cam;
        nivell = (int) (Math.random() * temple.getNumNivells());
        numCambra = (int) (Math.random() * temple.getNumCambres(nivell));
        cam = temple.getCambra(nivell, numCambra);
        return cam;
    }

    private void distribuirSoldats() {
        int [] num = {1,3,6}; //nombre de soldats a distribuir de cada tipus
        int numCambra = 0;
        boolean escala = true;
         
        for (int i = 0; i < Soldat.TipusSoldat.values().length ; i++) {
            for(int k = 0; k < num[i] ; k++){
                escala = true;
                int nivell = (int) (Math.random() * temple.getNumNivells());
                while (escala) {
                    numCambra = (int) (Math.random() * temple.getNumCambres(nivell));
                    if (nivell == 1 && numCambra == 2){}
                    else if (numCambra != 3 ) escala = false;
                }
                Cambra cam = temple.getCambra(nivell, numCambra);
                int[] cela = obtenirCelaLliure(cam);
                Soldat s = new Soldat(Soldat.TipusSoldat.values()[i]);  

                int[] posicio = cam.getPosicioCela(cela[0], cela[1]);
                s.setPosicioInicial(posicio[0], posicio[1]);
                cam.addActor(s);
            }
        }

    }

    private void distribuirDiamants() {
        int [] num = {2,3,3,3,2}; //nombre de diamants a distribuir de cada tipus
        int numCambra = 0;
        boolean escala = true;
         
        for (int i = 0; i < Constants.ColorDiamant.values().length ; i++) {
            for(int k = 0; k < num[i] ; k++){
                escala = true;
                int nivell = (int) (Math.random() * temple.getNumNivells());
                while (escala) {
                    numCambra = (int) (Math.random() * temple.getNumCambres(nivell));
                    if (numCambra != 3) escala = false;
                }
                Cambra cam = temple.getCambra(nivell, numCambra);
                int[] cela = obtenirCelaLliure(cam);
                Diamant d = new Diamant(Constants.ColorDiamant.values()[i]);

                int[] posicio = cam.getPosicioCela(cela[0], cela[1]);
                d.setPosicioInicial(posicio[0], posicio[1]);
                cam.addActor(d);
            }
        }

    }

    private void distribuirArmes() {
        int [] num = {2, 8, 10}; //nombre d'armes a distribuir de cada tipus
        int numCambra = 0;
        boolean escala = true;
         
        for (int i = 0; i < Arma.TipusArma.values().length ; i++) {
            for(int k = 0; k < num[i] ; k++){
                escala = true;
                int nivell = (int) (Math.random() * temple.getNumNivells());
                while (escala) {
                    numCambra = (int) (Math.random() * temple.getNumCambres(nivell));
                    if (numCambra != 3) escala = false;
                }
                Cambra cam = temple.getCambra(nivell, numCambra);
                int[] cela = obtenirCelaLliure(cam);
                Arma a = new Arma(Arma.TipusArma.values()[i]);

                int[] posicio = cam.getPosicioCela(cela[0], cela[1]);
                a.setPosicioInicial(posicio[0], posicio[1]);
                cam.addActor(a);
            }
        }

    }

    private void colocarObjectes() { //col·loca l'arca, la porta de l'arca, la clau, el cofre, la palanca...
        Cambra cam = temple.getCambra(1, 2);
        int[] cela = {7, 12};
        
        Arca a = new Arca();
        int[] posicio = cam.getPosicioCela(cela[0], cela[1]);
        a.setPosicioInicial(posicio[0], posicio[1]);
        cam.addActor(a);

        cela[0] = 11;
        Porto p = new Porto();
        posicio = cam.getPosicioCela(cela[0], cela[1]);
        p.setPosicioInicial(posicio[0], posicio[1]);
        cam.addActor(p);

        cela[1] = 11;
        MurFals m1 = new MurFals();
        posicio = cam.getPosicioCela(cela[0], cela[1]);
        m1.setPosicioInicial(posicio[0], posicio[1]);
        cam.addActor(m1);

        cela[1] = 13;
        MurFals m2 = new MurFals();
        posicio = cam.getPosicioCela(cela[0], cela[1]);
        m2.setPosicioInicial(posicio[0], posicio[1]);
        cam.addActor(m2);

        cam = temple.getCambra(1, 3);

        cela[0] = 6;
        cela[1] = 6;
        Clau c = new Clau();
        posicio = cam.getPosicioCela(cela[0], cela[1]);
        c.setPosicioInicial(posicio[0], posicio[1]);
        cam.addActor(c);

        cam = temple.getCambra(1, 1);

        cela[0] = 15;
        cela[1] = 2;
        Palanca pal = new Palanca();
        posicio = cam.getPosicioCela(cela[0], cela[1]);
        pal.setPosicioInicial(posicio[0], posicio[1]);
        cam.addActor(pal);

        cela[0] = 13;
        cela[1] = 1;
        Cofre che = new Cofre();
        posicio = cam.getPosicioCela(cela[0], cela[1]);
        che.setPosicioInicial(posicio[0], posicio[1]);
        cam.addActor(che);
    }
  

    private int[] obtenirCelaLliure(Cambra cambra) {
        int fila = 0;
        int col = 0;
        int cela[];
        boolean trobada = false;
        boolean terra;

        while (!trobada) {
            terra = false;
            while (!terra) {
                fila = (int) Math.max(0, (Math.random() * NUM_CELES_VERTICALS - 2));
                col = (int) Math.max(0, (Math.random() * NUM_CELES_HORIZONTALS - 2));
                terra = (cambra.getValor(fila, col) == SIMBOL_TERRA &&
                        cambra.getValor(fila + 1, col) == SIMBOL_TERRA &&
                        cambra.getValor(fila, col + 1) == SIMBOL_TERRA &&
                        cambra.getValor(fila + 1, col + 1) == SIMBOL_TERRA);
            }

            // comprovar que cap actor esta dins la cela
            Actor[] actors = cambra.getActorsAsArray();
            int i = 0;
            boolean lliure = true;
            while (i < actors.length && lliure) {
                cela = cambra.getCela(actors[i].getPosicioInicial()[0],
                        actors[i].getPosicioInicial()[1]);
                lliure = fila != cela[0] || col != cela[1];
                i++;
            }

            //comprovar que cindy_jones no esta dins la cela
            if (lliure) {
                cela = cambra.getCela(cindy_jones.getPosicioInicial()[0],
                        cindy_jones.getPosicioInicial()[1]);
                lliure = fila != cela[0] || col != cela[1];
            }

            trobada = lliure;
        }
        return new int[]{fila, col};
    }

}