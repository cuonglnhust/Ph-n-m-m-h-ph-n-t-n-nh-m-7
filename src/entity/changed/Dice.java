package entity.changed;

import entity.Entity;
import map.EntitySize;

import java.awt.*;

public class Dice extends Entity {

    private static final int DICE_OFFSET = 25;

    Dice(int x, int y) {
        super(x + DICE_OFFSET, y + DICE_OFFSET, EntitySize.DICE_WIDTH, EntitySize.DICE_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y, width, height, null);
    }
}
