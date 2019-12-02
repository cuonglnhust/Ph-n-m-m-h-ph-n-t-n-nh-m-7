package rmi.client;

import constant.ModeType;
import SCCommon.ConnectionData;
import rmi.model.ModeViewer;

public class ClientViewer extends ModeViewer {
    public ClientViewer(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_VIEWER;
    }

}
