package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Облако из нескольких овалов.
 */
public final class Cloud implements Drawable {

    /**
     * Один овал облака: смещение по X относительно положения облака,
     * абсолютная координата Y и размеры.
     */
    public record Puff(int offsetX, int y, int width, int height) {
    }

    private static final Color COLOR = Color.WHITE;

    private final int x;
    private final List<Puff> puffs;

    /**
     * @param x     положение облака по горизонтали
     * @param puffs овалы, из которых оно состоит
     */
    public Cloud(int x, List<Puff> puffs) {
        this.x = x;
        this.puffs = List.copyOf(puffs);
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(COLOR);
        for (Puff puff : puffs) {
            g.fillOval(x + puff.offsetX(), puff.y(), puff.width(), puff.height());
        }
    }
}
