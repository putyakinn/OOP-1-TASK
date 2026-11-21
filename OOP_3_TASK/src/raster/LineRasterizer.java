package raster;

/**
 * Алгоритм растеризации отрезка.
 */
public interface LineRasterizer {

    /**
     * Передаёт в {@code sink} все пиксели отрезка от (x0, y0) до (x1, y1),
     * включая оба конца, в порядке от начала к концу.
     */
    void rasterize(int x0, int y0, int x1, int y1, PixelSink sink);
}
