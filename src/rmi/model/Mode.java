package rmi.model;

import constant.ModeType;
import SCCommon.ConnectionData;

import java.rmi.registry.Registry;

public abstract class Mode {
    protected ModeType modeType;
    protected Registry registry;
    protected ConnectionData connectionData;

    public Mode(ConnectionData connectionData) {
        this.connectionData = connectionData;
    }

    public Registry getRegistry() {
        return registry;
    }

    public ModeType getModeType() {
        return modeType;
    }
}
