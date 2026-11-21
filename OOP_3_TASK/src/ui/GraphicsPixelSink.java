package ui;

import java.awt.Color;
import java.awt.Graphics2D;

import raster.PixelSink;

/**
 * Рисует каждый «пиксель» растра квадратиком заданного размера на {@link Graphics2D}.
 */
final class GraphicsPixelSink implements PixelSink {

    private final Graphics2D graphics;
    private final int pixelSize;

    GraphicsPixelSink(Graphics2D graphics, Color color, int pixelSize) {
        this.graphics = graphics;
        this.pixelSize = pixelSize;
        graphics.setColor(color);
    }

    @Override
    public void plot(int x, int y) {
        int half = pixelSize / 2;
        graphics.fillRect(x - half, y - half, pixelSize, pixelSize);
    }
}
