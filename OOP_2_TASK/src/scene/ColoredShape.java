package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.util.Objects;

/**
 * Произвольная геометрическая фигура с заливкой и (необязательным) контуром.
 */
public final class ColoredShape implements Drawable {

    private final Shape shape;
    private final Color fill;
    private final Color outline; // null — контур не рисуется

    private ColoredShape(Shape shape, Color fill, Color outline) {
        this.shape = Objects.requireNonNull(shape);
        this.fill = Objects.requireNonNull(fill);
        this.outline = outline;
    }

    public static ColoredShape filled(Shape shape, Color fill) {
        return new ColoredShape(shape, fill, null);
    }

    public static ColoredShape outlined(Shape shape, Color fill, Color outline) {
        return new ColoredShape(shape, fill, Objects.requireNonNull(outline));
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(fill);
        g.fill(shape);
        if (outline != null) {
            g.setColor(outline);
            g.draw(shape);
        }
    }
}
