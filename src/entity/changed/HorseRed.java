package entity.changed;

import constant.TeamType;
import graphics.Constant;
import graphics.CreateImage;
import player.Player;

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
    public void iskickedAss() {
        Point point = Constant.redHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
        player.getOnCourt().remove(this.id);
    }
}
