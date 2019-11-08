package entity.unchanged;

import entity.Entity;
import map.EntityPosition;

import java.awt.*;
import java.awt.image.BufferedImage;

// ô xếp
public class Rank extends Entity {

    // nếu orientation = true là ngang, false là dọc
    boolean orientation;
    // màu nền
    Color background;

    public Rank(BufferedImage rank, int x, int y, boolean orientation,Color background) {
        super(x, y, rank.getWidth(), rank.getHeight());
        this.entity = rank;
        this.orientation = orientation;
        this.background = background;
    }

    @Override
    public void render(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g.create();
        if (orientation) {
            // là ngang thì 80 x 40
            int width = 80;
            int height = 40;
            int newX = x + 40 - this.width / 2;
            int newY = y + 2;
            // vẽ nền
            graphics2D.setColor(background);
            graphics2D.fillRect(x, y, width, height);
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.setColor(Color.white);
            graphics2D.drawRect(x, y, width, height);
            graphics2D.drawImage(entity, newX, newY, null);
        } else {
            // là dọc thì 40 x 80
            int width = 40;
            int height = 80;
            int newY = y + 40 - this.height / 2;
            int newX = x + 2;
            // vẽ nền
            graphics2D.setColor(background);
            graphics2D.fillRect(x, y, width, height);
            graphics2D.setStroke(new BasicStroke(2));
            graphics2D.setColor(Color.white);
            graphics2D.drawRect(x, y, width, height);
            graphics2D.drawImage(entity, newX, newY, null);
        }
    }


}
