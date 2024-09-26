package player.local;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseRed;
import graphics.Constant;
import main.Handler;
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

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (Handler.getInstance().getMapTemp().getTurn() == team) {
            g.fillOval(EntityPosition.RED_DICE_PLACE_X + 35,
                    EntityPosition.RED_DICE_PLACE_Y + 125, 30, 30);
        }
    }
}
