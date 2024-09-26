package rmi.implementation;

import button.PlayerData;
import constant.DiceValue;
import constant.TeamType;
import entity.changed.data.HorseData;
import entity.changed.local.Horse;
import entity.changed.remote.HorseCopy;
import main.Handler;
import map.local.MapTemp;
import rmi.interfaces.WatchMatchClient;
import rmi.interfaces.WatchMatchServer;
import state.GameState;
import state.State;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WatchMatchServerImp extends UnicastRemoteObject implements WatchMatchServer {

    private HashMap<Integer, WatchMatchClient> clientHashMap;
    private MapTemp mapTemp;


    public WatchMatchServerImp() throws RemoteException {
        this.clientHashMap = new HashMap<>();
    }

    @Override
    public boolean hello() throws RemoteException {
        return State.getCurrentState() instanceof GameState;
    }

    @Override
    public void register(int id, WatchMatchClient watchMatchClient) throws RemoteException {
        clientHashMap.put(id, watchMatchClient);
    }

    @Override
    public List<PlayerData> getPlayerDataList() throws RemoteException {
        return mapTemp.getPlayerDataList();
    }

    // lấy giá trị dice của một đội có id
    @Override
    public DiceValue getPlayerDiceValue(int id) throws RemoteException {
        // nếu đội đó là ServerPlayer
        if (id == Handler.getInstance().getId()) {
            return mapTemp.getPlayer().getDice().getDiceValue();
        }
        // nếu đội đó là ClientPlayer
        else {
            return mapTemp.getPlayerCopyHashMap().get(id).getDiceCopy().getDiceValue();
        }
    }

    // lấy danh sách trạng thái ngựa của một đội
    @Override
    public HashMap<Integer, HorseData> getPlayerHorseList(int id) throws RemoteException {
        HashMap<Integer, HorseData> horseDataHashMap = new HashMap<>();
        // nếu là đội của ServerPlayer
        if (id == Handler.getInstance().getId()) {
            for (Horse horse : mapTemp.getPlayer().getHorses()) {
                HorseData horseData = new HorseData(horse.getId(), horse.getPosition(), horse.getRank());
                horseDataHashMap.put(horse.getId(), horseData);
            }
        }
        // nếu là đội của ClientPLayer
        else {
            for (HorseCopy horseCopy : mapTemp.getPlayerCopyHashMap().get(id).getHorseCopies()) {
                HorseData horseData = new HorseData(horseCopy.getId(), horseCopy.getPosition(), horseCopy.getRank());
                horseDataHashMap.put(horseCopy.getId(), horseData);
            }
        }

        return horseDataHashMap;
    }

    // quảng bá click dice
    public void updateDiceClick(int id) {
        ArrayList<WatchMatchClient> clients = new ArrayList<>(clientHashMap.values());
        for (WatchMatchClient watchMatchClient : clients) {
            try {
                watchMatchClient.updateDiceShake(id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // quảng bá diceValue
    public void updateDiceValue(int id, DiceValue diceValue) {
        ArrayList<WatchMatchClient> clients = new ArrayList<>(clientHashMap.values());
        for (WatchMatchClient watchMatchClient : clients) {
            try {
                watchMatchClient.updateDiceValue(id, diceValue);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // quảng bá đổi lượt
    public void changeTurn(TeamType teamType) {
        ArrayList<WatchMatchClient> clients = new ArrayList<>(clientHashMap.values());
        for (WatchMatchClient watchMatchClient : clients) {
            try {
                watchMatchClient.changeTurn(teamType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // quảng bá cập nhật vị trí ngựa
    public void updateHorsePosition(int idPlayer, int idHorse, int position, int rank) {
        ArrayList<WatchMatchClient> clients = new ArrayList<>(clientHashMap.values());
        for (WatchMatchClient watchMatchClient : clients) {
            try {
                watchMatchClient.updateHorseState(idPlayer, idHorse, position, rank);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // quảng bá cập nhật ngựa bị đá
    public void updateKickedHorsePosition(int idPlayer, int position) {
        ArrayList<WatchMatchClient> clients = new ArrayList<>(clientHashMap.values());
        for (WatchMatchClient watchMatchClient : clients) {
            try {
                watchMatchClient.updateKickedHorse(idPlayer, position);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // quảng bá kết quả trận đấu
    public void updateResult(int id) {
        ArrayList<WatchMatchClient> clients = new ArrayList<>(clientHashMap.values());
        for (WatchMatchClient watchMatchClient : clients) {
            try {
                watchMatchClient.updateResult(id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void setMapTemp(MapTemp mapTemp) {
        this.mapTemp = mapTemp;
    }
}
