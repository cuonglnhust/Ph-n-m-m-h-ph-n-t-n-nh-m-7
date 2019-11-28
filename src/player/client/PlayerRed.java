package player.client;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseRed;
import graphics.Constant;
import map.EntityPosition;

import java.awt.*;

public class PlayerRed extends Player {
    public PlayerRed() {
        Constant.initHorseRed();
        initHorse();
        dice = new Dice(EntityPosition.RED_DICE_PLACE_X, EntityPosition.RED_DICE_PLACE_Y);
        team = TeamType.TEAM_RED;
    }

    @Override
    protected void initHorse() {
        super.initHorse();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.redHorseTeam.get(i);
            horses.add(new HorseRed(i, point.x, point.y,this));
        }
    }
}
