package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.List;

/**
 * Облако из нескольких овалов. Плывёт вправо и, достигнув границы,
 * появляется заново в точке возврата.
 */
public final class Cloud extends AnimatedShape {

    /**
     * Один овал облака: смещение по X относительно положения облака,
     * абсолютная координата Y и размеры.
     */
    public record Puff(int offsetX, int y, int width, int height) {
    }

    private static final Color COLOR = Color.WHITE;

    private final List<Puff> puffs;
    private final int maxX;
    private final int resetX;
    private final int speed;

    private int x;

    /**
     * @param x      начальное положение
     * @param puffs  овалы, из которых состоит облако
     * @param maxX   граница, после которой облако возвращается
     * @param resetX положение, в которое облако возвращается
     * @param speed  смещение за один тик, пикселей
     */
    public Cloud(int x, List<Puff> puffs, int maxX, int resetX, int speed) {
        this.x = x;
        this.puffs = List.copyOf(puffs);
        this.maxX = maxX;
        this.resetX = resetX;
        this.speed = speed;
    }

    @Override
    protected void step() {
        x += speed;
        if (x > maxX) {
            x = resetX;
        }
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(COLOR);
        for (Puff puff : puffs) {
            g.fillOval(x + puff.offsetX(), puff.y(), puff.width(), puff.height());
        }
    }
}
