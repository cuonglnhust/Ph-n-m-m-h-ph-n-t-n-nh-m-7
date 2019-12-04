package state.server.ServerImplement;

import SCCommon.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.*;

public class ServerImp extends UnicastRemoteObject implements IServer {

    public Map<Integer, IClient> playerOnline;

    public Map<Integer,Match> matches;

    private ConnectDatabase connectDatabase = null;

    public ServerImp() throws RemoteException {
        this.playerOnline = new HashMap<>();
        this.matches = new HashMap<>();
    }

    // xử lí lời mời của P1 đến P2
    @Override
    public boolean sendInvitation(Player player1, Player player2, ConnectionData connectionData) throws RemoteException {

        // nếu P2 đồng ý chơi
        if (playerOnline.get(player2.getPid()).responseInvitation(playerOnline.get(player1.getPid()))) {
            // chuyển về cho P1 để P1 mở Server
            return true;
        } else {
            return false;
        }

//        if (playerOnline.get(player2.getPid()).                             // client2 gửi trả lời invite
//                responseInvitation(playerOnline.get(player1.getPid()))) {
//            playerOnline.get(player2.getPid()).
//                    retrieveIp(playerOnline.get(player1.getPid()).getIpAddress()); // client2 lấy ipaddress của client1
//            System.out.println(player2.getPname() + "đã có chấp nhận lời mời của bạn !");
//            Match match = new Match();
//            List<Player> players = new ArrayList<>();
//            players.add(player1);
//            players.add(player2);
//            match.setPlayers(players);
//            matches.add(match);
//
//        } else {
//            System.out.println("Play : " + player2.getPname() + "Từ chối chơi với bạn !");
//        }

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

        playerOnline.forEach((playerid, client) ->
                {
                    if (playerid != playerId) {
                        try {
                            iClient.updateData();
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
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
        matches.forEach((matchId,match) -> {
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
    public void sendMatchtoServer(int idMatch,Match match, Player player, ConnectionData connectionData) throws RemoteException {
        matches.put(idMatch,match);
        playerOnline.get(player.getPid()).setConnectDataForPlayer2(connectionData);
    }

    @Override
    public void updateMatchHistory(int idMatch, Match match) throws RemoteException {

        matches.remove(matches.get(idMatch));

        connectDatabase = new ConnectDatabase();

        try {
            connectDatabase.updateMatchHistory(match);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

  /*  @Override
    public List<Integer> senIdMatch(List<Match> matches) {
        List<Integer> ListRun = new ArrayList<>();
        for (Match match : matches) {
            if (match.getPlayers().size() >= 2 && match.getStatus() == 1) {
                ListRun.add(match.getId());

            }

        }
        return ListRun;
    }
*/
}
