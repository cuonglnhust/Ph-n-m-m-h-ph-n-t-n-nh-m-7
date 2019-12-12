package player.remote;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseRed;
import entity.changed.remote.DiceCopy;
import entity.changed.remote.HorseCopyRed;
import graphics.Constant;
import main.Handler;
import map.EntityPosition;

import java.awt.*;

public class PlayerCopyRed extends PlayerCopy {

    public PlayerCopyRed(int id) {
        super(id);
        Constant.initHorseRed();
        initHorseCopy();
        diceCopy = new DiceCopy(EntityPosition.RED_DICE_PLACE_X, EntityPosition.RED_DICE_PLACE_Y);
        team = TeamType.TEAM_RED;
    }

    @Override
    public void initHorseCopy() {
        super.initHorseCopy();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.redHorseTeam.get(i);
            horseCopies.add(new HorseCopyRed(i, point.x, point.y));
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        if (Handler.getInstance().getMapTemp() != null && Handler.getInstance().getMapTemp().getTurn() == team) {
            g.fillOval(EntityPosition.RED_DICE_PLACE_X + 35,
                    EntityPosition.RED_DICE_PLACE_Y + 125, 30, 30);
        }

        if (Handler.getInstance().getMapCopy() != null && Handler.getInstance().getMapCopy().getTurn() == team) {
            g.fillOval(EntityPosition.RED_DICE_PLACE_X + 35,
                    EntityPosition.RED_DICE_PLACE_Y + 125, 30, 30);
        }
    }
}
