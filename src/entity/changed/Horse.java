package entity.changed;

import entity.Entity;
import graphics.Constant;
import main.Handler;
import map.EntitySize;
import map.Map;
import mouse.Mouse;

import java.awt.*;

// ngựa
public class Horse extends Entity {

    private final int OFFSET = 28;
    private int team;


    // x, y tọa độ ô đang đứng

    // ô đang đứng, = 0 là chưa xuất phát hoặc đã lên chuồng
    private int position;

    // ô trong chuồng, = 0 nếu chưa lên chuồng
    private int rank;

    private Mouse mouse;
    private Map map;

    public Horse(int x, int y, int team) {
        super(x, y, EntitySize.HORSE_WIDTH, EntitySize.HORSE_HEIGHT);
        mouse = Handler.getInstance().getMouse();
        map = Handler.getInstance().getMap();
        this.team = team;
    }

    @Override
    public void tick() {

    }


    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y - OFFSET, width, height, null);
    }


    private Rectangle getBound() {
        return new Rectangle(x, y - OFFSET, width, height);
    }

    // Phát hiện sự kiện di chuyển theo Click chuột
    public boolean isMove() {
        if (getBound().contains(mouse.getMouseX(), mouse.getMouseY())) {
            return mouse.isLeftClick() || mouse.isRightClick();
        }
        return false;
    }

    // Ngựa di chuyển theo số bước step
    public void move(int step) {

    }

    /* check xem trên đường đi có ngựa khác hay không
    Trả về vị trí ô tiếp theo nếu di chuyển được */
    private int checkLine(int step) {
        int first_position = position;
        int last_position = position + step;

        // check đang có trong chuồng không
        if (isRank()) {
            return -1;
        }
        // check xem có đang ở ô cuối không
        else if (isLast()) {
            return -1;
        }

        // tính lại last

        else{
            if (last_position < 56){
                // check xem last_position
                return last_position;
            }else if (last_position == 56){

            }

            return 0;
        }

    }

    private boolean isRank() {
        return rank != 0;
    }

    private boolean isLast() {
        return Constant.teamLastPoint.containsKey(team);
    }


}
