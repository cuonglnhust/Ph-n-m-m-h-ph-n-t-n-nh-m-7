package entity.changed;

import constant.TeamType;
import entity.unchanged.Step;
import graphics.Constant;
import graphics.CreateImage;

import java.awt.*;

public class HorseBlue extends Horse {

    public HorseBlue(int id, int x, int y) {
        super(id, x, y);
        team = TeamType.TEAM_BLUE;
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.blueHorse;
    }

    @Override
    public void iskickedAss() {
        Point point = Constant.blueHorseTeam.get(this.id);
        this.setX(point.x);
        this.setY(point.y);
        this.position = -1;
    }
}
