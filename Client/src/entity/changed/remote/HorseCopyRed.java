package entity.changed.remote;

import constant.TeamType;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;

import java.awt.*;

public class HorseCopyRed extends HorseCopy {

    public HorseCopyRed(int id, int x, int y) {
        super(id, x, y);
        team = TeamType.TEAM_RED;
    }

    @Override
    public void setEntity() {
        entity = CreateImage.redHorse;
    }

    @Override
    public void isKickedAss() {
        Point point = Constant.redHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
    }

    @Override
    public void updateRankGraphics() {
        Rank rank = Handler.getInstance().getMapTemp().getRedTeam().getRanks().get(this.rank - 1);
        x = rank.getX() - 8;
        y = rank.getY() + 25 + OFFSET;
        width = EntitySize.HORSE_HEIGHT;
        height = EntitySize.HORSE_WIDTH;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.redHorseFlip;
        } else if (rank != 0) {
            entity = CreateImage.redHorseRank;
        } else entity = CreateImage.redHorse;
        super.render(g);
    }
}
