package entity.changed.remote;

import constant.TeamType;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;

import java.awt.*;

public class HorseCopyOrange extends HorseCopy {

    public HorseCopyOrange(int id, int x, int y) {
        super(id, x, y);
        team = TeamType.TEAM_ORANGE;
    }

    @Override
    public void setEntity() {
        entity = CreateImage.orangeHorse;
    }

    @Override
    public void isKickedAss() {
        Point point = Constant.orangeHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
    }

    @Override
    public void updateRankGraphics() {
        Rank rank = Handler.getInstance().getMapTemp().getOrangeTeam().getRanks().get(this.rank - 1);
        x = rank.getX() + 25;
        y = rank.getY() - 8 + OFFSET;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.orangeHorseFlip;
        } else if (rank != 0) {
            entity = CreateImage.orangeHorseRank;
        } else entity = CreateImage.orangeHorse;
        super.render(g);
    }
}
