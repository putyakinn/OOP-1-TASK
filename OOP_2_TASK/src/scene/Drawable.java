package scene;

import java.awt.Dimension;
import java.awt.Graphics2D;

/**
 * Элемент сцены, умеющий нарисовать себя.
 */
public interface Drawable {

    /**
     * @param g      графический контекст; элемент вправе менять его состояние
     *               (цвет, шрифт и т.д.) — {@link Scene} выдаёт каждому элементу собственную копию
     * @param canvas текущий размер области рисования
     */
    void draw(Graphics2D g, Dimension canvas);
}
