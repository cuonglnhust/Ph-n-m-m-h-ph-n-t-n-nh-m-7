package server.ServerImplement;

import client.RemoteInterface.Match;
import client.RemoteInterface.Player;
import client.RemoteInterface.IClient;
import server.RemoteInterface.IServer;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.*;

public class ServerImp extends UnicastRemoteObject implements IServer, Serializable {

    public Map<Integer, IClient> playerOnline;

    public ServerImp() throws RemoteException {
        this.playerOnline = new HashMap<>();
    }

    @Override
    public void sendInvitation(Player player1, Player player2) throws RemoteException {

        if (playerOnline.get(player2.getPid()).                             // client2 gửi trả lời invite
                responseInvitation(playerOnline.get(player1.getPid()))) {
            playerOnline.get(player2.getPid()).
                    retrieveIp(playerOnline.get(player1.getPid()).getIpAddress()); // client2 lấy ipaddress của client1
            System.out.println(player2.getPname() + "đã có chấp nhận lời mời của bạn !");
        } else {
            System.out.println("Play : " + player2.getPname() + "Từ chối chơi với bạn !");
        }

    }


    @Override
    public Player signIn(String username, String password) throws RemoteException, SQLException {

        ConnectDatabase connectDatabase = new ConnectDatabase();

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
    public List<Integer> senIdMatch(List<Match> matches) {
        List<Integer> ListRun = new ArrayList<>();
        for (var list : matches) {
            if (list.getPlayers().size() >= 2 && list.getStatus() == 1) {
                ListRun.add(list.getId());

            }

        }
        return ListRun;
    }

}
