package ui;

import java.awt.Dimension;
import javax.swing.JFrame;

import scene.Scene;

/**
 * Главное окно приложения.
 */
public final class MainWindow extends JFrame {

    private static final Dimension CANVAS_SIZE = new Dimension(800, 600);

    public MainWindow(Scene scene) {
        super("Домик");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new ScenePanel(scene, CANVAS_SIZE));
        pack();
        setLocationRelativeTo(null);
    }
}
