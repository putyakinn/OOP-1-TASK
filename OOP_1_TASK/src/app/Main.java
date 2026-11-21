package app;

import javax.swing.SwingUtilities;

import scene.HomeSceneFactory;
import ui.MainWindow;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainWindow(HomeSceneFactory.create()).setVisible(true));
    }
}
