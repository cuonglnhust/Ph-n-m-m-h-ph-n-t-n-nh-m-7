package player;

import constant.TeamType;
import entity.changed.Dice;
import entity.changed.HorseBlue;
import graphics.Constant;
import main.Handler;
import map.EntityPosition;

import java.awt.*;

public class PlayerBlue extends Player {

    public PlayerBlue() {
        Constant.initHorseBlue();
        initHorse();
        dice = new Dice(EntityPosition.BLUE_DICE_X, EntityPosition.BLUE_DICE_Y, this);
    }

    @Override
    protected void initHorse() {
        super.initHorse();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.blueHorseTeam.get(i);
            horses.add(new HorseBlue(i, point.x, point.y));
        }
    }


}
