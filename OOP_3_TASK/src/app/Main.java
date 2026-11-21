package app;

import javax.swing.SwingUtilities;

import model.SegmentInput;
import raster.BresenhamLineRasterizer;
import ui.MainWindow;

public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
                new MainWindow(new SegmentInput(), new BresenhamLineRasterizer()).setVisible(true));
    }
}
