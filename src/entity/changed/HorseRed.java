package entity.changed;

import constant.TeamType;
import graphics.CreateImage;

public class HorseRed extends Horse {
    public HorseRed(int x, int y, TeamType team) {
        super(x, y, team);
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.redHorse;
    }
}
