package entity.changed.data;

public class HorseData {
    private int id;
    private int position;
    private int rank;

    public HorseData(int id, int position, int rank) {
        this.id = id;
        this.position = position;
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public int getPosition() {
        return position;
    }

    public int getRank() {
        return rank;
    }
}
