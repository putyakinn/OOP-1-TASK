package ui;

import javax.swing.JFrame;

import model.SegmentInput;
import raster.LineRasterizer;

/**
 * Главное окно приложения.
 */
public final class MainWindow extends JFrame {

    public MainWindow(SegmentInput input, LineRasterizer rasterizer) {
        super("Алгоритм Брезенхейма");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(new SegmentCanvas(input, rasterizer));
        pack();
        setLocationRelativeTo(null);
    }
}
