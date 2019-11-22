package rmi.client;

import constant.ModeType;
import rmi.model.Mode;
import rmi.dataLogin.ConnectionData;

public class ClientLogin extends Mode {

    public ClientLogin(ConnectionData connectionData) {
        super(connectionData);
        modeType = ModeType.CLIENT_LOGIN;
    }

    public boolean connection(){
        return true;
    }

}
