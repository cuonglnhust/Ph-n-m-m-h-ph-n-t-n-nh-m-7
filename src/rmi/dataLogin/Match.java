package rmi.dataLogin;

import java.io.Serializable;
import java.util.List;

public class Match implements Serializable {
    private int id;

    private int duration;

    private int winner;

    private  int status;

    private List<Player> players;

    public  int getStatus(){return status;}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }
}
