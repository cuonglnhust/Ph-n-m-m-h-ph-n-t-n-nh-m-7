package entity.changed.data;

import java.io.Serializable;

public class HorseData implements Serializable {
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
