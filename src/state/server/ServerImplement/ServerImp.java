package state.server.ServerImplement;

import SCCommon.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.*;

public class ServerImp extends UnicastRemoteObject implements IServer {

    public Map<Integer, IClient> playerOnline;

    public Map<Integer, Match> matches;

    public ArrayList<Integer> playerOnMatch;

    // thông tin các máy online
    public HashMap<Integer, ConnectionData> viewServer;

    private ConnectDatabase connectDatabase = null;

    public ServerImp() throws RemoteException {
        this.playerOnline = new HashMap<>();
        this.matches = new HashMap<>();
        this.viewServer = new HashMap<>();
        this.playerOnMatch = new ArrayList<>();
    }

    // xử lí lời mời của P1 đến P2
    @Override
    public boolean sendInvitation(Player player1, Player player2, ConnectionData connectionData) throws RemoteException {
        if (playerOnMatch.contains(player2.getPid()))
            return false;
        // nếu P2 đồng ý chơi
        if (playerOnline.get(player2.getPid()).responseInvitation(playerOnline.get(player1.getPid()))) {
            // chuyển về cho P1 để P1 mở Server
            return true;
        } else {
            return false;
        }
    }


    @Override
    public Player signIn(String username, String password) throws RemoteException, SQLException {

        connectDatabase = new ConnectDatabase();

        List<Player> players = connectDatabase.getPlayers();

        for (Player player : players) {
            if (player.getUsername().trim().equals(username.trim())) {
                if (player.getPassword().trim().equals(password.trim())) {
                    return player;
                }
            }
        }
        return null;
    }

    @Override
    public void registerClient(int playerId, IClient iClient) throws RemoteException {
        this.playerOnline.put(playerId, iClient);
        ArrayList<IClient> iClients = new ArrayList<>(playerOnline.values());
        iClients.remove(playerOnline.get(playerId));
        for (IClient iClient1 : iClients) {
            iClient1.updateData();
        }

    }

    @Override
    public void msgToServer(String msg) throws RemoteException {
        System.out.println(msg);
    }

    @Override
    public List<Player> getPlayersOnline(Player player1) throws RemoteException {
        List<Player> players = new ArrayList<>();
        playerOnline.forEach((playerid, client) ->
                {
                    if (playerid != player1.getPid()) {
                        try {
                            players.add(client.getPlayer());
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        return players;
    }

    @Override
    public List<Match> getMatchsOnline() throws RemoteException {
        List<Match> listMatchs = new ArrayList<>();
        matches.forEach((matchId, match) -> {
            listMatchs.add(match);
        });
        return listMatchs;
    }

    @Override
    public List<Match> getMatchHistory(int playerId1) throws RemoteException, SQLException {
        connectDatabase = new ConnectDatabase();
        return connectDatabase.getMatchsHistory(playerId1);
    }

    @Override
    public void sendMatchtoServer(int idMatch, Match match, Player player, ConnectionData connectionData) throws RemoteException {
        matches.put(idMatch, match);
        for (Player player1 : match.getPlayers()) {
            playerOnMatch.add(player1.getPid());
        }

        // trận đấu được cập nhật
        System.out.println("1 Trận đấu được tạo có ip = " + idMatch);

        playerOnline.get(player.getPid()).setConnectDataForPlayer2(connectionData);
        ArrayList<IClient> iClients = new ArrayList<>(playerOnline.values());
        iClients.remove(playerOnline.get(idMatch));
        iClients.remove(playerOnline.get(player.getPid()));
        for (IClient iClient : iClients) {
            iClient.updateData();
        }
    }

    @Override
    public void updateMatchHistory(int idMatch, Match match) throws RemoteException {
        // xóa khỏi trận đang đấu
        matches.remove(matches.get(idMatch));

        ArrayList<IClient> iClients = new ArrayList<>(playerOnline.values());

        // xóa người chơi khỏi danh sách đang đấu
        for (Player player : match.getPlayers()) {
            playerOnMatch.remove(player.getPid());
            iClients.remove(playerOnline.get(player.getPid()));
        }

        // thông báo cập nhật cho các client khác
        for (IClient iClient : iClients) {
            iClient.updateData();
        }

        connectDatabase = new ConnectDatabase();
        try {
            connectDatabase.updateMatchHistory(match);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // tạo một máy chủ để xem
    @Override
    public void createNewViewServer(int id, ConnectionData connectionData) throws RemoteException {
        viewServer.put(id, connectionData);
        // đã nhận được trận đấu
        System.out.println("Match id = " + id + " Connection Data = " + connectionData.getIp()
                + connectionData.getPort() + connectionData.getBindName());
    }

    // hủy máy chủ xem
    @Override
    public void removeViewServer(int id) throws RemoteException {
        viewServer.remove(id);
    }

    // yêu cầu xem
    @Override
    public ConnectionData requestViewMatch(int id) throws RemoteException {
        System.out.println("Có người muốn xem " + id);
        return viewServer.get(id);
    }
}
