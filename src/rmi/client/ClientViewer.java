package rmi.client;

import constant.ModeType;
import SCCommon.ConnectionData;
import rmi.implementation.WatchMatchClientImp;
import rmi.implementation.WatchMatchServerImp;
import rmi.interfaces.WatchMatchServer;
import rmi.model.ModeViewer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class ClientViewer extends ModeViewer {

    private String url;
    private WatchMatchServer watchMatchServer;
    private WatchMatchClientImp watchMatchClientImp;

    public ClientViewer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_VIEWER;
    }

    public void connection() {
        try {
            url = "rmi://" + connectionData.getIp() + ":" + connectionData.getPort() + "/";
            watchMatchServer = (WatchMatchServer) Naming.lookup(url + "watch");
            watchMatchClientImp = new WatchMatchClientImp(watchMatchServer);
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public WatchMatchServer getWatchMatchServer() {
        return watchMatchServer;
    }

    public WatchMatchClientImp getWatchMatchClientImp() {
        return watchMatchClientImp;
    }
}
