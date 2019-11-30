package rmi.implementation;

import SCCommon.IClient;
import SCCommon.Player;
import rmi.interfaces.SenIdPlayer;
import SCCommon.IServer;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SendIdPlayerImp extends UnicastRemoteObject implements IClient, Serializable {

    private Player player;

    private InetAddress clientIp ;

    public SendIdPlayerImp(Player player, IServer iServer) throws RemoteException, UnknownHostException {

        this.player = player;
        clientIp = InetAddress.getLocalHost();

        iServer.registerClient(this.player.getPid(), this);

        String msg =  player.getPname() + " have ip : " + this.clientIp.toString() + " connected !";

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
        return clientIp.toString();
    }

    @Override
    public Player getPlayer() throws RemoteException {
        return player;
    }
}
