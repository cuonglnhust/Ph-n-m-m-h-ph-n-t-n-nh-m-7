package rmi.implementation;

import constant.DiceValue;
import constant.TeamType;
import entity.changed.data.HorseData;
import main.Handler;
import map.local.MapCopy;
import rmi.interfaces.WatchMatchClient;
import rmi.interfaces.WatchMatchServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WatchMatchClientImp extends UnicastRemoteObject implements WatchMatchClient {

    private MapCopy mapCopy;

    public WatchMatchClientImp(WatchMatchServer watchMatchServer) throws RemoteException {
        watchMatchServer.register(Handler.getInstance().getId(), this);
    }

    // cập nhật Dice click
    @Override
    public void updateDiceShake(int id) throws RemoteException {
        mapCopy.getPlayerCopyHashMap().get(id).getDiceCopy().setClick(true);
    }

    // cập nhật giá trị diceValue
    @Override
    public void updateDiceValue(int id, DiceValue diceValue) throws RemoteException {
        mapCopy.getPlayerCopyHashMap().get(id).getDiceCopy().setDiceValue(diceValue);
    }


    // cập nhật vị trí ngựa
    @Override
    public void updateHorseState(int idPlayer, int idHorse, int position, int rank) throws RemoteException {
        mapCopy.getPlayerCopyHashMap().get(idPlayer).setHorseData(new HorseData(idHorse, position, rank));
    }

    @Override
    public void changeTurn(TeamType teamType) throws RemoteException {
        mapCopy.setTurn(teamType);
    }

    @Override
    public void updateKickedHorse(int idPlayer, int position) throws RemoteException {
        mapCopy.getPlayerCopyHashMap().get(idPlayer).setKick(true);
        mapCopy.getPlayerCopyHashMap().get(idPlayer).setKickedPosition(position);
    }

    @Override
    public void updateResult(int id) throws RemoteException {
        mapCopy.setEnd(true);
    }

    public void setMapCopy(MapCopy mapCopy) {
        this.mapCopy = mapCopy;
    }
}
