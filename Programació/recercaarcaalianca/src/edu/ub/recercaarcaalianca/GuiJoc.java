package edu.ub.recercaarcaalianca;

import edu.ub.recercaarcaalianca.actors.Actor;
import edu.ub.recercaarcaalianca.actors.Arqueologa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

/**
 * Classe principal que controla i coordina el joc.
 */
public class GuiJoc implements KeyListener {

    private static final int NS_PER_FRAME = 1000 * 1000 * 1000 /
            Constants.FRAMES_PER_SEGON;
    int x;
    int y;
    private JFrame frame;
    private Canvas canvas;
    private Context context;
    private Marcador marcador;
    private final Joc joc;
    private final LogicaJoc logica;
    private LogicaJoc.EstatJoc estat;

    /**
     * Constructor que posa en marxa el joc proporcionat.
     *
     * @param joc el joc
     */
    public GuiJoc(Joc joc) {
        this.joc = joc;
        this.logica = new LogicaJoc(joc);
        init();
        run();
    }

    public JFrame getFrame(){
        return this.frame;
    }

    public void keyTyped(KeyEvent e) {
    }

    /**
     * Listener dels events del teclat.
     *
     * @param e l'event
     */
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_ESCAPE:
                logica.exit();
                break;
            case KeyEvent.VK_M:
                logica.pausar();
                break;
            case KeyEvent.VK_SPACE:
                logica.iniciar();
                break;
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
            case KeyEvent.VK_A:
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_LEFT:
                context.updateKeys(e, true);
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        context.updateKeys(e, false);
    }

    // private methods *********************************************************

    private void init() {
        GraphicsEnvironment ge =
                GraphicsEnvironment.getLocalGraphicsEnvironment();

        frame = new JFrame("UB :: Programacio I- Cindy a la recerca de l'Arca de l'Aliança-");
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(Constants.AMPLADA_FINESTRA,
                Constants.ALCADA_FINESTRA));

        frame.setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.setResizable(false);
        frame.setIgnoreRepaint(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.addKeyListener(this);
        frame.pack();

        Point cp = ge.getCenterPoint();
        cp.translate(-frame.getWidth() / 2, -frame.getHeight() / 2);
        frame.setLocation(cp.x, cp.y);

        frame.setVisible(true);
        canvas.createBufferStrategy(2);
        canvas.requestFocus();

        marcador = new Marcador();

        context = new Context(joc);
    }

    private void run() {
        boolean contentsLost;

        long tempsFramePrevi = System.currentTimeMillis();
        long tempsFrameFinal;

        while (logica.getEstat() != LogicaJoc.EstatJoc.EXIT) {

            long ara = System.currentTimeMillis();
            long duracioFrame = (ara - tempsFramePrevi);
            tempsFramePrevi = ara;
            tempsFrameFinal = System.nanoTime() + GuiJoc.NS_PER_FRAME;

            BufferStrategy bufferStrategy = this.canvas.getBufferStrategy();
            context.setTempsFrame(duracioFrame);

            // aixo controla els estats del joc
            switch (logica.getEstat()) {
                case MENU:
                    mostrarMenu(bufferStrategy);
                    break;
                case INICIANT:
                    break;
                case JUGANT:
                    actualitzarJoc();
                    doRender(bufferStrategy);
                    break;
                case GAMEOVER:
                    mostrarGameOver(bufferStrategy);
                    break;
                case PAUSAT:
                    break;
            }

            contentsLost = bufferStrategy.contentsLost();
            if (contentsLost) {
                if (bufferStrategy.contentsRestored())
                    contentsLost = false;
            } else {
                bufferStrategy.show();
            }

            esperarFiFrame(tempsFrameFinal);
        }
        System.out.println("Fins aviat!");
        System.exit(0);
    }

    private void actualitzarJoc() {
        Arqueologa arqueologa = (Arqueologa) joc.getArqueologa();
       // .... feu el codi aqui
       estat = logica.getEstat();
       if (arqueologa.getVida() == 0 || arqueologa.getHaGuanyat() || arqueologa.haGuanyatSoldats()){
           logica.setEstat(estat.GAMEOVER);
       }
        arqueologa.actualitzar(context);
        Cambra h = joc.getTemple().getCambra();
        for (Actor actor : h.getActors()) {
            actor.actualitzar(context);
                if (actor.getEstat() == Constants.ESTAT_ACTIU &&
                    arqueologa.getLimits().intersects(actor.getLimits())) {
                    Colisio colisio = new Colisio(arqueologa);
                    actor.tractarColisio(colisio);
                }
        }
        
       
    }

    private void esperarFiFrame(long tempsFrameFinal) {
        Thread.yield();
        while (System.nanoTime() < tempsFrameFinal) {
            Thread.yield();
            try {
                Thread.sleep(1);
            } catch (Exception e) {
            }
        }
    }

    private void doRender(BufferStrategy bufferStrategy) {
        Graphics g = bufferStrategy.getDrawGraphics();

        Graphics2D g2 = (Graphics2D) g;
        joc.getTemple().render(g2);
        Cambra h = joc.getTemple().getCambra();
        for (Actor actor : h.getActors()) {
            if (actor.getEstat() != Constants.ESTAT_INACTIU)
                actor.render(g2);
        }

        joc.getArqueologa().render(g2);

        marcador.render(context, g2);
    }

    private void mostrarMenu(BufferStrategy bufferStrategy) {
        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;
        dibuixarMarc(g2, Color.LIGHT_GRAY);

        Image image = Util.carregarImatge("cindy_low.png");
        g2.drawImage(image, 80, 50, frame);

        Font f = new Font("Dialog", Font.PLAIN, 30);
        g2.setFont(f);
        g2.setColor(Color.white);
        g2.drawString("* Cindy a la recerca de l'Arca de l'Aliança *", 160, 80);

        f = new Font("Dialog", Font.PLAIN, 16);
        g2.setFont(f);
        g2.drawString("'Espai' Nova Partida", 180, 110);
        g2.drawString("'ESC' Sortir", 330, 110);

        image = Util.carregarImatge("portada.jpg", 0, 0, 650, 400);
        g2.drawImage(image, 75, 135, frame);

        Rectangle r = canvas.getBounds();
        r.setBounds(600, 135, 125, 120);
        g2.setColor(Color.BLACK);
        g2.fill(r);
        g2.draw(r);

        g2.setColor(Color.white);
        g.drawString("Controls:", 640, 155);
        g.drawString("Moviment", 637, 185);
        g.drawString("↑", 665, 210);
        g.drawString("← ↓ →", 646, 230);
    }


    private void mostrarGameOver(BufferStrategy strategy) {
        Graphics g = strategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;
        Arqueologa arqueologa = (Arqueologa) joc.getArqueologa();
        Font f = new Font("Dialog", Font.PLAIN, 30);
        String missatge;
        Color color;
        int x = 0;
        // Situacions: 1. cindy ha trobat l'arca, 2. Cindy s'ha mort 
        // ... feu el codi aqui
        if (arqueologa.getVida() == 0){
            missatge = "Has mort.";
            x = 325;
            color = Color.RED;
        }
        else if (arqueologa.haGuanyatSoldats()){
            missatge = "Has matat a la meitat dels soldats!";
            x = 200;
            color = Color.GREEN;
        }
        else {
            missatge = "Has trobat l'arca!";
            x = 320;
            color = Color.GREEN;
        }
        dibuixarMarc(g2, color);


        g2.setFont(f);
        g2.setColor(Color.white);
        g2.drawString(missatge, x, 250);
        f = new Font("Dialog", Font.PLAIN, 14);
        g2.setFont(f);

        f = new Font("Dialog", Font.PLAIN, 14);
        g2.setFont(f);
        g2.drawString("'ESC' Sortir", 350, 290);
    }

    private void dibuixarMarc(Graphics2D g2, Color color) {
        Rectangle r = canvas.getBounds();
        r.setBounds((int) r.getX() + 20, (int) r.getY() + 20, (int) (r.getWidth() - 40),
                (int) (r.getHeight() - 40));

        g2.setColor(Color.BLACK);
        g2.fill(canvas.getBounds());
        g2.setColor(color);
        g2.setStroke(new BasicStroke(10.f, BasicStroke.CAP_ROUND,
                BasicStroke.JOIN_ROUND, 20.0f));
        g2.draw(r);
    }
}
