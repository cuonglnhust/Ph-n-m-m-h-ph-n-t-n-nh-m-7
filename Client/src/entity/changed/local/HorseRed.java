package entity.changed.local;

import constant.TeamType;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;
import player.local.Player;

import java.awt.*;

public class HorseRed extends Horse {

    public HorseRed(int id, int x, int y, Player player) {
        super(id, x, y, player);
        team = TeamType.TEAM_RED;
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.redHorse;
    }

    @Override
    public void isKickedAss() {
        Point point = Constant.redHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
        player.getOnCourt().remove(this.id);
    }

    @Override
    protected void updateRankGraphics() {
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
