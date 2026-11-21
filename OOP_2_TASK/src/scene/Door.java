package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Дверь с ручкой у правого края.
 */
public final class Door implements Drawable {

    private static final Color PANEL = new Color(139, 69, 19);
    private static final Color OUTLINE = Color.BLACK;
    private static final int KNOB_SIZE = 8;
    private static final int KNOB_OFFSET_Y = 40;

    private final Rectangle bounds;

    public Door(Rectangle bounds) {
        this.bounds = new Rectangle(bounds);
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(PANEL);
        g.fill(bounds);

        g.setColor(OUTLINE);
        g.draw(bounds);
        int knobX = bounds.x + bounds.width - KNOB_SIZE;
        g.fillOval(knobX, bounds.y + KNOB_OFFSET_Y, KNOB_SIZE, KNOB_SIZE);
    }
}
