package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Сцена — упорядоченный набор слоёв ({@link Drawable}). Слои рисуются в порядке добавления,
 * анимированные слои обновляются по {@link #update()}.
 */
public final class Scene {

    private final Color background;
    private final List<Drawable> layers = new ArrayList<>();
    private final List<Animated> animations = new ArrayList<>();

    public Scene(Color background) {
        this.background = Objects.requireNonNull(background);
    }

    /** Добавляет слой поверх уже существующих. Анимированные слои регистрируются автоматически. */
    public Scene add(Drawable layer) {
        layers.add(Objects.requireNonNull(layer));
        if (layer instanceof Animated animated) {
            animations.add(animated);
        }
        return this;
    }

    public void update() {
        animations.forEach(Animated::update);
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
