import rmi.RemoteImplementation;
import rmi.RemoteInterface;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MyServer extends RemoteImplementation {
    public static void main(String[] args) {

        try {
            RemoteImplementation remoteImplementation = new RemoteImplementation();
            RemoteInterface stub = (RemoteInterface) UnicastRemoteObject.exportObject(remoteImplementation,0);
            Registry registry = LocateRegistry.createRegistry(5000);
            registry.bind("Server",stub);
            System.err.println("Server Ready");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
