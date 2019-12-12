package rmi.server;

import constant.ModeType;
import rmi.implementation.ChoseTeamServerImp;
import rmi.implementation.PlayGameServerImp;
import rmi.implementation.WatchMatchServerImp;
import rmi.model.Mode;
import SCCommon.ConnectionData;
import rmi.model.ModeViewer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ServerViewer extends ModeViewer {

    String url;
    WatchMatchServerImp watchMatchServerImp;

    public ServerViewer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.SERVER_VIEWER;
    }

    public boolean connection(){
        try {
            System.setProperty("java.rmi.server.hostname",connectionData.getIp());
            LocateRegistry.createRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":" + connectionData.getPort() + "/";
            watchMatchServerImp = new WatchMatchServerImp();
            Naming.bind(url + "watch", watchMatchServerImp);
            System.out.println("Server Ready");
            return true;
        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public WatchMatchServerImp getWatchMatchServerImp() {
        return watchMatchServerImp;
    }
}
