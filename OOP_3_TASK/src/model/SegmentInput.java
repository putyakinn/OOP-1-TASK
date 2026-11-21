package model;

import java.awt.Point;
import java.util.Objects;
import java.util.Optional;

/**
 * Модель двухшагового ввода отрезка щелчками мыши.
 * Первый щелчок задаёт начало (прежний отрезок сбрасывается), второй — конец.
 */
public final class SegmentInput {

    private Point start;
    private Point end;

    /** Обрабатывает очередной щелчок. */
    public void click(Point point) {
        Objects.requireNonNull(point);
        if (start != null && end == null) {
            end = new Point(point);
        } else {
            start = new Point(point);
            end = null;
        }
    }

    /** Начальная точка, если она уже задана. */
    public Optional<Point> start() {
        return Optional.ofNullable(start).map(Point::new);
    }

    /** Готовый отрезок, если заданы оба конца. */
    public Optional<Segment> segment() {
        if (start == null || end == null) {
            return Optional.empty();
        }
        return Optional.of(new Segment(new Point(start), new Point(end)));
    }
}
