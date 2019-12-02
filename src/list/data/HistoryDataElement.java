package list.data;

import SCCommon.Player;

import java.util.List;

public class HistoryDataElement {

    private List<Player> opponents;
    private int winner;
    private String duration;

    public HistoryDataElement() {
    }

    public HistoryDataElement(List<Player> opponents, int winner, String duration) {
        this.opponents = opponents;
        this.winner = winner;
        this.duration = duration;
    }

    public List<Player> getOpponents() {
        return opponents;
    }

    public void setOpponents(List<Player> opponents) {
        this.opponents = opponents;
    }

    public int getWinner() {
        return winner;
    }

    public void setWinner(int winner) {
        this.winner = winner;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
