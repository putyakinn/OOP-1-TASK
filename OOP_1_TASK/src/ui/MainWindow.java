package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import scene.HomeScene;

/**
 * Главное окно: сцена и панель кнопок управления анимацией.
 */
public final class MainWindow extends JFrame {

    private static final Dimension CANVAS_SIZE = new Dimension(800, 600);

    public MainWindow(HomeScene home) {
        super("Домик");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new ScenePanel(home.scene(), CANVAS_SIZE), BorderLayout.CENTER);
        add(createControls(home), BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel createControls(HomeScene home) {
        JPanel controls = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 6));
        controls.add(new AnimationToggleButton(home.sun(), "Солнце: движется", "Солнце: остановлено"));
        controls.add(new AnimationToggleButton(home.clouds(), "Облака: движутся", "Облака: остановлены"));

        JButton close = new JButton("Закрыть программу");
        close.addActionListener(e -> dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        controls.add(close);
        return controls;
    }
}
