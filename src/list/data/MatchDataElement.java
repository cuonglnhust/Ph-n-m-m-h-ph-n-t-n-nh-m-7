package list.data;

public class MatchDataElement {
    private String firstPlayer;
    private String secondPlayer;

    public MatchDataElement(String firstPlayer, String secondPlayer) {
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public String getFirstPlayer() {
        return firstPlayer;
    }

    public String getSecondPlayer() {
        return secondPlayer;
    }
}
