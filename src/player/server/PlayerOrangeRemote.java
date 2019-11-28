package player.server;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseOrange;
import entity.changed.remote.DiceRemote;
import entity.changed.remote.HorseOrangeRemote;
import graphics.Constant;
import map.EntityPosition;
import player.client.Player;

import java.awt.*;
import java.rmi.RemoteException;

public class PlayerOrangeRemote extends PlayerRemote {
    public PlayerOrangeRemote() throws RemoteException {
        Constant.initHorseOrange();
        initHorse();
        diceRemote = new DiceRemote(EntityPosition.ORANGE_DICE_PLACE_X, EntityPosition.ORANGE_DICE_PLACE_Y);
        team = TeamType.TEAM_ORANGE;
        initHorse();
    }


    protected void initHorse() {
        for (int i = 0; i < 4; i++) {
            Point point = Constant.orangeHorseTeam.get(i);
            horses.add(new HorseOrangeRemote(i, point.x, point.y, this));
        }
    }
}
