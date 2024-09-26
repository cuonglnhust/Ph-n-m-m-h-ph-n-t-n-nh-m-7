package entity.unchanged;

import entity.Entity;
import map.EntitySize;

import java.awt.*;
import java.awt.image.BufferedImage;

// xúc xắc
public class DicePlace extends Entity {

    public DicePlace(BufferedImage DiceImage, int x, int y) {
        super(x, y, EntitySize.DICE_PLACE_WIDTH, EntitySize.DICE_PLACE_HEIGHT);
        this.entity = DiceImage;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y, width, height, null);
    }
}
