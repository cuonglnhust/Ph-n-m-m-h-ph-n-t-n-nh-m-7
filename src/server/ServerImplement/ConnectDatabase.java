package server.ServerImplement;

import client.RemoteInterface.Player;

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
