package rmi.server;

import constant.ModeType;
import rmi.Mode;

public class ServerViewer extends Mode {

    public ServerViewer(String bindName, int port) {
        super(bindName, port);
        modeType = ModeType.SERVER_VIEWER;
    }
}
