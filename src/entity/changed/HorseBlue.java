package entity.changed;

import constant.TeamType;
import graphics.CreateImage;

public class HorseBlue extends Horse {

    public HorseBlue(int x, int y, TeamType team) {
        super(x, y, team);
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.blueHorse;
    }
}
