package entity.changed;

import entity.Entity;
import map.EntityPosition;
import map.EntitySize;

import java.awt.*;

// ngựa
public class Horse extends Entity {

    private final int OFFSET = 28;

    // x, y tọa độ ô đang đứng

    // ô đang đứng, = 0 là chưa xuất phát hoặc đã lên chuồng
    private int position;

    // ô trong chuồng, = 0 nếu chưa lên chuồng
    private int rank;

    public Horse(int x, int y) {
        super(x, y, EntitySize.HORSE_WIDTH, EntitySize.HORSE_HEIGHT);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y - OFFSET, width, height, null);
    }


}
