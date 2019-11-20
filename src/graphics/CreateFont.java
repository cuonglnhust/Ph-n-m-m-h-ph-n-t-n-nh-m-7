package graphics;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateFont {
    public static String historyFont;
    public static String homeFont;

    public static void create() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/kredit.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/STREETCR.TTF")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/Michroma.ttf")));
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/BLACKR.ttf")));
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        historyFont = "STREETCR";
        homeFont = "kredit";
    }




    private static Font scaleFontWidth(String fontName, Graphics g, int width) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Font font = Font.decode(fontName);
        Rectangle2D rectangle2D = graphics2D.getFontMetrics(font).getStringBounds("KKKKKK", g);
        float newSize = (float) (font.getSize2D() * width / rectangle2D.getWidth());
        Font font1 = font.deriveFont(4 * newSize / 5);
        graphics2D.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Map<TextAttribute, Object> atts = new HashMap<TextAttribute, Object>();
        atts.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
        Font font2 = font1.deriveFont(atts);
        return font2;
    }

}
