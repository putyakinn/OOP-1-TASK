package raster;

import java.util.Objects;

/**
 * Целочисленный алгоритм Брезенхейма для отрезка в любом октанте:
 * только сложение, вычитание и сдвиг, без деления и чисел с плавающей точкой.
 */
public final class BresenhamLineRasterizer implements LineRasterizer {

    @Override
    public void rasterize(int x0, int y0, int x1, int y1, PixelSink sink) {
        Objects.requireNonNull(sink);

        int dx = Math.abs(x1 - x0);
        int dy = Math.abs(y1 - y0);
        int stepX = x0 < x1 ? 1 : -1;
        int stepY = y0 < y1 ? 1 : -1;

        // Накопленная ошибка: разность между «идеальной» и растровой линиями
        int error = dx - dy;
        int x = x0;
        int y = y0;

        while (true) {
            sink.plot(x, y);
            if (x == x1 && y == y1) {
                return;
            }
            int doubledError = 2 * error;
            if (doubledError > -dy) {
                error -= dy;
                x += stepX;
            }
            if (doubledError < dx) {
                error += dx;
                y += stepY;
            }
        }
    }
}
