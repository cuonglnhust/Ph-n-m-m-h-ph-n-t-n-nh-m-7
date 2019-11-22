package rmi.dataLogin;

public class ConnectionData {
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
