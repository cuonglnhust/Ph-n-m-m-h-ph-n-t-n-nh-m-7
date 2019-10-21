package player;

import constant.TeamType;
import entity.changed.Dice;
import entity.changed.HorseRed;
import graphics.Constant;
import map.EntityPosition;

import java.awt.*;

public class PlayerRed extends Player {
    public PlayerRed() {
        Constant.initHorseRed();
        initHorse();
        dice = new Dice(EntityPosition.RED_DICE_X, EntityPosition.RED_DICE_Y, this);
    }

    @Override
    protected void initHorse() {
        super.initHorse();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.redHorseTeam.get(i);
            horses.add(new HorseRed(i, point.x, point.y));
        }
    }
}
