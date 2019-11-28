package player.server;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseBlue;
import entity.changed.remote.DiceRemote;
import entity.changed.remote.HorseBlueRemote;
import graphics.Constant;
import map.EntityPosition;
import player.client.Player;

import java.awt.*;
import java.rmi.RemoteException;

public class PlayerBlueRemote extends PlayerRemote {

    public PlayerBlueRemote() throws RemoteException {
        Constant.initHorseBlue();
        initHorse();
        diceRemote = new DiceRemote(EntityPosition.BLUE_DICE_PLACE_X, EntityPosition.BLUE_DICE_PLACE_Y);
        team = TeamType.TEAM_BLUE;
        initHorse();
    }

    protected void initHorse() {
        for (int i = 0; i < 4; i++) {
            Point point = Constant.blueHorseTeam.get(i);
            horses.add(new HorseBlueRemote(i, point.x, point.y,this));
        }
    }

}
