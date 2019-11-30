package rmi.client;

import SCCommon.Player;
import constant.ModeType;
import main.Handler;
import rmi.implementation.SendIdPlayerImp;
import rmi.model.Mode;
import rmi.dataLogin.ConnectionData;
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
    //private IServer iServer;
    private SendIdPlayerImp sendIdPlayerImp;

    public ClientLogin(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_LOGIN;
    }

    public boolean connection(String UserName, String Password) throws SQLException {
        try {

            //registry = LocateRegistry.getRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":"
                    + connectionData.getPort() + "/";
            IServer stub = (IServer) Naming.lookup(url + connectionData.getBindName());
            GetPlayerServer = stub.signIn(UserName,Password);
            if (GetPlayerServer != null)
            {
                Handler.getInstance().setId(GetPlayerServer.getPid());
                Handler.getInstance().setName(GetPlayerServer.getPname());
                sendIdPlayerImp = new SendIdPlayerImp(GetPlayerServer,stub);
                return  true;

            }
            //IServer sigin = (IServer) registry.lookup(url + connectionData.getBindName());




            return false;
        } catch (RemoteException | NotBoundException | UnknownHostException | MalformedURLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getUrl() {
        return url;
    }
    public Player getPlayer() {return GetPlayerServer;}

}
