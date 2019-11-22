package list.data;

public class PlayerDataElement {

    private int idPlayer;
    private String playerName;

    public PlayerDataElement(int idPlayer, String playerName) {
        this.idPlayer = idPlayer;
        this.playerName = playerName;
    }

    public int getIdPlayer() {
        return idPlayer;
    }

    public String getPlayerName() {
        return playerName;
    }
}
