package rmi.client;

import SCCommon.Player;
import constant.ModeType;
import main.Handler;
import rmi.implementation.IClientImp;
import rmi.model.Mode;
import SCCommon.ConnectionData;
import SCCommon.IServer;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;

public class ClientLogin extends Mode {
    private String url;
    private Player GetPlayerServer;
    private IServer iServer;
    private IClientImp IClientImp;

    public ClientLogin(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_LOGIN;
    }

    public boolean connection(String UserName, String Password) throws SQLException {
        try {

            //registry = LocateRegistry.getRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":"
                    + connectionData.getPort() + "/";
            iServer = (IServer) Naming.lookup(url + connectionData.getBindName());
            GetPlayerServer = iServer.signIn(UserName, Password);
            if (GetPlayerServer != null) {
                Handler.getInstance().setId(GetPlayerServer.getPid());
                Handler.getInstance().setName(GetPlayerServer.getPname());
                IClientImp = new IClientImp(GetPlayerServer, iServer);
                return true;
            }

            return false;
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getUrl() {
        return url;
    }

    public Player getPlayer() {
        return GetPlayerServer;
    }

    public IServer getiServer() {
        return iServer;
    }

    public rmi.implementation.IClientImp getIClientImp() {
        return IClientImp;
    }
}
