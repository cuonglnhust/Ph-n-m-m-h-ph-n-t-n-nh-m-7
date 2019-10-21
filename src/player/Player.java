package player;

import constant.DiceValue;
import constant.TeamType;
import entity.changed.*;
import graphics.Constant;
import main.Handler;
import map.Map;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    // ngựa của người chơi
    protected ArrayList<Horse> horses;
    // Xúc xắc
    protected Dice dice;
    // đội màu gì
    protected TeamType team;

    public void tick() {
        // kiểm tra lượt và đã lắc hay chưa
        if (Handler.getInstance().getMap().getTurn() == team && !dice.isShake()) {
            // lắc xúc xắc
            dice.tick();
            // ngựa di chuyển theo xúc xắc
            for (Horse horse : horses) {
                horse.tick();
            }
        }
        // nếu giá trị là 6 thì được lắc tiếp
        if (dice.getDiceValue() != DiceValue.SIX) {
            Handler.getInstance().getMap().changeTurn();
        }
        // đặt lại xúc xắc
        dice.setShake(false);
        dice.setDiceValue(DiceValue.NONE);
    }

    public void render(Graphics g) {
        for (Horse horse : horses) {
            horse.render(g);
        }
    }

    // khởi tạo ngựa
    protected void initHorse() {
        horses = new ArrayList<>();
    }

    // lấy Dice
    public Dice getDice() {
        return dice;
    }

    public ArrayList<Horse> getHorses() {
        return horses;
    }

    public TeamType getTeam() {
        return team;
    }

    public void setDefaultDice() {
        dice.setDiceValue(DiceValue.NONE);
    }


}
