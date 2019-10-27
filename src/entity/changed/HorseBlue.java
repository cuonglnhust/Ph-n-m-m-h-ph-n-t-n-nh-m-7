package entity.changed;

import constant.TeamType;
import entity.unchanged.Step;
import graphics.Constant;
import graphics.CreateImage;
import player.Player;

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
    public void iskickedAss() {
        Point point = Constant.blueHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
        player.getOnCourt().remove(this.id);
    }
}
