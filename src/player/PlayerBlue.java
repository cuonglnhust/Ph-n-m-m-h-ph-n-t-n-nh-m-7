package player;

import constant.TeamType;
import entity.changed.Dice;
import entity.changed.HorseBlue;
import graphics.Constant;
import map.EntityPosition;

import java.awt.*;

public class PlayerBlue extends Player {

    public PlayerBlue() {
        Constant.initHorseBlue();
        initHorse();
        dice = new Dice(EntityPosition.BLUE_DICE_PLACE_X, EntityPosition.BLUE_DICE_PLACE_Y);
        team = TeamType.TEAM_BLUE;
    }

    @Override
    protected void initHorse() {
        super.initHorse();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.blueHorseTeam.get(i);
            horses.add(new HorseBlue(i, point.x, point.y,this));
        }
    }

}
