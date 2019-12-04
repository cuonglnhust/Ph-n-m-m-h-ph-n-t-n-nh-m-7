package rmi.interfaces;

import constant.DiceValue;
import constant.TeamType;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayGameClient extends Remote {
    public void updateDiceClick(int id) throws RemoteException;

    public void updateDiceValue(int id, DiceValue diceValue) throws RemoteException;

    public void changeTurn() throws RemoteException;

    public void updateVirtualMap(int position, TeamType teamType) throws RemoteException;

    public void updateHorse(int id, int horseId, int position, int rank) throws RemoteException;

    public void updateKickedHorsePosition(int kickedId, int position) throws RemoteException;

    public void updateResultLose() throws RemoteException;
}
