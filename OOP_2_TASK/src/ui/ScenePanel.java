package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Objects;
import javax.swing.JPanel;

import scene.Scene;

/**
 * Панель, отображающая сцену.
 */
public final class ScenePanel extends JPanel {

    private final Scene scene;

    public ScenePanel(Scene scene, Dimension preferredSize) {
        this.scene = Objects.requireNonNull(scene);
        setPreferredSize(preferredSize);
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
