package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;
import edu.ub.recercaarcaalianca.Constants.ColorDiamant;

import java.awt.*;

public class Diamant extends AbstractActor {
    //atributs de la classe
    private String sprite = "Diamond.png";
    private Image image;
    private final int amplada = 32;
    private final int alcada = 27;
    private Constants.ColorDiamant color;
    public Diamant(Constants.ColorDiamant colorDiamant){
        switch (colorDiamant){
            case VERMELL:
                image = Util.carregarImatge(sprite, new Color(255, 0, 0, 0));
                setColor(ColorDiamant.VERMELL);
                break;
            case BLAU:
                image = Util.carregarImatge(sprite, new Color(0, 0, 255, 0));
                setColor(ColorDiamant.BLAU);
                break;
            case VERD:
                image = Util.carregarImatge(sprite, new Color(0, 255, 0, 0));
                setColor(ColorDiamant.VERD);
                break;
            case GROC:
                image = Util.carregarImatge(sprite, new Color(255, 255, 0, 0));
                setColor(ColorDiamant.GROC);
                break;
            case TARONJA:
                image = Util.carregarImatge(sprite, new Color(255, 128, 64, 0));
                setColor(ColorDiamant.TARONJA);
                break;
        }
    }
    public Constants.ColorDiamant getColor() {
        return color;
    }
    public void setColor(Constants.ColorDiamant color){
        this.color = color;
    }
    public void actualitzar(Context context) {
        // no fem res, es estatic (no se mou)
    }
    public Rectangle getLimits() {
        // es important per tractar les colisions des de la classe GuiJoc al metode actualizarJoc
        return new Rectangle(getX(), getY() - 20, amplada, alcada);
    }
    public void tractarColisio(Colisio colisio) {
        Actor actor = colisio.getActor();
        boolean jaElTe = false;
        if (actor instanceof Arqueologa) {
            Arqueologa arqueologa = (Arqueologa) actor;
            for (int i = 0; i < 5; i++){
                if (!arqueologa.getDiamants()[i]){}
                else if (i == this.getColor().getColorNum()){
                    jaElTe = true;
                }
            }
            if (!jaElTe){
                arqueologa.setEnergia(this.color.getEnergia());
                arqueologa.setContacte(2);
                arqueologa.addDiamant(this);
                this.setPosicio(-50, -50);
            } else {
                arqueologa.setEnergia(this.color.getEnergia());
                arqueologa.setContacte(2);
                this.setPosicio(-50, -50);
            }
            
        }
    }
    public void render(Graphics2D g) {
        //Com dibuixar a la pantalla principal
        g.drawImage(image, getX(), getY(), observer);
    }
}