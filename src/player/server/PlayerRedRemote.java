package player.server;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseRed;
import entity.changed.remote.DiceRemote;
import entity.changed.remote.HorseRedRemote;
import graphics.Constant;
import map.EntityPosition;
import player.client.Player;

import java.awt.*;
import java.rmi.RemoteException;

public class PlayerRedRemote extends PlayerRemote {
    public PlayerRedRemote() throws RemoteException {
        Constant.initHorseRed();
        initHorse();
        diceRemote = new DiceRemote(EntityPosition.RED_DICE_PLACE_X, EntityPosition.RED_DICE_PLACE_Y);
        team = TeamType.TEAM_RED;
        initHorse();
    }

    protected void initHorse() {
        for (int i = 0; i < 4; i++) {
            Point point = Constant.redHorseTeam.get(i);
            horses.add(new HorseRedRemote(i, point.x, point.y, this));
        }
    }
}
