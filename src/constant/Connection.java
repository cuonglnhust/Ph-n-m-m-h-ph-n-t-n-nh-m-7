package constant;

import SCCommon.ConnectionData;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Connection {

    public static ConnectionData createToPlayer() {    //
        String ip = null;
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ConnectionData connectionData = new ConnectionData(ip, 5000, "conToPlay");
        return connectionData;
    }

    public static ConnectionData createToWatch() {    //
        String ip = null;
        try {
            ip = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        ConnectionData connectionData = new ConnectionData(ip, 6000, "conToWatch");
        return connectionData;
    }


}
