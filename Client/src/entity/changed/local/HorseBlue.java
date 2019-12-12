package entity.changed.local;

import constant.TeamType;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import player.local.Player;

import java.awt.*;

public class HorseBlue extends Horse {

    public HorseBlue(int id, int x, int y, Player player) {
        super(id, x, y, player);
        team = TeamType.TEAM_BLUE;
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.blueHorse;
    }

    @Override
    public void isKickedAss() {
        Point point = Constant.blueHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
        player.getOnCourt().remove(this.id);
    }

    @Override
    protected void updateRankGraphics() {
        Rank rank = Handler.getInstance().getMapTemp().getBlueTeam().getRanks().get(this.rank - 1);
        x = rank.getX() + 25;
        y = rank.getY() + OFFSET;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.blueHorseFlip;
        }else if (rank != 0){
            entity = CreateImage.blueHorseRank;
        }
        else entity = CreateImage.blueHorse;
        super.render(g);
    }
}
