package player;

import constant.TeamType;
import entity.changed.Dice;
import entity.changed.HorseViolet;
import graphics.Constant;
import map.EntityPosition;

import java.awt.*;

public class PlayerViolet extends Player {

    public PlayerViolet() {
        Constant.initHorseViolet();
        initHorse();
        dice = new Dice(EntityPosition.VIOLET_DICE_X,EntityPosition.VIOLET_DICE_Y,this);
    }

    @Override
    protected void initHorse() {
        super.initHorse();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.violetHorseTeam.get(i);
            horses.add(new HorseViolet(i, point.x, point.y));
        }
    }
}
