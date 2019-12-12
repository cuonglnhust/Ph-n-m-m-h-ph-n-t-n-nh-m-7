package entity.changed.local;

import constant.DiceValue;
import constant.HorseState;
import constant.TeamType;
import entity.Entity;
import entity.unchanged.Step;
import graphics.Constant;
import main.Handler;
import map.EntitySize;
import mouse.Mouse;
import player.local.Player;

import java.awt.*;
import java.rmi.RemoteException;

// ngựa
public class Horse extends Entity {

    private static final int CANT_MOVE = 56;
    final int OFFSET = 28;

    // đội màu gì
    protected TeamType team;

    // chủ sở hữu của nó
    protected Player player;

    // x, y tọa độ ô đang đứng
    // ô đang đứng, = -1 là chưa xuất phát hoặc đã lên chuồng
    protected int position;

    // ô trong chuồng, = 0 nếu chưa lên chuồng
    protected int rank;

    private HorseState horseState;
    private int checkResult;

    int id;
    private Mouse mouse;
    private boolean turn;

    public Horse(int id, int x, int y, Player player) {
        super(x, y, EntitySize.HORSE_WIDTH, EntitySize.HORSE_HEIGHT);
        this.position = -1;
        this.rank = 0;
        this.horseState = HorseState.NONE;
        this.turn = false;
        this.id = id;
        this.player = player;
        mouse = Handler.getInstance().getMouse();
        setEntity();
    }

    // khởi tạo graphics Horse theo team
    protected void setEntity() {}

    // xuất quân
    private void starting() {
        // nếu vị trí xuất quân không có ngựa
        if (Handler.getInstance().getMapTemp().
                getVirtualMap()[Constant.teamFirstPoint.get(team)] == TeamType.NONE) {
            startingUpdate();
            // cập nhật vị trí cho map và quảng bá
            Handler.getInstance().getMapTemp().updateVirtualMapBroadcast(position, team);
            turn = true;
        } else if (Handler.getInstance().getMapTemp().
                getVirtualMap()[Constant.teamFirstPoint.get(team)] != team) {

            startingUpdate();

            // lấy Player có ngựa bị đá và cập nhật
            Handler.getInstance().getMapTemp().kickAss(position);

            Handler.getInstance().getMapTemp().updateVirtualMapBroadcast(position, team);
            turn = true;

        }

    }

