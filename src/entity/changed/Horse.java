package entity.changed;

import entity.Entity;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;
import map.Map;
import mouse.Mouse;
import player.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

// ngựa
public class Horse extends Entity {

    private final int CANT_MOVE = 56;
    private final int OFFSET = 28;

    // đội màu gì
    private int team;

    // chủ sở hữu của nó
    private Player player;

    // x, y tọa độ ô đang đứng
    // ô đang đứng, = -1 là chưa xuất phát hoặc đã lên chuồng
    private int position;

    // ô trong chuồng, = 0 nếu chưa lên chuồng
    private int rank;

    private Mouse mouse;
    private Map map;

    public Horse(int x, int y, int team) {
        super(x, y, EntitySize.HORSE_WIDTH, EntitySize.HORSE_HEIGHT);
        position = -1;
        rank = 0;
        mouse = Handler.getInstance().getMouse();
        map = Handler.getInstance().getMap();
        this.team = team;
        setEntity();
    }

    // khởi tạo graphics Horse theo team
    private void setEntity() {
        switch (team) {
            case Constant.TEAM_BLUE:
                entity = CreateImage.blueHorse;
                break;
            case Constant.TEAM_RED:
                entity = CreateImage.redHorse;
                break;
            case Constant.TEAM_ORANGE:
                entity = CreateImage.orangeHorse;
                break;
            case Constant.TEAM_VIOLET:
                entity = CreateImage.violetHorse;
                break;
        }
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

    // Phát hiện sự kiện di chuyển theo Click chuột vào ngựa
    public boolean isClicked() {
        if (player.isTurn()) {
            if (getBound().contains(mouse.getMouseX(), mouse.getMouseY())) {
                return mouse.isLeftClick() || mouse.isRightClick();
            }
            return false;
        }
        return false;
    }

    public void action(int diceValue) {
        // ở trong chuồng
        if (rank != 0) {
            if (diceValue - rank == 1) {
                rank = diceValue;
            }
        }
        // đang ở điểm cuối
        else if (Constant.teamLastPoint.get(team) == position) {
            // lên chuồng
            rank = diceValue;
            position = -1;
        }
        // xuất quân
        else if (rank == 0 && position == -1) {

        } else {

        }
    }

    // Ngựa di chuyển theo số bước step
    public void move(int step) {
        int checkResult = checkLine(step);
        if (checkResult == CANT_MOVE) {
            // không thể di chuyển
        } else if (checkResult > 0) {
            // di chuyển được
        } else {
            // đá
        }
    }

    /* check xem trên đường đi có ngựa khác hay không

    Trả về last_position nếu di chuyển được
    Trả về 56 nếu không đi được
    Trả về -last_position nếu đá

    */

    private int checkLine(int step) {
        int first_position = position;
        int last_position = position + step;
        // TH first < last < 56
        if (last_position < 56) {
            return actionWithCondition(isBlock1(first_position, last_position), last_position);
        }
        // TH last < first < 56
        else {
            // tính lại last_position
            last_position = last_position - 56;
            return actionWithCondition(isBlock2(first_position, last_position), last_position);
        }
    }


    // check ngựa trên đường trong TH first < last < 56
    private int isBlock1(int first, int last) {
        for (int i = first + 1; i <= last; i++) {
            if (map.getVirtualMap()[i] != 0) {
                if (i == last) {
                    // ô last đang có ngựa
                    return 1;
                }
                // 1 trong các ô đang có ngựa, không phải last
                return -1;
            }
        }
        // không có ngựa trên đường
        return 0;
    }

    // check ngựa trên đường trong TH last < first < 56
    private int isBlock2(int first, int last) {
        // Chia đường thành 2 đoạn

        // xét nửa đoạn đường đầu
        for (int i = first + 1; i < 56; i++) {
            if (map.getVirtualMap()[i] != 0) {
                return -1;
            }
        }

        // xét nửa đoạn sau
        for (int i = 0; i <= last; i++) {
            if (map.getVirtualMap()[i] != 0) {
                if (i == last) {
                    return 1;
                }
                return -1;
            }
        }
        return 0;
    }

    // kết quả check đường
    private int actionWithCondition(int condition, int lastPosition) {
        switch (condition) {
            case 0:
                return lastPosition;
            case 1:
                // check xem ô last là ngựa gì
                // cùng màu
                if (map.getVirtualMap()[lastPosition] == team) {
                    return CANT_MOVE;
                }
                return -lastPosition;
            default:
                return CANT_MOVE;
        }
    }


}
