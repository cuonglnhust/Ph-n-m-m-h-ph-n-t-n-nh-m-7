package server;

import server.RemoteInterface.IServer;
import server.ServerImplement.ServerImp;

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
            Naming.bind("rmi://localhost:9999/hippocampus", iServer);
            System.out.println("Server Ready ...");


        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
