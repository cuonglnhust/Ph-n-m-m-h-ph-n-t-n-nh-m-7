package rmi.client;

import Login.Login;
import constant.ModeType;
import main.Handler;
import player.client.Player;
import rmi.implementation.ChoseTeamImpClient;
import rmi.implementation.SendIdPlayerImp;
import rmi.interfaces.RemoteInterfaceServer;
import rmi.model.Mode;
import rmi.dataLogin.ConnectionData;
import state.client.ChoseTeamServer;
import state.server.IServer;

import java.net.UnknownHostException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

public class ClientLogin extends Mode {
    private String url;
    private rmi.dataLogin.Player GetPlayerServer;
    //private IServer iServer;
    private SendIdPlayerImp sendIdPlayerImp;

    public ClientLogin(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_LOGIN;
    }

    public boolean connection(String UserName, String Password) throws SQLException {
        try {

            registry = LocateRegistry.getRegistry(connectionData.getPort());
            url = "rmi://" + connectionData.getIp() + ":"
                    + connectionData.getPort() + "/";
            IServer stub = (IServer) registry.lookup(url + connectionData.getBindName());
            GetPlayerServer = stub.signIn(UserName,Password);
            if (GetPlayerServer != null)
            {
                Handler.getInstance().setId(GetPlayerServer.getPid());
                Handler.getInstance().setName(GetPlayerServer.getPname());
                return  true;

            }
            IServer sigin = (IServer) registry.lookup(url + connectionData.getBindName());
            sendIdPlayerImp = new SendIdPlayerImp(GetPlayerServer,sigin);



            return false;
        } catch (RemoteException | NotBoundException | UnknownHostException e) {
            e.printStackTrace();
            return false;
        }

    }

    public String getUrl() {
        return url;
    }
    public  rmi.dataLogin.Player getPlayer() {return GetPlayerServer;}

}
