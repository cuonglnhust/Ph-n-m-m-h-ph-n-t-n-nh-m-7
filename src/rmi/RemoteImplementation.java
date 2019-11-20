package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public class RemoteImplementation implements RemoteInterface {

    @Override
    public void hello() throws RemoteException {
        System.out.println("Hello");
    }
}
