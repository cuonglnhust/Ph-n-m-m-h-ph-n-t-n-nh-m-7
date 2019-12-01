package list.data;

public class PlayerDataElement {

    private int idPlayer;
    private String playerName;

    public PlayerDataElement() {
    }

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

    public void setIdPlayer(int idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
