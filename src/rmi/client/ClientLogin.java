package rmi.client;

import constant.ModeType;
import rmi.Mode;

public class ClientLogin extends Mode {

    public ClientLogin(String bindName, int port) {
        super(bindName, port);
        modeType = ModeType.CLIENT_LOGIN;
    }

}
