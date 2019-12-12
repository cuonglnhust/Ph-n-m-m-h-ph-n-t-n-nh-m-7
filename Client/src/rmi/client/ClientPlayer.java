package rmi.client;

import constant.ModeType;
import main.Handler;
import SCCommon.ConnectionData;
import rmi.implementation.ChoseTeamClientImp;
import rmi.implementation.PlayGameClientImp;
import rmi.interfaces.PlayGameServer;
import rmi.model.ModePlayer;
import rmi.interfaces.ChoseTeamServer;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClientPlayer extends ModePlayer {

    private ChoseTeamServer choseTeamServer;
    private ChoseTeamClientImp choseTeamClientImp;
    private PlayGameServer playGameServer;
    private PlayGameClientImp playGameClientImp;

    private String url;

    public ClientPlayer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_PLAYER;
    }

    public boolean connection() {
        try {
            url = "rmi://" + connectionData.getIp() + ":"
                    + connectionData.getPort() + "/";
            choseTeamServer = (ChoseTeamServer) Naming.lookup(url + "choseTeam");
            playGameServer = (PlayGameServer) Naming.lookup(url + "playGame");
            choseTeamClientImp = new ChoseTeamClientImp(Handler.getInstance().getId(), choseTeamServer);
            playGameClientImp = new PlayGameClientImp(Handler.getInstance().getId(),playGameServer);
            return choseTeamServer.hello();
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ChoseTeamServer getChoseTeamServer() {
        return choseTeamServer;
    }

    public ChoseTeamClientImp getChoseTeamClientImp() {
        return choseTeamClientImp;
    }

    public void setChoseTeamServer(ChoseTeamServer choseTeamServer) {
        this.choseTeamServer = choseTeamServer;
    }

    public void setChoseTeamClientImp(ChoseTeamClientImp choseTeamClientImp) {
        this.choseTeamClientImp = choseTeamClientImp;
    }

    public PlayGameServer getPlayGameServer() {
        return playGameServer;
    }

    public PlayGameClientImp getPlayGameClientImp() {
        return playGameClientImp;
    }

    public String getUrl() {
        return url;
    }
}
