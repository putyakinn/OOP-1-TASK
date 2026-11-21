package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сцена — упорядоченный набор слоёв ({@link Drawable}), которые рисуются
 * поверх однотонного фона в порядке добавления.
 */
public final class Scene {

    private final Color background;
    private final List<Drawable> layers = new ArrayList<>();

    public Scene(Color background) {
        this.background = Objects.requireNonNull(background);
    }

    /** Добавляет слой поверх уже существующих. */
    public Scene add(Drawable layer) {
        layers.add(Objects.requireNonNull(layer));
        return this;
    }

    public void draw(Graphics2D g, Dimension canvas) {
        g.setColor(background);
        g.fillRect(0, 0, canvas.width, canvas.height);
        for (Drawable layer : layers) {
            Graphics2D isolated = (Graphics2D) g.create();
            try {
                layer.draw(isolated, canvas);
            } finally {
                isolated.dispose();
            }
        }
    }
}
