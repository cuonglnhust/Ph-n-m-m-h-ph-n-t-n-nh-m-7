package SCCommon;

import java.io.Serializable;

public class ConnectionData implements Serializable {

    private static final long serialVersionUID = 1L;

    private String ip;
    private int port;
    private String bindName;

    public ConnectionData(String ip, int port, String bindName) {
        this.ip = ip;
        this.port = port;
        this.bindName = bindName;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getBindName() {
        return bindName;
    }
}
