package rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterfaceServer extends Remote {
    public boolean hello() throws RemoteException;
}
