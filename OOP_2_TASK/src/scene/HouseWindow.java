package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * Окно с крестовой рамой.
 */
public final class HouseWindow implements Drawable {

    private static final Color GLASS = Color.CYAN;
    private static final Color FRAME = Color.BLACK;

    private final Rectangle bounds;

    public HouseWindow(Rectangle bounds) {
        this.bounds = new Rectangle(bounds);
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(GLASS);
        g.fill(bounds);

        g.setColor(FRAME);
        g.draw(bounds);
        int midX = bounds.x + bounds.width / 2;
        int midY = bounds.y + bounds.height / 2;
        g.drawLine(midX, bounds.y, midX, bounds.y + bounds.height);
        g.drawLine(bounds.x, midY, bounds.x + bounds.width, midY);
    }
}
