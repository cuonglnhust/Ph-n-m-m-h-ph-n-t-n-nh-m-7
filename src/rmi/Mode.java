package rmi;

import constant.ModeType;

import java.rmi.registry.Registry;

public class Mode {
    protected ModeType modeType;
    protected String bindName;
    protected int port;
    protected Registry registry;
    protected RemoteInterface stub;

    public Mode(String bindName, int port) {
        this.bindName = bindName;
        this.port = port;
    }

    public Registry getRegistry() {
        return registry;
    }

    public RemoteInterface getStub() {
        return stub;
    }

    public ModeType getModeType() {
        return modeType;
    }
}
