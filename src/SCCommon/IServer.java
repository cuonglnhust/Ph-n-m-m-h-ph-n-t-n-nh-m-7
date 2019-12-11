package SCCommon;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IServer extends Remote {

    boolean sendInvitation(Player player1, Player player2, ConnectionData connectionData) throws RemoteException;  // player1 gửi lời mời sang player2

    Player signIn(String username, String password) throws RemoteException, SQLException; // đăng nhập

    void registerClient(int playerId, IClient iClient) throws RemoteException; // sau khi đăng nhập thành công thì player

    // sẽ là onlie và được thêm vào Map này
    void msgToServer(String msg) throws RemoteException; // gửi thông báo từ client lên server

    List<Player> getPlayersOnline(Player player1) throws RemoteException; // lấy các player đang online mà khác player1

    List<Match> getMatchsOnline() throws RemoteException; // hàm trả về trận đấu đan diễn ra

    List<Match> getMatchHistory(int playerId1) throws RemoteException, SQLException; // hàm trả về lịch sử các trận đấu mà player1 đã chơi

    void sendMatchtoServer(int idMatch, Match match, Player player2, ConnectionData connectionData) throws RemoteException;

    void updateMatchHistory(int idMatch, Match match) throws RemoteException;

    // tạo một máy chủ để xem
    public void createNewViewServer(int id, ConnectionData connectionData) throws RemoteException;

    // hủy máy chủ xem
    public void removeViewServer(int id) throws RemoteException;

    ConnectionData requestViewMatch(int id) throws RemoteException;
}
