package entity.changed;

import constant.TeamType;
import entity.unchanged.Step;
import graphics.Constant;
import graphics.CreateImage;

public class HorseOrange extends Horse {


    public HorseOrange(int id, int x, int y) {
        super(id, x, y);
        team = TeamType.TEAM_ORANGE;
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.orangeHorse;
    }


}
