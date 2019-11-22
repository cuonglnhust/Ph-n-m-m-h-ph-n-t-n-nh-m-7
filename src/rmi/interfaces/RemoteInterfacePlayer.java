package rmi.interfaces;

import constant.TeamType;
import entity.changed.Dice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterfacePlayer extends Remote {

    public boolean hello() throws RemoteException;

    public void diceClick(TeamType teamType) throws RemoteException;

    public void updateDice(Dice dice, TeamType teamType) throws RemoteException;



}
