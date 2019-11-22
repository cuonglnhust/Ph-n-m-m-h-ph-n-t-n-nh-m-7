package rmi.implementation;

import constant.TeamType;
import entity.changed.Dice;
import rmi.interfaces.RemoteInterfacePlayer;

import java.rmi.RemoteException;

public class RemoteImplementationPlayer implements RemoteInterfacePlayer {
    @Override
    public boolean hello() throws RemoteException {
        return false;
    }

    @Override
    public void diceClick(TeamType teamType) throws RemoteException {

    }

    @Override
    public void updateDice(Dice dice, TeamType teamType) throws RemoteException {

    }
}
