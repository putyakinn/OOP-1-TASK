package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Цветок: лепестки-круг, жёлтая серединка и стебель.
 */
public final class Flower implements Drawable {

    private static final int PETALS_DIAMETER = 15;
    private static final int CORE_DIAMETER = 3;
    private static final int STEM_LENGTH = 15;
    private static final Color CORE_COLOR = Color.YELLOW;
    private static final Color STEM_COLOR = Color.GREEN;

    private final int x;
    private final int y;
    private final Color petalsColor;

    /**
     * @param x           левый край лепестков
     * @param y           верхний край лепестков
     * @param petalsColor цвет лепестков
     */
    public Flower(int x, int y, Color petalsColor) {
        this.x = x;
        this.y = y;
        this.petalsColor = petalsColor;
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(petalsColor);
        g.fillOval(x, y, PETALS_DIAMETER, PETALS_DIAMETER);

        int coreOffset = (PETALS_DIAMETER - CORE_DIAMETER) / 2;
        g.setColor(CORE_COLOR);
        g.fillOval(x + coreOffset, y + coreOffset, CORE_DIAMETER, CORE_DIAMETER);

        int stemX = x + PETALS_DIAMETER / 2;
        int stemTop = y + PETALS_DIAMETER;
        g.setColor(STEM_COLOR);
        g.drawLine(stemX, stemTop, stemX, stemTop + STEM_LENGTH);
    }
}
