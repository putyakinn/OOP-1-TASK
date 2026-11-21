package sector.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.Objects;
import javax.swing.JPanel;

import sector.model.Sector;

/**
 * Представление: рисует сектор и справочную информацию. Сама ничего не изменяет —
 * перерисовывается при любом изменении модели.
 */
public final class SectorView extends JPanel {

    private static final Dimension PREFERRED_SIZE = new Dimension(800, 600);
    private static final Color FILL = new Color(100, 150, 230, 180);
    private static final Color OUTLINE = new Color(30, 70, 150);
    private static final Color CENTER_MARK = Color.RED;
    private static final Font INFO_FONT = new Font("Arial", Font.PLAIN, 12);
    private static final int CENTER_MARK_DIAMETER = 6;

    private final Sector sector;

    public SectorView(Sector sector) {
        this.sector = Objects.requireNonNull(sector);
        setBackground(Color.WHITE);
        setPreferredSize(PREFERRED_SIZE);
        sector.addPropertyChangeListener(event -> repaint());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawSector(g2);
            drawCenterMark(g2);
            drawInfo(g2);
        } finally {
            g2.dispose();
        }
    }

    private void drawSector(Graphics2D g) {
        Shape shape = sector.toShape();
        g.setColor(FILL);
        g.fill(shape);
        g.setColor(OUTLINE);
        g.setStroke(new BasicStroke(2f));
        g.draw(shape);
    }

    private void drawCenterMark(Graphics2D g) {
        Point2D center = sector.getCenter();
        double half = CENTER_MARK_DIAMETER / 2.0;
        g.setColor(CENTER_MARK);
        g.fillOval((int) Math.round(center.getX() - half), (int) Math.round(center.getY() - half),
                CENTER_MARK_DIAMETER, CENTER_MARK_DIAMETER);
    }

    private void drawInfo(Graphics2D g) {
        g.setColor(Color.DARK_GRAY);
        g.setFont(INFO_FONT);
        g.drawString(String.format("Радиус: %.0f", sector.getRadius()), 10, 20);
        g.drawString(String.format("Угол раствора: %.0f°", sector.getSweepAngle()), 10, 38);
        g.drawString(String.format("Начальный угол: %.0f°", sector.getStartAngle()), 10, 56);
    }
}
