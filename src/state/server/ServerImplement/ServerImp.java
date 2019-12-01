package state.server.ServerImplement;

import SCCommon.Match;
import SCCommon.Player;
import SCCommon.IClient;
import SCCommon.IServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.*;

public class ServerImp extends UnicastRemoteObject implements IServer{

    public Map<Integer, IClient> playerOnline;

    public List<Match> matches;

    private ConnectDatabase connectDatabase = null;

    public ServerImp() throws RemoteException {
        this.playerOnline = new HashMap<>();
        this.matches = new ArrayList<>();
    }

    @Override
    public boolean sendInvitation(Player player1, Player player2) throws RemoteException {

        if(playerOnline.get(player2.getPid()).responseInvitation(playerOnline.get(player1.getPid()))){

            return true;
        }else {
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
        return this.matches;
    }

    @Override
    public List<Match> getMatchHistory(int playerId1) throws RemoteException, SQLException {
        connectDatabase = new ConnectDatabase();

        return connectDatabase.getMatchsHistory(playerId1);
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
