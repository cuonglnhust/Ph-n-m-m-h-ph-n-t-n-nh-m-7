package list.data;

public class MatchDataElement {
    private String firstPlayer;
    private String secondPlayer;
    private int serverId;

    public MatchDataElement(String firstPlayer, String secondPlayer, int serverId) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
        this.serverId = serverId;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }

    public int getServerId() {
        return serverId;
    }
}
