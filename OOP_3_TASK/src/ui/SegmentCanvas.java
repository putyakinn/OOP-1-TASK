package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Objects;
import javax.swing.JPanel;

import model.Segment;
import model.SegmentInput;
import raster.LineRasterizer;

/**
 * Холст: принимает щелчки мыши, передаёт их модели ввода и рисует результат.
 */
public final class SegmentCanvas extends JPanel {

    private static final Dimension PREFERRED_SIZE = new Dimension(800, 600);
    private static final Color AXES_COLOR = Color.LIGHT_GRAY;
    private static final Color LINE_COLOR = Color.BLUE;
    private static final Color MARKER_COLOR = Color.RED;
    private static final int MARKER_DIAMETER = 8;
    private static final int PIXEL_SIZE = 2;

    private final SegmentInput input;
    private final LineRasterizer rasterizer;

    public SegmentCanvas(SegmentInput input, LineRasterizer rasterizer) {
        this.input = Objects.requireNonNull(input);
        this.rasterizer = Objects.requireNonNull(rasterizer);
        setBackground(Color.WHITE);
        setPreferredSize(PREFERRED_SIZE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                input.click(e.getPoint());
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        try {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            drawAxes(g2);
            input.start().ifPresent(start -> drawMarker(g2, start));
            input.segment().ifPresent(segment -> drawSegment(g2, segment));
        } finally {
            g2.dispose();
        }
    }

    private void drawAxes(Graphics2D g) {
        g.setColor(AXES_COLOR);
        g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        g.drawLine(getWidth() / 2, 0, getWidth() / 2, getHeight());
    }

    private void drawSegment(Graphics2D g, Segment segment) {
        Point a = segment.start();
        Point b = segment.end();
        rasterizer.rasterize(a.x, a.y, b.x, b.y, new GraphicsPixelSink(g, LINE_COLOR, PIXEL_SIZE));
        drawMarker(g, b);
    }

    private void drawMarker(Graphics2D g, Point p) {
        g.setColor(MARKER_COLOR);
        int half = MARKER_DIAMETER / 2;
        g.fillOval(p.x - half, p.y - half, MARKER_DIAMETER, MARKER_DIAMETER);
    }
}
