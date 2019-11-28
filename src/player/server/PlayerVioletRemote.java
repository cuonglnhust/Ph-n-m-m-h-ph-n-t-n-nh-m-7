package player.server;

import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.HorseViolet;
import entity.changed.remote.DiceRemote;
import entity.changed.remote.HorseVioletRemote;
import graphics.Constant;
import map.EntityPosition;
import player.client.Player;

import java.awt.*;
import java.rmi.RemoteException;

public class PlayerVioletRemote extends PlayerRemote {

    public PlayerVioletRemote() throws RemoteException {
        Constant.initHorseViolet();
        initHorse();
        diceRemote = new DiceRemote(EntityPosition.VIOLET_DICE_PLACE_X, EntityPosition.VIOLET_DICE_PLACE_Y);
        team = TeamType.TEAM_VIOLET;
    }

    protected void initHorse() {
        for (int i = 0; i < 4; i++) {
            Point point = Constant.violetHorseTeam.get(i);
            horses.add(new HorseVioletRemote(i, point.x, point.y, this));
        }
    }
}
