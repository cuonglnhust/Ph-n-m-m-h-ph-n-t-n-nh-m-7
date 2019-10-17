package player;

import entity.changed.Dice;
import entity.changed.Horse;
import graphics.Constant;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    // ngựa của người chơi
    private ArrayList<Horse> horses;

    // Xúc xắc
    private Dice dice;

    // đội màu gì
    private int team;

    // lượt chơi
    private boolean turn;

    public Player(Dice dice, int team) {
        this.dice = dice;
        this.team = team;
    }

    public void tick() {
        for (Horse horse : horses) {
            horse.tick();
        }
    }

    public void render(Graphics g) {
        for (Horse horse : horses) {
            horse.render(g);
        }
    }

    // khởi tạo ngựa
    private void initHorse() {
        horses = new ArrayList<>();
        switch (team) {
            case Constant.TEAM_BLUE:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.blueHorseTeam.get(i);
                    horses.add(new Horse(point.x, point.y, team));
                }
                break;
            case Constant.TEAM_RED:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.redHorseTeam.get(i);
                    horses.add(new Horse(point.x, point.y, team));
                }
                break;
            case Constant.TEAM_ORANGE:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.orangeHorseTeam.get(i);
                    horses.add(new Horse(point.x, point.y, team));
                }
                break;
            case Constant.TEAM_VIOLET:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.violetHorseTeam.get(i);
                    horses.add(new Horse(point.x, point.y, team));
                }
                break;
        }
    }

    // check xem đến lượt hay chưa
    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    // lấy Dice
    public Dice getDice() {
        return dice;
    }
}
