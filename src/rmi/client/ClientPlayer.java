package rmi.client;

import constant.ModeType;
import rmi.model.Mode;
import rmi.dataLogin.ConnectionData;
import rmi.interfaces.RemoteInterfacePlayer;
import rmi.model.ModePlayer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClientPlayer extends ModePlayer {

    public ClientPlayer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_PLAYER;
    }

    public boolean connection() {
        try {
            registry = LocateRegistry.getRegistry(connectionData.getPort());
            stub = (RemoteInterfacePlayer) registry.lookup("rmi://" + connectionData.getIp() + ":"
                    + connectionData.getPort() + "/" + connectionData.getBindName());
            return stub.hello();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
