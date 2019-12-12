package entity.changed.remote;

import constant.TeamType;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;

import java.awt.*;

public class HorseCopyBlue extends HorseCopy {

    public HorseCopyBlue(int id, int x, int y) {
        super(id, x, y);
        team = TeamType.TEAM_BLUE;
    }

    @Override
    public void setEntity() {
        entity = CreateImage.blueHorse;
    }


    @Override
    public void updateRankGraphics() {
        Rank rank = Handler.getInstance().getMapTemp().getBlueTeam().getRanks().get(this.rank - 1);
        x = rank.getX() + 25;
        y = rank.getY() + OFFSET;
    }

    @Override
    public void isKickedAss() {
        Point point = Constant.blueHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.blueHorseFlip;
        } else if (rank != 0) {
            entity = CreateImage.blueHorseRank;
        } else entity = CreateImage.blueHorse;
        super.render(g);
    }
}
