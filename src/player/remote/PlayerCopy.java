package player.remote;

import constant.DiceValue;
import constant.TeamType;
import entity.changed.data.HorseData;
import entity.changed.local.Horse;
import entity.changed.remote.DiceCopy;
import entity.changed.remote.HorseCopy;
import main.Handler;

import java.awt.*;
import java.util.ArrayList;

public class PlayerCopy {

    // ngựa
    protected ArrayList<HorseCopy> horseCopies;

    // xúc xắc
    protected DiceCopy diceCopy;

    // id đội chơi
    protected int id;

    // dấu hiệu cập nhật
    protected HorseData horseData;

    // màu đội
    protected TeamType team;

    // dấu hiệu bị đá
    private boolean isKick;
    private int kickedPosition = -1;


    public PlayerCopy(int id) {
        this.id = id;
    }

    public void tick() {
        diceCopy.tick();
        // nếu có dấu hiệu cập nhật
        if (horseData != null) {
            // tìm ngựa phù hợp
            for (HorseCopy horseCopy : horseCopies) {
                if (horseCopy.getId() == horseData.getId()) {
                    // cập nhật dữ liệu
                    horseCopy.tick(horseData);
                    break;
                }
            }
            // đặt lại horseData
            horseData = null;
        }
        if (isKick) {
            if (kickedPosition != -1) {
                // tìm ngựa ở vị trí này
                for (HorseCopy horseCopy : horseCopies) {
                    if (horseCopy.getPosition() == kickedPosition) {
                        horseCopy.isKickedAss();
                        break;
                    }
                }
                kickedPosition = -1;
            }
            isKick = false;
        }
    }

    public void isKickedAss() {
    }

    public void render(Graphics g) {
        diceCopy.render(g);
        for (HorseCopy horseCopy : horseCopies) {
            horseCopy.render(g);
        }
    }

    public void initHorseCopy() {
        horseCopies = new ArrayList<>();
    }

    public DiceCopy getDiceCopy() {
        return diceCopy;
    }

    public void setHorseData(HorseData horseData) {
        this.horseData = horseData;
    }

    public void setKick(boolean kick) {
        isKick = kick;
    }

    public void setKickedPosition(int kickedPosition) {
        this.kickedPosition = kickedPosition;
    }

    public TeamType getTeam() {
        return team;
    }

    public ArrayList<HorseCopy> getHorseCopies() {
        return horseCopies;
    }

    public int getId() {
        return id;
    }
}
