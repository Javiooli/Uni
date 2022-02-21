package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;

import java.awt.*;

public class Palanca extends AbstractActor {
    //atributs de la classe
    private String sprite = "lever0.png";
    private Image image;
    private final int amplada = 32;
    private final int alcada = 32;
    public Palanca(){
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
            image = Util.carregarImatge("lever1.png");
            arqueologa.setPalanca(true);
            
        }
    }
    public void render(Graphics2D g) {
        //Com dibuixar a la pantalla principal
        g.drawImage(image, getX(), getY(), observer);
    }
}