package rmi.client;

import constant.ModeType;
import rmi.Mode;

public class ClientViewer extends Mode {
    public ClientViewer(String bindName, int port) {
        super(bindName, port);
        modeType = ModeType.CLIENT_VIEWER;
    }
}
