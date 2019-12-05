package player.local;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseBlue;
import graphics.Constant;
import main.Handler;
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
            horses.add(new HorseBlue(i, point.x, point.y, this));
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (Handler.getInstance().getMapTemp().getTurn() == team) {
            g.fillOval(EntityPosition.BLUE_DICE_PLACE_X + 35,
                    EntityPosition.BLUE_DICE_PLACE_Y + 125, 30, 30);
        }
    }
}
