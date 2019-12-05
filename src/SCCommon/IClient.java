package SCCommon;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IClient extends Remote {
    boolean responseInvitation(IClient iClient) throws RemoteException; // gửi lại thông báo cho người gửi player1

    String retrieveIp(String player1Ip) throws RemoteException; // người chơi player2 lấy ip của người chơi player1
    // param là ip của player1

    String getIpAddress() throws RemoteException;               // lấy ip address từ một client

    Player getPlayer() throws RemoteException;                  // lấy đối tượng player từ một client

    void setConnectDataForPlayer2(ConnectionData connectionData) throws RemoteException;

    void updateData() throws RemoteException;

}