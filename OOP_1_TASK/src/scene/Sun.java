package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Солнце с лучами. Колеблется по горизонтали между двумя крайними положениями.
 */
public final class Sun extends AnimatedShape {

    private static final int DIAMETER = 100;
    private static final int RAY_COUNT = 12;
    private static final int RAY_INNER_RADIUS = 60;
    private static final int RAY_OUTER_RADIUS = 80;
    private static final Color BODY_COLOR = Color.YELLOW;
    private static final Color RAY_COLOR = Color.ORANGE;

    private final int y;
    private final int minX;
    private final int maxX;
    private final int speed;

    private int x;
    private int direction = 1;

    /**
     * @param x     начальная координата левого края
     * @param y     координата верхнего края
     * @param minX  левая граница движения
     * @param maxX  правая граница движения
     * @param speed смещение за один тик, пикселей
     */
    public Sun(int x, int y, int minX, int maxX, int speed) {
        if (minX > maxX || x < minX || x > maxX) {
            throw new IllegalArgumentException("Положение солнца должно лежать в [minX, maxX]");
        }
        this.x = x;
        this.y = y;
        this.minX = minX;
        this.maxX = maxX;
        this.speed = speed;
    }

    @Override
    protected void step() {
        x += direction * speed;
        if (x >= maxX) {
            x = maxX;
            direction = -1;
        } else if (x <= minX) {
            x = minX;
            direction = 1;
        }
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
