package entity.unchanged;

import entity.Entity;
import map.EntityPosition;
import map.EntitySize;

import java.awt.*;
import java.awt.image.BufferedImage;

// chuồng
public class Cage extends Entity {

    public Cage(BufferedImage cageImage, int x, int y) {
        super(x, y, EntitySize.CAGE_WIDTH, EntitySize.CAGE_HEIGHT);
        this.entity = cageImage;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y, width, height, null);
        g.drawRect(x - 5, y - 5, width + 10, height + 10);
    }
}
