package entity.changed;

import constant.TeamType;
import graphics.CreateImage;

public class HorseOrange extends Horse {
    public HorseOrange(int x, int y, TeamType team) {
        super(x, y, team);
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.orangeHorse;
    }
}
