package rmi.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterfaceLogin extends Remote {

    public void sendInvite(int playerId) throws RemoteException;

}
