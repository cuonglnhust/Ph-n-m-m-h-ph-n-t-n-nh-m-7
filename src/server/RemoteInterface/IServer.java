package server.RemoteInterface;

import client.RemoteInterface.Player;
import client.RemoteInterface.IClient;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IServer extends Remote {


    void sendInvitation(Player player1, Player player2) throws RemoteException;  // player1 gửi lời mời sang player2

    int signIn(String username, String password) throws RemoteException, SQLException; // đăng nhập


    void registerClient(int playerId,IClient iClient) throws RemoteException; // sau khi đăng nhập thành công thì player
                                                                                // sẽ là onlie và được thêm vào Map này

    void msgToServer(String msg) throws RemoteException; // gửi thông báo từ client lên server

    List<Player> getPlayersOnline(Player player1) throws RemoteException; // lấy các player đang online

}
