package rmi.server;

import constant.ModeType;
import rmi.model.Mode;
import rmi.dataLogin.ConnectionData;
import rmi.implementation.RemoteImplementationPlayer;
import rmi.interfaces.RemoteInterfacePlayer;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ServerPlayer extends Mode {
    public ServerPlayer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.SERVER_PLAYER;
    }

    private void openServer() {
        try {
            RemoteImplementationPlayer remoteImplementationPlayer = new RemoteImplementationPlayer();
            stub = (RemoteInterfacePlayer) UnicastRemoteObject.exportObject(remoteImplementationPlayer, 0);
            registry = LocateRegistry.createRegistry(connectionData.getPort());
            registry.bind("rmi://" + connectionData.getIp() + ";" + connectionData.getPort() +
                    "/" + connectionData.getBindName(), stub);
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
