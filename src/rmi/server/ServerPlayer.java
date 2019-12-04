package rmi.server;

import constant.ModeType;
import rmi.implementation.ChoseTeamServerImp;
import SCCommon.ConnectionData;
import rmi.implementation.PlayGameServerImp;
import rmi.model.ModePlayer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ServerPlayer extends ModePlayer {

    private String url;
    private ChoseTeamServerImp choseTeamServerImp;
    private PlayGameServerImp playGameServerImp;


    public ServerPlayer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.SERVER_PLAYER;
    }


    @Override
    public boolean connection() {
        try {
            System.setProperty("java.rmi.server.hostname",connectionData.getIp());
            LocateRegistry.createRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":" + connectionData.getPort() + "/";
            choseTeamServerImp = new ChoseTeamServerImp();
            playGameServerImp = new PlayGameServerImp();
            Naming.bind(url + "choseTeam", choseTeamServerImp);
            Naming.bind(url + "playGame", playGameServerImp);
            System.out.println("Server Ready");
            return true;
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public ChoseTeamServerImp getChoseTeamServerImp() {
        return choseTeamServerImp;
    }

    public PlayGameServerImp getPlayGameServerImp() {
        return playGameServerImp;
    }
}
