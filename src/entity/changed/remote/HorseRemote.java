package entity.changed.remote;

import constant.DiceValue;
import constant.HorseState;
import constant.TeamType;
import entity.Entity;
import entity.unchanged.Step;
import graphics.Constant;
import main.Handler;
import map.EntitySize;
import mouse.Mouse;
import player.client.Player;
import player.server.PlayerRemote;

import java.awt.*;

public class HorseRemote extends Entity {

    final int OFFSET = 28;

    // đội màu gì
    protected TeamType team;

    // chủ sở hữu của nó
    protected PlayerRemote playerRemote;

    // x, y tọa độ ô đang đứng
    // ô đang đứng, = -1 là chưa xuất phát hoặc đã lên chuồng
    protected int position;

    // ô trong chuồng, = 0 nếu chưa lên chuồng
    protected int rank;

    private HorseState horseState;
    private int checkResult;

    int id;
    private boolean turn;

    public HorseRemote(int id, int x, int y, PlayerRemote playerRemote) {
        super(x, y, EntitySize.HORSE_WIDTH, EntitySize.HORSE_HEIGHT);
        this.position = -1;
        this.rank = 0;
        this.horseState = HorseState.NONE;
        this.turn = false;
        this.id = id;
        this.playerRemote = playerRemote;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y - OFFSET, width, height, null);
    }

    public void updateHorsePosition(int position, int rank) {
        this.position = position;
        this.rank = rank;
    }

}
