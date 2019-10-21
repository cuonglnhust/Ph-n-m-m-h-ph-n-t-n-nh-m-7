package entity.changed;

import constant.TeamType;
import graphics.CreateImage;

public class HorseRed extends Horse {


    public HorseRed(int id, int x, int y) {
        super(id, x, y);
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.redHorse;
    }
}
