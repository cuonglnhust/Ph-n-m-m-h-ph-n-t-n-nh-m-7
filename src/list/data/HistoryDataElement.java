package list.data;

public class HistoryDataElement {
    private String player;
    private boolean result;
    private String times;

    public HistoryDataElement(String player, boolean result, String times) {
        this.player = player;
        this.result = result;
        this.times = times;
    }

    public String getPlayer() {
        return player;
    }

    public boolean isResult() {
        return result;
    }

    public String getTimes() {
        return times;
    }
}
