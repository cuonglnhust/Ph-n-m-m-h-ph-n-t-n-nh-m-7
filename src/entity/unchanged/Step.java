package entity.unchanged;

import entity.Entity;
import map.EntitySize;

import java.awt.*;
import java.awt.image.BufferedImage;

// bước
public class Step extends Entity {

    public Step(BufferedImage step, int x, int y) {
        super(x, y, EntitySize.STEP_WIDTH, EntitySize.STEP_HEIGHT);
        this.entity = step;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y, width, height, null);
    }

}
