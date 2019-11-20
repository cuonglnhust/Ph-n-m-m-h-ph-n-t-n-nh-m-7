package rmi.client;

import constant.ModeType;
import rmi.Mode;

public class ClientPlayer extends Mode {

    public ClientPlayer(String bindName, int port) {
        super(bindName, port);
        modeType = ModeType.CLIENT_PLAYER;
    }

}
