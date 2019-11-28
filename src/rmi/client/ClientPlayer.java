package rmi.client;

import constant.ModeType;
import main.Handler;
import rmi.dataLogin.ConnectionData;
import rmi.implementation.ChoseTeamImpClient;
import rmi.interfaces.RemoteInterfaceServer;
import rmi.model.ModePlayer;
import state.client.ChoseTeamServer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClientPlayer extends ModePlayer {

    private ChoseTeamServer choseTeamServer;
    private ChoseTeamImpClient choseTeamImpClient;
    private String url;

    public ClientPlayer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_PLAYER;
    }

    public boolean connection() {
        try {

            registry = LocateRegistry.getRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":"
                    + connectionData.getPort() + "/";
            RemoteInterfaceServer stub = (RemoteInterfaceServer) registry.lookup(url + connectionData.getBindName());
            choseTeamServer = (ChoseTeamServer) registry.lookup(url + "choseTeam");
            choseTeamImpClient = new ChoseTeamImpClient(Handler.getInstance().getId(), choseTeamServer);
            return stub.hello();
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ChoseTeamServer getChoseTeamServer() {
        return choseTeamServer;
    }

    public ChoseTeamImpClient getChoseTeamImpClient() {
        return choseTeamImpClient;
    }

    public void setChoseTeamServer(ChoseTeamServer choseTeamServer) {
        this.choseTeamServer = choseTeamServer;
    }

    public void setChoseTeamImpClient(ChoseTeamImpClient choseTeamImpClient) {
        this.choseTeamImpClient = choseTeamImpClient;
    }

    public String getUrl() {
        return url;
    }
}
