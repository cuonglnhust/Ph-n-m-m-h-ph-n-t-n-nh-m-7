package rmi.model;

import rmi.dataLogin.ConnectionData;

public abstract class ModePlayer extends Mode {

    public ModePlayer(ConnectionData connectionData) {
        super(connectionData);
    }

    public abstract boolean connection();
}
