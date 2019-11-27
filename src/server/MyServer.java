package server;

import client.RemoteInterface.Match;
import server.RemoteInterface.IServer;
import server.ServerImplement.ServerImp;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    public static void main(String[] args) throws SQLException {

        try {

            LocateRegistry.createRegistry(9999);
            IServer iServer = new ServerImp();
            Naming.bind("rmi://localhost:9999/hippocampus", iServer);
            System.out.println("Server Ready ...");

            while (true)
            {
                List<Match> listMatch = new ArrayList<>();
                iServer.senIdMatch(listMatch);
            }
        } catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
