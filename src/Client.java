import rmi.RemoteImplementation;
import rmi.RemoteInterface;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(5000);
            RemoteInterface stub = (RemoteInterface) registry.lookup("Server");

            stub.hello();

            stub.invite(3);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
