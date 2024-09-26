package rmi.interfaces;

import constant.DiceValue;
import constant.TeamType;
import map.local.MapCopy;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface WatchMatchClient extends Remote {

    // cập nhật click dice
    public void updateDiceShake(int id) throws RemoteException;

    // cập nhật giá trị dice
    public void updateDiceValue(int id, DiceValue diceValue) throws RemoteException;

    // cập nhật vị trí ngựa
    public void updateHorseState(int idPlayer, int idHorse, int position, int rank) throws RemoteException;

    // cập nhật lượt
    public void changeTurn(TeamType teamType) throws RemoteException;

    // cập nhật ngựa bị đá
    public void updateKickedHorse(int idPlayer, int position) throws RemoteException;

    // cập nhật kết quả trận đấu
    public void updateResult(int id) throws RemoteException;
}