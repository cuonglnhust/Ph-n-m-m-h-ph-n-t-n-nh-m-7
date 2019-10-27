package entity.changed;

import constant.DiceValue;
import constant.TeamType;
import entity.Entity;
import entity.unchanged.Step;
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

    protected final int CANT_MOVE = 56;
    protected final int OFFSET = 28;

    // đội màu gì
    protected TeamType team;

    // chủ sở hữu của nó
    protected Player player;

    // x, y tọa độ ô đang đứng
    // ô đang đứng, = -1 là chưa xuất phát hoặc đã lên chuồng
    protected int position;

    // ô trong chuồng, = 0 nếu chưa lên chuồng
    protected int rank;

    protected int id;
    protected Mouse mouse;
    protected boolean turn;
    protected boolean cantMove;


    public Horse(int id, int x, int y, Player player) {
        super(x, y, EntitySize.HORSE_WIDTH, EntitySize.HORSE_HEIGHT);
        position = -1;
        rank = 0;
        turn = false;
        cantMove = false;
        this.id = id;
        this.player = player;
        mouse = Handler.getInstance().getMouse();
        setEntity();
    }

    // khởi tạo graphics Horse theo team
    protected void setEntity() {
    }

    // xuất quân
    protected void starting() {
        // nếu vị trí xuất quân không có ngựa
        if (Handler.getInstance().getMap().
                getVirtualMap()[Constant.teamFirstPoint.get(team)] == TeamType.NONE) {
            startingUpdate();
            // cập nhật vị trí cho map
            Handler.getInstance().getMap().updateVirtualMap(position, team);
            turn = true;
        } else if (Handler.getInstance().getMap().
                getVirtualMap()[Constant.teamFirstPoint.get(team)] != team) {
            startingUpdate();
            // lấy Player có ngựa bị đá và cập nhật
            Handler.getInstance().getMap().kickAss(position);
            Handler.getInstance().getMap().updateVirtualMap(position, team);
            turn = true;
        }

    }

    @Override
    public void tick() {
        if (isClicked()) {
            action(player.getDice().getDiceValue().getValue());
        }
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
        if (getBound().contains(mouse.getMouseX(), mouse.getMouseY())) {
            return mouse.isLeftClick() || mouse.isRightClick();
        }
        return false;
    }

    public void action(int diceValue) {
        // ở trong chuồng
        if (rank != 0) {
            if (diceValue - rank == 1) {
                rank = diceValue;
                turn = true;
            }
        }
        // đang ở điểm cuối
        else if (Constant.teamLastPoint.get(team) == position) {
            // lên chuồng
            rank = diceValue;
            position = -1;
            turn = true;
        }
        // xuất quân
        else if (rank == 0 && position == -1) {
            if (diceValue == DiceValue.SIX.getValue()) {
                starting();
            }
        } else {
            System.out.println("Team : " + team + " move : " + diceValue);
            move(diceValue);
        }
    }

    // Ngựa di chuyển theo số bước step
    protected void move(int step) {
        int checkResult = checkLine(step);
        // không thể di chuyển
        if (checkResult == CANT_MOVE) {
            cantMove = true;
        }
        // di chuyển được
        else if (checkResult > 0) {
            Handler.getInstance().getMap().updateVirtualMapNone(position);
            position = checkResult;
            this.setX(Handler.getInstance().getMap().getMapGraphics().get(position).getX());
            this.setY(Handler.getInstance().getMap().getMapGraphics().get(position).getY());
            Handler.getInstance().getMap().updateVirtualMap(position, team);
            turn = true;
        }
        // đá
        else {
            System.out.println("Đá");
            int last = -(checkResult + 1);
            kickAss(last);
            position = last;
            this.setX(Handler.getInstance().getMap().getMapGraphics().get(position).getX());
            this.setY(Handler.getInstance().getMap().getMapGraphics().get(position).getY());
            turn = true;
        }
    }

    /*
    Check xem trên đường đi có ngựa khác hay không
    Trả về last_position nếu di chuyển được
    Trả về 56 nếu không đi được
    Trả về -last_position nếu đá
    */
    protected int checkLine(int step) {
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
    protected int isBlock1(int first, int last) {
        for (int i = first + 1; i <= last; i++) {
            if (Handler.getInstance().getMap().getVirtualMap()[i] != TeamType.NONE) {
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
    protected int isBlock2(int first, int last) {
        // Chia đường thành 2 đoạn

        // xét nửa đoạn đường đầu
        for (int i = first + 1; i < 56; i++) {
            if (Handler.getInstance().getMap().getVirtualMap()[i] != TeamType.NONE) {
                return -1;
            }
        }

        // xét nửa đoạn sau
        for (int i = 0; i <= last; i++) {
            if (Handler.getInstance().getMap().getVirtualMap()[i] != TeamType.NONE) {
                System.out.println("Map - " + Handler.getInstance().getMap().getVirtualMap()[i] + " last " + last);
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
                if (Handler.getInstance().getMap().getVirtualMap()[lastPosition] == team) {
                    System.out.println(" Same team ");
                    return CANT_MOVE;
                }
                return -(lastPosition + 1);
            default:
                System.out.println(" Have other horse on Line");
                return CANT_MOVE;
        }
    }

    // cập nhật lại vị trí khi bị đá
    public void iskickedAss() {
    }

    private void kickAss(int last) {
        System.out.println("Horse.kickAss()");
        Handler.getInstance().getMap().updateVirtualMapNone(position);
        Handler.getInstance().getMap().kickAss(last);
        Handler.getInstance().getMap().updateVirtualMap(last, team);
    }

    public int getPosition() {
        return position;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

    private void startingUpdate() {
        Step step = Handler.getInstance().getMap().getMapGraphics().get(Constant.teamFirstPoint.get(team));
        x = step.getX();
        y = step.getY();
        position = Constant.teamFirstPoint.get(team);
    }

    public void printHorse() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Team : ")
                .append(team)
                .append(" Horse : " + id)
                .append("Position : " + position)
        ;
        System.out.println(stringBuffer.toString());
    }


    public boolean isCantMove() {
        return cantMove;
    }

    public void setCantMove(boolean cantMove) {
        this.cantMove = cantMove;
    }
}
