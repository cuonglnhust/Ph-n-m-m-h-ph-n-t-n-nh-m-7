package rmi.implementation;

import rmi.client.IClient;
import rmi.dataLogin.Player;
import rmi.interfaces.SenIdPlayer;
import state.server.IServer;

import java.io.Serializable;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SendIdPlayerImp extends UnicastRemoteObject implements SenIdPlayer, Serializable {

    private Player player;

    public SendIdPlayerImp(Player player, IServer iServer) throws RemoteException, UnknownHostException {
        this.player = player;
        iServer.registerClient(this.player.getPid(), (IClient) this);
    }
}
