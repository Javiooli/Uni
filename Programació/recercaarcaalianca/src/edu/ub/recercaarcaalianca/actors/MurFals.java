package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;

import java.awt.*;

public class MurFals extends AbstractActor {
    //atributs de la classe
    private String sprite = "mur32.png";
    private Image image;
    private final int amplada = 32;
    private final int alcada = 32;
    public MurFals(){
        image = Util.carregarImatge(sprite);
    }
    public void actualitzar(Context context) {
        Arqueologa arqueologa = (Arqueologa) context.getJoc().getArqueologa();
        if (arqueologa.getPalanca()) desapareixer();
    }
    public Rectangle getLimits() {
        // es important per tractar les colisions des de la classe GuiJoc al metode actualizarJoc
        return new Rectangle(getX(), getY(), amplada, alcada);
    }
    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        if (actor instanceof Arqueologa) {
            Arqueologa arqueologa = (Arqueologa) actor;
            arqueologa.setTocantPorto(true);
        }
    }

    public void desapareixer(){
        this.setPosicio(-250, -250);
    }

    public void render(Graphics2D g) {
        //Com dibuixar a la pantalla principal
        g.drawImage(image, getX(), getY(), observer);
    }
}