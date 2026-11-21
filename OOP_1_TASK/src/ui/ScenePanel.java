package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;
import javax.swing.JPanel;
import javax.swing.Timer;

import scene.Scene;

/**
 * Панель, отображающая сцену и гоняющая её анимацию по таймеру.
 * Таймер работает только пока панель находится в иерархии отображаемых компонентов.
 */
public final class ScenePanel extends JPanel {

    private static final int FRAME_DELAY_MS = 50;

    private final Scene scene;
    private final Timer timer;

    public ScenePanel(Scene scene, Dimension preferredSize) {
        this.scene = Objects.requireNonNull(scene);
        setPreferredSize(preferredSize);
        this.timer = new Timer(FRAME_DELAY_MS, e -> {
            scene.update();
            repaint();
        });
    }

    @Override
    public void addNotify() {
        super.addNotify();
        timer.start();
    }

    @Override
    public void removeNotify() {
        timer.stop();
        super.removeNotify();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            scene.draw(g2, getSize());
        } finally {
            g2.dispose();
        }
    }
}
