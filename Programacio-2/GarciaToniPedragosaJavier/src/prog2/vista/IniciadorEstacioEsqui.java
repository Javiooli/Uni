/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prog2.vista;

/**
 *
 * @author ricardo.marques
 */
public class IniciadorEstacioEsqui {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Definim el nom i les condicions meteorologiques inicials a l'estació
        String nomEstacio      = "Vall2000";
        int velocitatVentNord  = 10;
        String visibilitatNord = "Dolenta";
        int velocitatVentSud   = 10;
        String visibilitatSud  = "Dolenta";

        // Creem un objecte de la vista i li passem el nom i condicions meteorologiques inicials a l'estació
        VistaEstacioEsqui vistaEstacioVall2000 = new VistaEstacioEsqui(nomEstacio, velocitatVentNord, visibilitatNord,
                                                                                   velocitatVentSud,  visibilitatSud);
     
        // Inicialitzem l'execució de la vista
        vistaEstacioVall2000.gestioEstacio();
    }
    
}
