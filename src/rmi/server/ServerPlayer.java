package rmi.server;

import constant.ModeType;
import rmi.Mode;

public class ServerPlayer extends Mode {
    public ServerPlayer(String bindName, int port) {
        super(bindName, port);
        modeType = ModeType.SERVER_PLAYER;
    }
}
