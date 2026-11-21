package sector.ui;

import java.awt.BorderLayout;
import javax.swing.JFrame;

import sector.model.Sector;

/**
 * Главное окно: панель управления сверху, представление сектора в центре.
 */
public final class MainFrame extends JFrame {

    public MainFrame(Sector sector) {
        super("Сектор круга — управление мышью");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        SectorView view = new SectorView(sector);
        new SectorMouseController(sector).attachTo(view);

        add(new ControlPanel(sector), BorderLayout.NORTH);
        add(view, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }
}
