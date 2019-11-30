package state.server;

import SCCommon.IServer;
import state.server.ServerImplement.ServerImp;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;

public class MyServer {
    public static void main(String[] args) throws SQLException {

        try {

            LocateRegistry.createRegistry(9999);
            IServer iServer = new ServerImp();
            Naming.bind("rmi://localhost:9999/abc", iServer);
            System.out.println("Server Ready ...");


        } catch (RemoteException | AlreadyBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
