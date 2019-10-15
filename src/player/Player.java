package player;

import entity.changed.Dice;
import entity.changed.Horse;

import java.util.ArrayList;

public class Player {

    private Horse[] horses = new Horse[4];
    private Dice dice;

    // lấy giá trị xúc xắc
    // chưa code
    private int getDiceValue() {
        return 1;
    }

    // di chuyển ngựa
    public void move() {
        for (Horse horse : horses) {
            if (horse.isMove()) {
                horse.move(getDiceValue());
                break;
            }
        }
    }
}
