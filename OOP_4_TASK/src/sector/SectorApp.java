package sector;

import java.awt.geom.Point2D;
import javax.swing.SwingUtilities;

import sector.model.Sector;
import sector.ui.MainFrame;

/**
 * Точка входа приложения «Сектор круга».
 */
public final class SectorApp {

    private static final Point2D INITIAL_CENTER = new Point2D.Double(400, 300);

    private SectorApp() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame(new Sector(INITIAL_CENTER)).setVisible(true));
    }
}
