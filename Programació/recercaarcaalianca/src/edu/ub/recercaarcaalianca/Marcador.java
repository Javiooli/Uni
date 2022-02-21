package edu.ub.recercaarcaalianca;

import edu.ub.recercaarcaalianca.actors.Arqueologa;

import java.awt.*;

/**
 * Representa el marcador de vida, i si ha trobat els diamants l'arqueologa (no implementat)
 *
 * @author ub.edu
 */
public class Marcador {

    private final int y;
    private int delay = 0;
    private final String fontName = "Dialog";

    private final Font fontNormal = new Font(fontName, Font.PLAIN, 12);
    private final Font fontPetita = new Font(fontName, Font.BOLD, 10);
    private final Font fontGran = new Font(fontName, Font.BOLD, 14);

    public Marcador() {
        y = Constants.ALCADA_FINESTRA - 30;
    }

    /**
     * Dibuixa el seu contingut.
     *
     * @param ctx context del joc
     * @param g   grafics de sortida
     */
    public void render(Context ctx, Graphics2D g) {
        pintarFons(g);
        pintarNivellForca(ctx, g);
        pintarCambra(ctx, g);
        pintarDiamants(ctx, g);
        if (ctx.getEnemicInmovilitzat() && this.delay < 30){
            pintarTimer(ctx, g);
            this.delay++;
        }
        else if (this.delay == 30){
            ctx.setEnemicInmovilitzat(false);
            this.delay = 0;
        }
    }


    // private methods *********************************************************

    private void pintarFons(Graphics2D g) {
        g.setPaint(new GradientPaint(
                Constants.AMPLADA_FINESTRA / 2.f, y, Color.DARK_GRAY,
                Constants.AMPLADA_FINESTRA / 2.f, y + 40.f, Color.BLACK));

        Rectangle r = new Rectangle(0, y,
                Constants.AMPLADA_FINESTRA, Constants.ALCADA_FINESTRA - y);
        g.fill(r);
    }

    private void pintarNivellForca(Context ctx, Graphics2D g) {
        g.setPaint(Color.white);
        g.setFont(fontNormal);
        g.drawString("Vida: ", 10, y + 15);

        Rectangle r = new Rectangle(40, y + 5, 100, 12);
        g.setColor(Color.BLACK);
        g.fill(r);

        Rectangle t = new Rectangle();
        Arqueologa cindy = (Arqueologa) ctx.getJoc().getArqueologa(); 
       
       // TODO  alumnes, cal fer les funcions de cindy i descomentar el codi
        t.setRect(r.getX(), r.getY(), cindy.getVida(), (int) r.getHeight());  
        int nivell = (int) (cindy.getVida());
        if (nivell < 50) g.setColor(Color.RED);
        else if (nivell < 75) g.setColor(Color.ORANGE);
        else g.setColor(Color.BLUE);
        g.fill(t);
        g.setColor(Color.white);
        g.draw(r);
        g.setFont(fontPetita);

        g.drawString(nivell + "%", 60, y + 15);
    }


    private void pintarCambra(Context ctx, Graphics2D g) {
        g.setColor(Color.white);
        g.setFont(fontNormal);
        int Nivell = ctx.getJoc().getTemple().getNivell() + 1;
        int cambra = ctx.getJoc().getTemple().getNumCambra() + 1;
        g.drawString("Nivell: " + Nivell + " - Cambra: " + cambra, 200, y + 15);
    }

    private void pintarDiamants(Context ctx, Graphics2D g) {
        g.setColor(Color.red);
        g.setFont(fontNormal);
        Arqueologa arqueologa = (Arqueologa) ctx.getJoc().getArqueologa();
        // TODO alumnes de la funció corresponent i descomentar el codi
        if (arqueologa.getDiamants()[0]) g.drawString("V", 350, y +15);
        g.setColor(Color.blue);
        if (arqueologa.getDiamants()[1]) g.drawString("B", 360, y +15); 
        g.setColor(Color.green);
        if (arqueologa.getDiamants()[2]) g.drawString("V", 370, y +15); 
        g.setColor(Color.yellow);
        if (arqueologa.getDiamants()[3]) g.drawString("G", 380, y +15); 
        g.setColor(Color.orange);
        if (arqueologa.getDiamants()[4]) g.drawString("T", 390, y +15); 
        if (arqueologa.getTeTotsElsDiamants()) g.setColor(Color.green);
        else g.setColor(Color.white);
        g.drawString("Ha trobat tots els diamants? " + arqueologa.haTrobatElsDiamants(), 620, y +15); 
    }

    private void pintarTimer(Context ctx, Graphics2D g) {
        g.setColor(Color.CYAN);
        g.setFont(fontGran);
        Arqueologa arqueologa = (Arqueologa) ctx.getJoc().getArqueologa();
        g.drawString("Enemic derrotat!", arqueologa.getX() - 20, arqueologa.getY() - 25);
    }
}
