package constant;

import SCCommon.ConnectionData;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Connection {

    public static ConnectionData create(){
        //
        String ip= null;
        try {
            ip = Inet4Address.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ConnectionData connectionData = new ConnectionData("10.0.75.1",5000,"conToPlay");
        return connectionData;
    }
}
