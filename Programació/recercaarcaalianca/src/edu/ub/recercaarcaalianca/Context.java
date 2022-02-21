package edu.ub.recercaarcaalianca;

import java.awt.event.KeyEvent;

/**
 * El context del joc representa l'estat del joc en un moment especific en el
 * temps.
 *
 * @author ub.edu
 */
public class Context {

    public static final int W = 0;
    public static final int S = 1;
    public static final int A = 2;
    public static final int D = 3;
    public static final int KEY_UP = 4;
    public static final int KEY_DOWN = 5;
    public static final int KEY_LEFT = 6;
    public static final int KEY_RIGHT = 7;
    private long tempsFrame;
    private final Joc joc;
    private final boolean[] keyMap = {false, false, false, false, false, false, false, false};
    private boolean enemicInmovilitzat;

    public Context(Joc joc) {
        this.joc = joc;
    }

    /**
     * Obte el joc.
     *
     * @return el joc
     */
    public Joc getJoc() {
        return joc;
    }

    /**
     * Obte l'Cambra actual.
     *
     * @return l'Cambra
     */
    public Cambra getCambra() {
        return joc.getTemple().getCambra();
    }

    public boolean isKeyPressed(int key) {
        return keyMap[key];
    }

    public void updateKeys(KeyEvent e, boolean pressed) {
        if (e.getKeyCode() == KeyEvent.VK_W)
            keyMap[W] = pressed;
        if (e.getKeyCode() == KeyEvent.VK_A)
            keyMap[A] = pressed;
        if (e.getKeyCode() == KeyEvent.VK_S)
            keyMap[S] = pressed;
        if (e.getKeyCode() == KeyEvent.VK_D)
            keyMap[D] = pressed;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            keyMap[KEY_UP] = pressed;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            keyMap[KEY_DOWN] = pressed;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            keyMap[KEY_LEFT] = pressed;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            keyMap[KEY_RIGHT] = pressed;
    }

    /**
     * Obte el temps transcorregut en milisegons entre el frame anterior i
     * l'actual.
     *
     * @return el temps en ms.
     */
    public boolean getEnemicInmovilitzat() {
        return enemicInmovilitzat;
    }

    public void setEnemicInmovilitzat(boolean a) {
        this.enemicInmovilitzat = a;
    }

    public long getTempsFrame() {
        return tempsFrame;
    }

    public void setTempsFrame(long tempsFrame) {
        this.tempsFrame = tempsFrame;
    }

}
