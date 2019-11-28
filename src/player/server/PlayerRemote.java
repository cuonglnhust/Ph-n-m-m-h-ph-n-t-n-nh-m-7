package player.server;

import constant.DiceValue;
import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.Horse;
import entity.changed.remote.DiceRemote;
import entity.changed.remote.HorseRemote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerRemote extends UnicastRemoteObject implements PlayerServerInterface {
    // ngựa của người chơi
    protected ArrayList<HorseRemote> horses;
    // Xúc xắc
    protected DiceRemote diceRemote;
    // đội màu gì
    protected TeamType team;

    // ngựa trên bản đồ
    private HashMap<Integer, Horse> onCourt = new HashMap<>();

    // ngựa trên Rank
    private HashMap<Integer, Horse> onRank = new HashMap<>();

    // ngựa di chuyển được
    private ArrayList<Horse> horseCanMove = new ArrayList<>();

    public PlayerRemote() throws RemoteException {

    }


    @Override
    public void setDiceValueDefault() {
        diceRemote.updateDiceValue(DiceValue.NONE);
    }

    @Override
    public void updateDiceClick( TeamType teamType) {
        diceRemote.updateAnimation();
    }

    @Override
    public void updateDiceValue(TeamType teamType) {

    }

    @Override
    public void updateChangeTurn(TeamType teamType) {

    }

    @Override
    public void updateHorse() {

    }

    @Override
    public void updateVirtualMap() {

    }
}
