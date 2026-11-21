package raster;

/**
 * Приёмник пикселей: куда именно «ставится точка», алгоритму растеризации безразлично
 * (экран, изображение, список координат в тесте).
 */
@FunctionalInterface
public interface PixelSink {

    void plot(int x, int y);
}
