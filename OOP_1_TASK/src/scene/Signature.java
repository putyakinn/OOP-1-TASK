package scene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

/**
 * Подпись по центру у нижнего края сцены, с лёгкой светлой тенью.
 */
public final class Signature implements Drawable {

    private static final Font FONT = new Font("Arial", Font.BOLD, 24);
    private static final Color TEXT_COLOR = Color.BLACK;
    private static final Color SHADOW_COLOR = new Color(255, 255, 255, 150);
    private static final int SHADOW_OFFSET = 2;

    private final String text;
    private final int bottomMargin;

    public Signature(String text, int bottomMargin) {
        this.text = text;
        this.bottomMargin = bottomMargin;
    }

    @Override
    public void draw(Graphics2D g, Dimension canvas) {
        g.setFont(FONT);
        FontMetrics metrics = g.getFontMetrics();
        int x = (canvas.width - metrics.stringWidth(text)) / 2;
        int y = canvas.height - bottomMargin;

        g.setColor(SHADOW_COLOR);
        g.drawString(text, x - SHADOW_OFFSET, y - SHADOW_OFFSET);
        g.setColor(TEXT_COLOR);
        g.drawString(text, x, y);
    }
}
