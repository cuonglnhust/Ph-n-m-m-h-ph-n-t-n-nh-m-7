package rmi.implementation;

import SCCommon.ConnectionData;
import SCCommon.IClient;
import SCCommon.Player;
import SCCommon.IServer;
import state.ChoseTeamState;
import state.HomeState;
import state.State;

import javax.swing.*;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class IClientImp extends UnicastRemoteObject implements IClient, Serializable {

    private Player player;
    private String clientIp;
    private HomeState homeState;

    public IClientImp(Player player, IServer iServer) throws RemoteException, UnknownHostException {
        this.player = player;
        clientIp = InetAddress.getLocalHost().getHostAddress();
        iServer.registerClient(this.player.getPid(), this);
        String msg = player.getPname() + " have ip : " + this.clientIp.toString() + " connected !";
        iServer.msgToServer(msg);
    }

    @Override
    public boolean responseInvitation(IClient iClient) throws RemoteException {
        int input = JOptionPane.showConfirmDialog(null, iClient.getPlayer().getPname().toUpperCase() +
                        " vừa mời bạn chơi cùng, Bạn có muốn chơi với " + iClient.getPlayer().getPname().toUpperCase() + " ?", "Accept Invitation",
                JOptionPane.YES_NO_OPTION);
        if (input == JOptionPane.YES_OPTION) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void setConnectDataForPlayer2(ConnectionData connectionData) throws RemoteException {
        State.setCurrentState(new ChoseTeamState(connectionData));
    }

    @Override
    public String retrieveIp(String player1Ip) throws RemoteException {
        return player1Ip;
    }

    @Override
    public String getIpAddress() throws RemoteException {
        return clientIp;
    }

    @Override
    public Player getPlayer() throws RemoteException {
        return player;
    }

    @Override
    public void updateData() throws RemoteException {
        homeState.setUpdateData(true);

    }

    public void setHomeState(HomeState homeState) {
        this.homeState = homeState;
    }
}
