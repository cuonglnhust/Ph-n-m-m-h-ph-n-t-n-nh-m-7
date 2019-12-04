package rmi.implementation;

import constant.DiceValue;
import constant.TeamType;
import entity.changed.data.HorseData;
import main.Handler;
import map.local.MapTemp;
import rmi.interfaces.PlayGameClient;
import rmi.interfaces.PlayGameServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class PlayGameClientImp extends UnicastRemoteObject implements PlayGameClient {

    private MapTemp mapTemp;

    public PlayGameClientImp(int id, PlayGameServer playGameServer) throws RemoteException {
        playGameServer.register(id, this);
    }

    @Override
    public void updateDiceClick(int id) throws RemoteException {
        // thay đổi dice.click
        mapTemp.getPlayerCopyHashMap().get(id).getDiceCopy().setClick(true);
    }

    @Override
    public void updateDiceValue(int id, DiceValue diceValue) throws RemoteException {
        // thay đổi diveValue
        mapTemp.getPlayerCopyHashMap().get(id).getDiceCopy().setDiceValue(diceValue);
    }

    @Override
    public void changeTurn() throws RemoteException {
        // bật cờ đổi lượt
        mapTemp.setChangeTurn(true);
    }

    // cập nhật vị trí cho Map
    @Override
    public void updateVirtualMap(int position, TeamType teamType) throws RemoteException {
        mapTemp.updateVirtualMap(position, teamType);
    }

    // cập nhật vị trí ngựa
    @Override
    public void updateHorse(int id, int horseId, int position, int rank) throws RemoteException {
        // lấy ra player từ id, lấy ra horse từ horseId, cập nhật
        mapTemp.getPlayerCopyHashMap().get(id).setHorseData(new HorseData(horseId, position, rank));
    }

    @Override
    public void updateKickedHorsePosition(int kickedId, int position) throws RemoteException {
        // nếu là Horse của mình
        if (kickedId == Handler.getInstance().getId()) {
            mapTemp.getPlayer().setKick(true);
            mapTemp.getPlayer().setKickedPosition(position);
        }
        // nếu không
        else {
            mapTemp.getPlayerCopyHashMap().get(kickedId).setKick(true);
            mapTemp.getPlayerCopyHashMap().get(kickedId).setKickedPosition(position);
        }
    }

    @Override
    public void updateResultLose() throws RemoteException {
        // cập nhật biến trong Map
        mapTemp.setLose(true);
    }

    public void setMapTemp(MapTemp mapTemp) {
        this.mapTemp = mapTemp;
    }
}
