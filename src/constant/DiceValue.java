package constant;

public enum DiceValue {
    NONE(0),ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6);

    private int value;

    public int getValue() {
        return value;
    }

    DiceValue(int value) {
        this.value = value;
    }

}
