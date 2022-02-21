package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;

import java.awt.*;

public class Cofre extends AbstractActor {
    //atributs de la classe
    private String sprite = "chest.png";
    private Image image;
    private final int amplada = 100;
    private final int alcada = 100;
    public Cofre(){
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
            if (arqueologa.getHasKey()){
                this.setPosicio(-250, -250);
            }
            else{
                arqueologa.setTocantCofreSenseClau(true);
            }
            
        }
    }
    public void render(Graphics2D g) {
        //Com dibuixar a la pantalla principal
        g.drawImage(image, getX(), getY(), observer);
    }
}