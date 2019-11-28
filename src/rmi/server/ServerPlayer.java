package rmi.server;

import constant.ModeType;
import rmi.implementation.ChoseTeamImpServer;
import rmi.implementation.RemoteImpServer;
import rmi.dataLogin.ConnectionData;
import rmi.model.ModePlayer;
import state.ChoseTeamState;
import state.client.ChoseTeamServer;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ServerPlayer extends ModePlayer {

    private String url;
    private ChoseTeamImpServer choseTeamImpServer;


    public ServerPlayer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.SERVER_PLAYER;
    }


    @Override
    public boolean connection() {
        try {
            registry = LocateRegistry.createRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":" + connectionData.getPort() + "/";

            RemoteImpServer server = new RemoteImpServer();
            choseTeamImpServer = new ChoseTeamImpServer();

            registry.rebind(url + connectionData.getBindName(), server);
            registry.rebind(url + "choseTeam", choseTeamImpServer);

            System.out.println("Server Ready");

            return true;
        } catch (RemoteException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUrl() {
        return url;
    }

    public ChoseTeamImpServer getChoseTeamImpServer() {
        return choseTeamImpServer;
    }

    public void setChoseTeamImpServer(ChoseTeamImpServer choseTeamImpServer) {
        this.choseTeamImpServer = choseTeamImpServer;
    }
}
