package sector.ui;

import java.awt.Component;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.Objects;
import javax.swing.SwingUtilities;

import sector.model.Sector;

/**
 * Контроллер: превращает жесты мыши в операции над моделью.
 *
 * <ul>
 *   <li>ЛКМ внутри сектора + перетаскивание — перемещение;</li>
 *   <li>ПКМ + перетаскивание — вращение вокруг центра;</li>
 *   <li>колесо — изменение радиуса.</li>
 * </ul>
 */
public final class SectorMouseController extends MouseAdapter {

    private enum DragMode { NONE, MOVE, ROTATE }

    private static final double WHEEL_RADIUS_STEP = 10;

    private final Sector sector;
    private DragMode mode = DragMode.NONE;
    private Point lastPoint;

    public SectorMouseController(Sector sector) {
        this.sector = Objects.requireNonNull(sector);
    }

    /** Подписывает контроллер на все нужные события компонента. */
    public void attachTo(Component component) {
        component.addMouseListener(this);
        component.addMouseMotionListener(this);
        component.addMouseWheelListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastPoint = e.getPoint();
        if (SwingUtilities.isLeftMouseButton(e) && sector.contains(e.getX(), e.getY())) {
            mode = DragMode.MOVE;
        } else if (SwingUtilities.isRightMouseButton(e)) {
            mode = DragMode.ROTATE;
        } else {
            mode = DragMode.NONE;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mode = DragMode.NONE;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (mode == DragMode.NONE) {
            return;
        }
        switch (mode) {
            case MOVE -> sector.moveBy(e.getX() - lastPoint.x, e.getY() - lastPoint.y);
            case ROTATE -> sector.rotateBy(
                    sector.angleTo(e.getX(), e.getY()) - sector.angleTo(lastPoint.x, lastPoint.y));
            default -> { }
        }
        lastPoint = e.getPoint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        sector.setRadius(sector.getRadius() - e.getWheelRotation() * WHEEL_RADIUS_STEP);
    }
}
