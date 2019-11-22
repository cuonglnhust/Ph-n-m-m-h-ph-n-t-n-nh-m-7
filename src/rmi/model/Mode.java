package rmi.model;

import constant.ModeType;
import rmi.dataLogin.ConnectionData;
import rmi.interfaces.RemoteInterfacePlayer;

import java.rmi.registry.Registry;

public abstract class Mode {
    protected ModeType modeType;
    protected Registry registry;
    protected RemoteInterfacePlayer stub;
    protected ConnectionData connectionData;

    public Mode(ConnectionData connectionData) {
        this.connectionData = connectionData;
    }

    public Registry getRegistry() {
        return registry;
    }

    public RemoteInterfacePlayer getStub() {
        return stub;
    }

    public ModeType getModeType() {
        return modeType;
    }
}
