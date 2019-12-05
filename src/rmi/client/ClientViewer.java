package rmi.client;

import constant.ModeType;
import SCCommon.ConnectionData;
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

    String url;
    WatchMatchServer watchMatchServer;

    public ClientViewer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_VIEWER;
    }

    public boolean connection(){
        try {
            System.setProperty("java.rmi.server.hostname",connectionData.getIp());
            LocateRegistry.createRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":" + connectionData.getPort() + "/";
            watchMatchServer = (WatchMatchServer) Naming.lookup(url + "watch");
            System.out.println("Server Ready");
            return true;
        } catch (RemoteException | MalformedURLException | NotBoundException e) {
            e.printStackTrace();
            return false;
        }
    }

}
