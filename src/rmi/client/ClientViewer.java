package rmi.client;

import constant.ModeType;
import rmi.model.Mode;
import rmi.dataLogin.ConnectionData;
import rmi.model.ModeViewer;

public class ClientViewer extends ModeViewer {
    public ClientViewer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_VIEWER;
    }

}
