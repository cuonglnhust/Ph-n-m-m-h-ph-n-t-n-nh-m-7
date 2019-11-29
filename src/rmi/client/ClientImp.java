package rmi.client;

import rmi.dataLogin.Player;
import state.server.IServer;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientImp extends UnicastRemoteObject implements IClient, Serializable {
    private Player player;

    private InetAddress ip = InetAddress.getLocalHost();

    public ClientImp(Player player, IServer iServer) throws RemoteException, UnknownHostException {

        this.player = player;

        iServer.registerClient(this.player.getPid(),this);

        String msg =  player.getPname() + " have ip : " + this.ip.toString() + " connected !";

        iServer.msgToServer(msg);
    }


    @Override
    public boolean responseInvitation(IClient iClient) throws RemoteException {
        return false;
    }

    @Override
    public String retrieveIp(String player1Ip) throws RemoteException {
        return player1Ip;
    }

    @Override
    public String getIpAddress() throws RemoteException {
        return ip.toString();
    }

    @Override
    public Player getPlayer() {
        return player;
    }
}
