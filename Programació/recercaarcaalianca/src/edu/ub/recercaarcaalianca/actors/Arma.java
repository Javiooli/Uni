package edu.ub.recercaarcaalianca.actors;

import edu.ub.recercaarcaalianca.*;

import java.awt.*;

import static edu.ub.recercaarcaalianca.Constants.*;

/**
 * El Arma amb un tipus, potencia, i quantitat que es distribuix pel temple
 */
public class Arma extends AbstractActor {
    public enum TipusArma {
        FUET(1.f,5.f, "fuet.png", 2),
        PISTOLA(6.f, 10.f, "pistola.png", 8),
        GRANADA(11.f, 20.f, "granada.png", 10);
        
        private final float minPotencia;
        private final float maxPotencia;
        private final String imageName;
        private final int quantitat;
        
        private TipusArma(float minPotencia, float maxPotencia, String imageName, int quantitat) {
            this.minPotencia = minPotencia;
            this.maxPotencia = maxPotencia;
            this.imageName = imageName;
            this.quantitat = quantitat;
        }
        
        public int getQuantitat() {
            return quantitat;
        }
        
        public float novaPotencia() {
            return (float) Math.random() * (maxPotencia - minPotencia) + minPotencia;
        }
        
        public String getImageName() {
            return imageName;
        }
    }

    private Image image;
    private final int amplada = 20;
    private final int alcada = 37;
    private final TipusArma tipusArma;
    private final float potencia;


    /**
     * <code>Diamond.png</code> Cada color  
     * aporta un guany proporcional en la vida del jugador.
     */
    public Arma(TipusArma tipusArma) {

        this.tipusArma = tipusArma;
        this.potencia = tipusArma.novaPotencia();
        this.image = Util.carregarImatge(tipusArma.getImageName());
        
        setEstat(ESTAT_ACTIU);
    }

    public float getPotencia() {
        return potencia;
    }
    
    public Rectangle getLimits() {
        // es important per tractar les colisions des de la classe GuiJoc al metode actualizarJoc
        return new Rectangle(getX(), getY(), amplada, alcada);
    }

    public void tractarColisio(Colisio colisio) {
        // a cada iteracio del joc es crida a actualizar desde la classe GuiJoc al metode actualizarJoc
        Actor actor = colisio.getActor();
        // Cal explicar el que es instanceof
        // l'instanceof comprova si la variable actor és una instància de la classe Arqueologa.
        if (actor instanceof Arqueologa) {
            Arqueologa arqueologa = (Arqueologa) actor; 
            arqueologa.addArma(this);
            setEstat(ESTAT_INACTIU);
        }
    }

    public void render(Graphics2D g) {
        //Com dibuixar a la pantalla principal
        g.drawImage(image, getX(), getY(), observer);
    }

    public void actualitzar(Context context) {
        // a cada iteracio del joc es crida a actualizar desde la classe GuiJoc al metode actualizarJoc
        // no fem res, es estatic (no se mou)
    }
}
