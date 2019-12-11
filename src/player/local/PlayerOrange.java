package player.local;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseOrange;
import graphics.Constant;
import main.Handler;
import map.EntityPosition;

import java.awt.*;

public class PlayerOrange extends Player {
    public PlayerOrange() {
        Constant.initHorseOrange();
        initHorse();
        dice = new Dice(EntityPosition.ORANGE_DICE_PLACE_X, EntityPosition.ORANGE_DICE_PLACE_Y);
        team = TeamType.TEAM_ORANGE;
    }

    @Override
    protected void initHorse() {
        super.initHorse();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.orangeHorseTeam.get(i);
            horses.add(new HorseOrange(i, point.x, point.y,this
            ));
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (Handler.getInstance().getMapTemp().getTurn() == team) {
            g.fillOval(EntityPosition.ORANGE_DICE_PLACE_X + 35,
                    EntityPosition.ORANGE_DICE_PLACE_Y - 65, 30, 30);
        }
    }
}
