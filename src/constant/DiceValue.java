package constant;

import java.io.Serializable;

public enum DiceValue implements Serializable {
    NONE(0),ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    private int value;

    public int getValue() {
        return value;
    }

    DiceValue(int value) {
        this.value = value;
    }

}
