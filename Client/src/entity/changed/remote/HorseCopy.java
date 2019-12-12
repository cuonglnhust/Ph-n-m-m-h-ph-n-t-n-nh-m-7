package entity.changed.remote;

import constant.TeamType;
import entity.Entity;
import entity.changed.data.HorseData;
import entity.unchanged.Step;
import main.Handler;
import map.EntitySize;
import player.local.Player;
import player.remote.PlayerCopy;

import java.awt.*;

public class HorseCopy extends Entity {

    private static final int CANT_MOVE = 56;
    final int OFFSET = 28;

    // đội màu gì
    protected TeamType team;

    // x, y tọa độ ô đang đứng
    // ô đang đứng, = -1 là chưa xuất phát hoặc đã lên chuồng
    protected int position;

    // ô trong chuồng, = 0 nếu chưa lên chuồng
    protected int rank;

    // định danh
    protected int id;

    public HorseCopy(int id, int x, int y) {
        super(x, y, EntitySize.HORSE_WIDTH, EntitySize.HORSE_HEIGHT);
        this.id = id;
        setEntity();
    }

    public void setEntity() {
    }

    public void isKickedAss() {
    }

    public void tick(HorseData horseData) {
        position = horseData.getPosition();
        rank = horseData.getRank();
        updatePosition();
    }

    // cập nhật tọa độ theo vị trí
    private void updatePosition() {
        if (position != -1) {
            Step step = null;
            // nếu trong trạng thái chơi
            if (Handler.getInstance().getMapTemp() != null) {
                step = Handler.getInstance().getMapTemp().getMapGraphics().get(position);
            }
            // nếu trong trạng thái xem
            else if (Handler.getInstance().getMapCopy() != null) {
                step = Handler.getInstance().getMapCopy().getMapGraphics().get(position);
            }
            x = step.getX();
            y = step.getY();
        } else if (rank != 0) {
            updateRankGraphics();
        }
    }

    // cập nhật đồ họa cho rank
    public void updateRankGraphics() {
    }

    // cập nhật vị trí
    public void setData(HorseData horseData) {
        position = horseData.getPosition();
        rank = horseData.getRank();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y - OFFSET, width, height, null);
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public int getRank() {
        return rank;
    }
}