    @Override
    public void tick() {
        if (isClicked()) {
            actionWithState();
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

    /*
     * Hàm kiểm tra trạng thái của ngựa
     * Cập nhật trạng thái sau của Horse
     * */
    public void checkState(DiceValue diceValue) {

        // đang RUN, START, PREPARE, KICK
        if (position != -1) {
            // Trạng thái hiện tại là START, RUN, KICK
            if (position != Constant.teamLastPoint.get(team)) {

                checkResult = checkLine(diceValue.getValue());

                if (checkResult == CANT_MOVE) {
                    horseState = HorseState.CANT_MOVE;
                } else if (checkResult >= 0) {
                    horseState = HorseState.RUN;
                } else {
                    horseState = HorseState.KICK;
                }
            } // Trạng thái hiện tại là PREPARE
            else {
                if (player.getOnRank().get(diceValue.getValue()) != null) {
                    horseState = HorseState.CANT_MOVE;
                } else
                    horseState = HorseState.RANK;
            }
        } else {
            // trạng thái hiện tại là RANK
            if (rank != 0) {
                if (rank != 6 && diceValue.getValue() == rank + 1) {
                    horseState = HorseState.RANK;
                } else horseState = HorseState.CANT_MOVE;
            } // trạng thái hiện tại là NONE
            else {
                if (diceValue == DiceValue.SIX) {
                    // nếu vị trí xuất quân hiện tại không có ngựa nào của mình
                    if (Handler.getInstance().getMapTemp().getVirtualMap()[Constant.teamFirstPoint.get(team)] != team) {
                        horseState = HorseState.START;
                    } else horseState = HorseState.CANT_MOVE;
                } else
                    horseState = HorseState.CANT_MOVE;
            }
        }

    }

    private void actionWithState() {
        switch (horseState) {
            case START:
                starting();
                break;
            case RUN:
                run();
                break;
            case KICK:
                kick();
                break;
            case RANK:
                rank();
                break;
        }
    }

    // di chuyển ngựa
    private void run() {
        System.out.println("Run");
        Handler.getInstance().getMapTemp().updateVirtualMapBroadcast(position, TeamType.NONE);
        position = checkResult;
        this.setX(Handler.getInstance().getMapTemp().getMapGraphics().get(position).getX());
        this.setY(Handler.getInstance().getMapTemp().getMapGraphics().get(position).getY());
        // quảng bá di chuyển
        System.out.println("Horse broadcast");
        broadcastHorse();
        Handler.getInstance().getMapTemp().updateVirtualMapBroadcast(position, team);
        turn = true;
        System.out.println("Position = " + position + "");
    }

    // đá ngựa khác
    private void kick() {
        int last = -(checkResult + 1);
        kickAss(last);
        position = last;
        this.setX(Handler.getInstance().getMapTemp().getMapGraphics().get(position).getX());
        this.setY(Handler.getInstance().getMapTemp().getMapGraphics().get(position).getY());
        broadcastHorse();
        turn = true;
    }

    // lên rank
    private void rank() {
        rank = player.getDice().getDiceValue().getValue();
        updateRankGraphics();
        if (position != -1) {
            player.getOnCourt().remove(id);
            Handler.getInstance().getMapTemp().updateVirtualMapBroadcast(position, TeamType.NONE);
            position = -1;
        }
        player.getOnRank().put(rank, this);
        // quảng bá di chuyển
        broadcastHorse();
        turn = true;
    }

    protected void updateRankGraphics() {}

    /*
    Check xem trên đường đi có ngựa khác hay không
    Trả về last_position nếu di chuyển được
    Trả về 56 nếu không đi được
    Trả về -last_position nếu đá
    */
    private int checkLine(int step) {
        int first_position = position;
        int last_position = position + step;
        int lastPoint = Constant.teamLastPoint.get(team);

        // TH last > điểm cuối để lên chuồng
        if (first_position <= lastPoint && last_position > lastPoint) {
            return CANT_MOVE;
        }
        // TH first < last < 56
        else if (last_position < 56) {
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
            if (Handler.getInstance().getMapTemp().getVirtualMap()[i] != TeamType.NONE) {
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
            if (Handler.getInstance().getMapTemp().getVirtualMap()[i] != TeamType.NONE) {
                return -1;
            }
        }

        // xét nửa đoạn sau
        for (int i = 0; i <= last; i++) {
            if (Handler.getInstance().getMapTemp().getVirtualMap()[i] != TeamType.NONE) {
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
                if (Handler.getInstance().getMapTemp().getVirtualMap()[lastPosition] == team) {
                    return CANT_MOVE;
                }
                return -(lastPosition + 1);
            default:
                return CANT_MOVE;
        }
    }

    // cập nhật lại vị trí khi bị đá
    public void isKickedAss() {}

    private void kickAss(int last) {
        Handler.getInstance().getMapTemp().updateVirtualMapBroadcast(position, TeamType.NONE);

        Handler.getInstance().getMapTemp().kickAss(last);

        Handler.getInstance().getMapTemp().updateVirtualMapBroadcast(last, team);
    }

    // cập nhật vị trí cho ngựa
    private void startingUpdate() {
        Step step = Handler.getInstance().getMapTemp().getMapGraphics().get(Constant.teamFirstPoint.get(team));
        x = step.getX();
        y = step.getY();
        position = Constant.teamFirstPoint.get(team);
        // quảng bá di chuyển
        broadcastHorse();
    }

    private void broadcastHorse() {
        if (Handler.getInstance().getClientPlayer() != null) {
            try {
                System.out.println("Client broadcast");
                Handler.getInstance().getClientPlayer().getPlayGameServer().
                        updateHorse(Handler.getInstance().getId(), this.id, position, rank);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (Handler.getInstance().getServerPlayer() != null) {
            try {
                System.out.println("Server broadcast");
                Handler.getInstance().getServerPlayer().getPlayGameServerImp().
                        updateHorseFromServer(Handler.getInstance().getId(), this.id, position, rank);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void printHorse() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Team : ")
                .append(team)
                .append(" Horse : " + id)
                .append("Position : " + position)
                .append("State : " + horseState)
        ;
        System.out.println(stringBuffer.toString());
    }

    public HorseState getHorseState() {
        return horseState;
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

    public int getRank() {
        return rank;
    }
}
