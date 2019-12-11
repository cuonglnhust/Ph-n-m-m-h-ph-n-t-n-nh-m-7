package player.remote;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseBlue;
import entity.changed.remote.DiceCopy;
import entity.changed.remote.HorseCopyBlue;
import graphics.Constant;
import main.Handler;
import map.EntityPosition;

import java.awt.*;

public class PlayerCopyBlue extends PlayerCopy {

    public PlayerCopyBlue(int id) {
        super(id);
        Constant.initHorseBlue();
        initHorseCopy();
        diceCopy = new DiceCopy(EntityPosition.BLUE_DICE_PLACE_X, EntityPosition.BLUE_DICE_PLACE_Y);
        team = TeamType.TEAM_BLUE;
    }

    @Override
    public void initHorseCopy() {
        super.initHorseCopy();
        for (int i = 0; i < 4; i++) {
            Point point = Constant.blueHorseTeam.get(i);
            horseCopies.add(new HorseCopyBlue(i, point.x, point.y));
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        // nếu đang chơi

        if (Handler.getInstance().getMapTemp() != null && Handler.getInstance().getMapTemp().getTurn() == team) {
            g.fillOval(EntityPosition.BLUE_DICE_PLACE_X + 35,
                    EntityPosition.BLUE_DICE_PLACE_Y + 125, 30, 30);
        }

        // nếu đang xem
        if (Handler.getInstance().getMapCopy() != null && Handler.getInstance().getMapCopy().getTurn() == team) {
            g.fillOval(EntityPosition.BLUE_DICE_PLACE_X + 35,
                    EntityPosition.BLUE_DICE_PLACE_Y + 125, 30, 30);
        }
    }
}
