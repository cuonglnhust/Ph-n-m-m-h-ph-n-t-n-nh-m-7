package rmi.interfaces;

import constant.TeamType;
import entity.changed.local.Dice;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterfaceClient extends Remote {

    public boolean hello() throws RemoteException;





}
