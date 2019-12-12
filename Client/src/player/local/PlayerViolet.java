package player.local;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseViolet;
import graphics.Constant;
import main.Handler;
import map.EntityPosition;

import java.awt.*;

public class PlayerViolet extends Player {

    public PlayerViolet() {
        Constant.initHorseViolet();
        initHorse();
        dice = new Dice(EntityPosition.VIOLET_DICE_PLACE_X,EntityPosition.VIOLET_DICE_PLACE_Y);
        team = TeamType.TEAM_VIOLET;
    }

    @Override
    protected void initHorse() {
        super.initHorse();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.violetHorseTeam.get(i);
            horses.add(new HorseViolet(i, point.x, point.y,this));
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (Handler.getInstance().getMapTemp().getTurn() == team) {
            g.fillOval(EntityPosition.VIOLET_DICE_PLACE_X + 35,
                    EntityPosition.VIOLET_DICE_PLACE_Y - 65, 30, 30);
        }
    }
}
