package entity.changed;

import constant.TeamType;
import graphics.CreateImage;

public class HorseViolet extends Horse {

    public HorseViolet(int id, int x, int y) {
        super(id, x, y);
    }

    @Override
    protected void setEntity() {
        entity = CreateImage.violetHorse;
    }
}
