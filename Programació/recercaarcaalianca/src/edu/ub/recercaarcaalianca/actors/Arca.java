package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;

import java.awt.*;

public class Arca extends AbstractActor {
    //atributs de la classe
    private String sprite = "arca32.png";
    private Image image;
    private final int amplada = 48;
    private final int alcada = 48;
    public Arca(){
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
            if (arqueologa.getTeTotsElsDiamants() && arqueologa.getVida() > 29){
                arqueologa.setHaGuanyat(true);
                this.setPosicio(-250, -250);
            }
            
        }
    }
    public void render(Graphics2D g) {
        //Com dibuixar a la pantalla principal
        g.drawImage(image, getX(), getY(), observer);
    }
}