package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;

import java.awt.*;

public class Clau extends AbstractActor {
    //atributs de la classe
    private String sprite = "key.png";
    private Image image;
    private final int amplada = 40;
    private final int alcada = 22;
    public Clau(){
        image = Util.carregarImatge(sprite);
    }
    public void actualitzar(Context context) {
        // no fem res, es estatic (no se mou)
    }
    public Rectangle getLimits() {
        // es important per tractar les colisions des de la classe GuiJoc al metode actualizarJoc
        return new Rectangle(getX(), getY(), amplada, alcada);
    }
    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        if (actor instanceof Arqueologa) {
            Arqueologa arqueologa = (Arqueologa) actor;
            arqueologa.setHasKey(true);
            this.setPosicio(-50, -50);
        }
    }
    public void render(Graphics2D g) {
        //Com dibuixar a la pantalla principal
        g.drawImage(image, getX(), getY(), observer);
    }
}