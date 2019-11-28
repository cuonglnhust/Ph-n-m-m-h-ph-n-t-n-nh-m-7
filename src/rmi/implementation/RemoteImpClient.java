package rmi.implementation;

import constant.TeamType;
import entity.changed.local.Dice;
import rmi.interfaces.RemoteInterfaceClient;

import java.rmi.RemoteException;

public class RemoteImpClient implements RemoteInterfaceClient {
    @Override
    public boolean hello() throws RemoteException {
        System.out.println("Hello");
        return false;
    }

}
