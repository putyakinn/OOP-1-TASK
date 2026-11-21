package sector.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

/**
 * Геометрическая модель сектора круга: центр, радиус, начальный угол и угол раствора.
 *
 * <p>Углы измеряются в градусах против часовой стрелки, как в математике
 * (ось Y экрана направлена вниз, поэтому при построении фигуры она инвертируется).
 * Допустимые диапазоны радиуса и раствора поддерживаются самой моделью.
 * Об изменениях модель уведомляет подписчиков ({@link PropertyChangeListener}) —
 * ни представление, ни элементы управления на неё не влияют напрямую друг на друга.
 */
public final class Sector {

    public static final String PROP_CENTER = "center";
    public static final String PROP_RADIUS = "radius";
    public static final String PROP_START_ANGLE = "startAngle";
    public static final String PROP_SWEEP_ANGLE = "sweepAngle";

    public static final double MIN_RADIUS = 20;
    public static final double MAX_RADIUS = 400;
    public static final double DEFAULT_RADIUS = 150;

    public static final double MIN_SWEEP_ANGLE = 1;
    public static final double MAX_SWEEP_ANGLE = 359;
    public static final double DEFAULT_SWEEP_ANGLE = 90;

    public static final double DEFAULT_START_ANGLE = 30;

    /** Число отрезков, аппроксимирующих дугу. */
    private static final int ARC_SEGMENTS = 100;

    private final PropertyChangeSupport changes = new PropertyChangeSupport(this);
    private final Point2D initialCenter;

    private Point2D center;
    private double radius = DEFAULT_RADIUS;
    private double startAngle = DEFAULT_START_ANGLE;
    private double sweepAngle = DEFAULT_SWEEP_ANGLE;

    public Sector(Point2D center) {
        this.initialCenter = new Point2D.Double(center.getX(), center.getY());
        this.center = new Point2D.Double(center.getX(), center.getY());
    }

    // ---------- состояние ----------

    public Point2D getCenter() {
        return new Point2D.Double(center.getX(), center.getY());
    }

    public double getRadius() {
        return radius;
    }

    /** Начальный угол, нормализованный в [0, 360). */
    public double getStartAngle() {
        return startAngle;
    }

    public double getSweepAngle() {
        return sweepAngle;
    }

    // ---------- изменение состояния ----------

    public void setCenter(Point2D newCenter) {
        Point2D old = center;
        center = new Point2D.Double(newCenter.getX(), newCenter.getY());
        changes.firePropertyChange(PROP_CENTER, old, center);
    }

    public void moveBy(double dx, double dy) {
        setCenter(new Point2D.Double(center.getX() + dx, center.getY() + dy));
    }

    /** Устанавливает радиус; значение приводится к допустимому диапазону. */
    public void setRadius(double newRadius) {
        double old = radius;
        radius = clamp(newRadius, MIN_RADIUS, MAX_RADIUS);
        changes.firePropertyChange(PROP_RADIUS, old, radius);
    }

    public void setStartAngle(double degrees) {
        double old = startAngle;
        startAngle = normalizeDegrees(degrees);
        changes.firePropertyChange(PROP_START_ANGLE, old, startAngle);
    }

    public void rotateBy(double degrees) {
        setStartAngle(startAngle + degrees);
    }

    /** Устанавливает угол раствора; значение приводится к допустимому диапазону. */
    public void setSweepAngle(double degrees) {
        double old = sweepAngle;
        sweepAngle = clamp(degrees, MIN_SWEEP_ANGLE, MAX_SWEEP_ANGLE);
        changes.firePropertyChange(PROP_SWEEP_ANGLE, old, sweepAngle);
    }

    /** Возвращает все параметры к исходным значениям, включая положение и поворот. */
    public void reset() {
        setCenter(initialCenter);
        setRadius(DEFAULT_RADIUS);
        setStartAngle(DEFAULT_START_ANGLE);
        setSweepAngle(DEFAULT_SWEEP_ANGLE);
    }

    // ---------- геометрия ----------

    /** Полярный угол точки относительно центра сектора, градусы, в [-180, 180]. */
    public double angleTo(double x, double y) {
        return Math.toDegrees(Math.atan2(center.getY() - y, x - center.getX()));
    }

    /** Принадлежит ли точка сектору (с границей). */
    public boolean contains(double x, double y) {
        double dx = x - center.getX();
        double dy = center.getY() - y;
        if (Math.hypot(dx, dy) > radius) {
            return false;
        }
        return normalizeDegrees(angleTo(x, y) - startAngle) <= sweepAngle;
    }

    /** Контур сектора: центр, дуга (ломаная из {@value #ARC_SEGMENTS} звеньев), возврат в центр. */
    public Shape toShape() {
        Path2D.Double path = new Path2D.Double();
        path.moveTo(center.getX(), center.getY());
        for (int i = 0; i <= ARC_SEGMENTS; i++) {
            double angle = Math.toRadians(startAngle + sweepAngle * i / ARC_SEGMENTS);
            path.lineTo(center.getX() + radius * Math.cos(angle),
                    center.getY() - radius * Math.sin(angle));
        }
        path.closePath();
        return path;
    }

    // ---------- подписка ----------

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changes.addPropertyChangeListener(listener);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener) {
        changes.addPropertyChangeListener(property, listener);
    }

    // ---------- вспомогательное ----------

    private static double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

    private static double normalizeDegrees(double degrees) {
        double result = degrees % 360;
        return result < 0 ? result + 360 : result;
    }
}
