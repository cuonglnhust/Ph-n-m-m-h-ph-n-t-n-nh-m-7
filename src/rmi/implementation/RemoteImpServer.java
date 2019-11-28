package rmi.implementation;

import rmi.interfaces.RemoteInterfaceServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteImpServer extends UnicastRemoteObject implements RemoteInterfaceServer {
    public RemoteImpServer() throws RemoteException {

    }

    @Override
    public boolean hello() {
        System.out.println("Hello");
        return true;
    }
}
