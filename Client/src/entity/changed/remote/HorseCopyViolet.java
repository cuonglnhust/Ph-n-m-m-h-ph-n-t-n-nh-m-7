package entity.changed.remote;

import constant.TeamType;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;

import java.awt.*;

public class HorseCopyViolet extends HorseCopy {

    public HorseCopyViolet(int id, int x, int y) {
        super(id, x, y);
        team = TeamType.TEAM_VIOLET;
    }

    public void setEntity() {
        entity = CreateImage.violetHorse;
    }

    @Override
    public void isKickedAss() {
        Point point = Constant.violetHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
    }

    @Override
    public void updateRankGraphics() {
        Rank rank = Handler.getInstance().getMapTemp().getVioletTeam().getRanks().get(this.rank - 1);
        x = rank.getX();
        y = rank.getY() + 25 + OFFSET;
        width = EntitySize.HORSE_HEIGHT;
        height = EntitySize.HORSE_WIDTH;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.violetHorseFlip;
        } else if (rank != 0) {
            entity = CreateImage.violetHorseRank;
        } else {
            entity = CreateImage.violetHorse;
        }
        super.render(g);
        super.render(g);
    }
}
