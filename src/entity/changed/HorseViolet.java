package entity.changed;

import constant.TeamType;
import graphics.Constant;
import graphics.CreateImage;
import player.Player;

import java.awt.*;

public class HorseViolet extends Horse {

    public HorseViolet(int id, int x, int y, Player player) {
        super(id, x, y, player);
        team = TeamType.TEAM_VIOLET;
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.violetHorse;
    }

    @Override
    public void iskickedAss() {
        Point point = Constant.violetHorseTeam.get(this.id);
        x = point.x;
        y = point.y;
        this.position = -1;
        player.getOnCourt().remove(this.id);
    }
}
