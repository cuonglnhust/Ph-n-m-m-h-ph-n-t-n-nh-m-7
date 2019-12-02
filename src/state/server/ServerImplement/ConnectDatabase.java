package state.server.ServerImplement;

import SCCommon.Match;
import SCCommon.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConnectDatabase {

    private String url="jdbc:mysql://localhost:3306/hippocampus?useUnicode=yes&characterEncoding=UTF-8&useSSL=false";
    private String username="root";
    private String password="vuong";

    private Connection conn=null;
    private Statement statement = null;

    private void createConnection() throws SQLException {
        if(conn==null) {

            conn = DriverManager.getConnection(url, username, password);
            System.out.println("connect database !!");
        }else {System.out.println("database da connect !!");}
    }

    public List<Player> getPlayers() throws SQLException {

        List<Player> players = new ArrayList<>();
        createConnection();
        statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM player");

        while (rs.next()){
            Player player = new Player();
            player.setPid(rs.getInt(1));
            player.setPname(rs.getString(2));
            player.setUsername(rs.getString(3));
            player.setPassword(rs.getString(4));

            players.add(player);
        }
        return players;
    }
    public List<Match> getMatchsHistory(int playerId1) throws SQLException{
        List<Match> matches = new ArrayList<>();


        createConnection();

        Statement statement1 = conn.createStatement();
        ResultSet rs1 = statement1.executeQuery("select matches.id,matches.duration," +
                                                            "matches.winner,player.id " +
                                                    "from match_player,player,matches " +
                                                    "where matches.id = match_player.match_id and player.id = match_player.player_id " +
                                                    "and player.id=" + playerId1);

        while (rs1.next()){
            List<Player> players = new ArrayList<>();

            Match match = new Match();
            match.setId(rs1.getInt(1));
            match.setDuration(rs1.getString(2));
            match.setWinner(rs1.getInt(3));

            Statement statement2 = conn.createStatement();
            ResultSet rs2 = statement2.executeQuery("select player.id,player.name," +
                                                                "player.username,player.password " +
                                                        "from match_player,player,matches " +
                                                        "where matches.id = match_player.match_id and player.id = match_player.player_id " +
                                                        "and matches.id=" + match.getId());
            while (rs2.next()){

                Player player = new Player();
                player.setPid(rs2.getInt(1));
                player.setPname(rs2.getString(2));
                player.setUsername(rs2.getString(3));
                player.setPassword(rs2.getString(4));
                if (player.getPid() != playerId1){
                    players.add(player);
                }

            }
            match.setPlayers(players);

            matches.add(match);
        }
        return matches;
    }
    private void shutdownConnection() {

        try {
            if(statement != null) {
                statement.close();
            }
            if(conn != null) {
                DriverManager.getConnection(url + ";shutdown=true", username, password);
                conn.close();
            }
        } catch (SQLException e) {

        }


    }
}
