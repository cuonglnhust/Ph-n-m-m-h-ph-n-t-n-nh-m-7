package rmi.server;

import constant.ModeType;
import rmi.implementation.ChoseTeamServerImp;
import SCCommon.ConnectionData;
import rmi.implementation.PlayGameServerImp;
import rmi.model.ModePlayer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

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
            registry = LocateRegistry.createRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":" + connectionData.getPort() + "/";
            choseTeamServerImp = new ChoseTeamServerImp();
            playGameServerImp = new PlayGameServerImp();
            System.setProperty("java.rmi.server.hostname",connectionData.getIp());
            registry.rebind(url + "choseTeam", choseTeamServerImp);
            registry.rebind(url + "playGame", playGameServerImp);
            System.out.println("Server Ready");
            return true;
        } catch (RemoteException e) {
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
