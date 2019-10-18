package entity.changed;

import constant.TeamType;
import graphics.CreateImage;

public class HorseViolet extends Horse {
    public HorseViolet(int x, int y, TeamType team) {
        super(x, y, team);
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.violetHorse;
    }
}
