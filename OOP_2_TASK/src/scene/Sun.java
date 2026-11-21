package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Солнце с лучами.
 */
public final class Sun implements Drawable {

    private static final int DIAMETER = 100;
    private static final int RAY_COUNT = 12;
    private static final int RAY_INNER_RADIUS = 60;
    private static final int RAY_OUTER_RADIUS = 80;
    private static final Color BODY_COLOR = Color.YELLOW;
    private static final Color RAY_COLOR = Color.ORANGE;

    private final int x;
    private final int y;

    /**
     * @param x левый край диска
     * @param y верхний край диска
     */
    public Sun(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(BODY_COLOR);
        g.fillOval(x, y, DIAMETER, DIAMETER);

        g.setColor(RAY_COLOR);
        int centerX = x + DIAMETER / 2;
        int centerY = y + DIAMETER / 2;
        for (int i = 0; i < RAY_COUNT; i++) {
            double angle = i * 2 * Math.PI / RAY_COUNT;
            double cos = Math.cos(angle);
            double sin = Math.sin(angle);
            g.drawLine(centerX + (int) (RAY_INNER_RADIUS * cos), centerY + (int) (RAY_INNER_RADIUS * sin),
                    centerX + (int) (RAY_OUTER_RADIUS * cos), centerY + (int) (RAY_OUTER_RADIUS * sin));
        }
    }
}
