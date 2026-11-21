package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.util.List;

/**
 * Домик: стены, крыша, окно и дверь. Геометрия частей вычисляется
 * относительно прямоугольника стен, поэтому дом можно поставить в любое место.
 */
public final class House implements Drawable {

    private static final int ROOF_OVERHANG = 20;
    private static final int ROOF_HEIGHT = 100;
    private static final int WINDOW_OFFSET_X = 80;
    private static final int WINDOW_OFFSET_Y = 50;
    private static final Dimension WINDOW_SIZE = new Dimension(90, 70);
    private static final int DOOR_OFFSET_X = 180;
    private static final Dimension DOOR_SIZE = new Dimension(50, 80);

    private final List<Drawable> parts;

    /**
     * @param walls прямоугольник стен
     */
    public House(Rectangle walls) {
        Rectangle wallBounds = new Rectangle(walls);

        Polygon roof = new Polygon(
                new int[]{wallBounds.x - ROOF_OVERHANG,
                        wallBounds.x + wallBounds.width / 2,
                        wallBounds.x + wallBounds.width + ROOF_OVERHANG},
                new int[]{wallBounds.y, wallBounds.y - ROOF_HEIGHT, wallBounds.y},
                3);

        Rectangle window = new Rectangle(
                wallBounds.x + WINDOW_OFFSET_X, wallBounds.y + WINDOW_OFFSET_Y,
                WINDOW_SIZE.width, WINDOW_SIZE.height);

        Rectangle door = new Rectangle(
                wallBounds.x + DOOR_OFFSET_X, wallBounds.y + wallBounds.height - DOOR_SIZE.height,
                DOOR_SIZE.width, DOOR_SIZE.height);

        this.parts = List.of(
                ColoredShape.outlined(wallBounds, Color.PINK, Color.BLACK),
                ColoredShape.outlined(roof, Color.RED, Color.BLACK),
                new HouseWindow(window),
                new Door(door));
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        parts.forEach(part -> part.draw(g, canvas));
    }
}
