package list.graphics;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;

public class TextGraphicsElement {
    private int x;
    private int width, height;
    private String text;
    private int bottomLine;
    private String font;

    public TextGraphicsElement(int x, int width, int height, String text, int bottomLine, String font) {
        this.x = x;
        this.width = width;
        this.height = height;
        this.text = text;
        this.bottomLine = bottomLine;
        this.font = font;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.setFont(scaleFontWidth(g));
        g.drawString(text, alignCenterHorizontal(g), alignCenterVertical(g, bottomLine));

    }


    private Font scaleFontWidth(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Font font1 = Font.decode(font);
        Rectangle2D rectangle2D = graphics2D.getFontMetrics(font1).getStringBounds("KKKKKK", g);
        float newSize = (float) (font1.getSize2D() * width / rectangle2D.getWidth());
        Font font2 = font1.deriveFont(4 * newSize / 5);
        graphics2D.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Map<TextAttribute, Object> atts = new HashMap<TextAttribute, Object>();
        atts.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
        Font font3 = font2.deriveFont(atts);
        return font2;
    }


    private int alignCenterVertical(Graphics g, int bottomLine) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Font font1 = Font.decode(font);
        Rectangle2D rectangle2D = graphics2D.getFontMetrics(font1).getStringBounds("KKKKKK", g);
        float baseLine = bottomLine - ((float) (height - rectangle2D.getHeight())) / 2;
        return (int) baseLine;
    }

    private int alignCenterHorizontal(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Font font1 = Font.decode(font);
        Rectangle2D rectangle2D = graphics2D.getFontMetrics(font1).getStringBounds("KKKKKK", g);
        float leftLine = x + ((float) (width - rectangle2D.getWidth())) / 8;
        return (int) leftLine;
    }

    public void setText(String text) {
        this.text = text;
    }
}
