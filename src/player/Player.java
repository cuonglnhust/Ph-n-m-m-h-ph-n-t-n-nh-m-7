package player;

import constant.TeamType;
import entity.changed.*;
import graphics.Constant;

import java.awt.*;
import java.util.ArrayList;

public class Player {

    // ngựa của người chơi
    private ArrayList<Horse> horses;

    // Xúc xắc
    private Dice dice;

    // đội màu gì
    private TeamType team;

    // lượt chơi
    private boolean turn;

    public Player(Dice dice, TeamType team) {
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
            case TEAM_BLUE:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.blueHorseTeam.get(i);
                    horses.add(new HorseBlue(point.x, point.y, team));
                }
                break;
            case TEAM_RED:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.redHorseTeam.get(i);
                    horses.add(new HorseRed(point.x, point.y, team));
                }
                break;
            case TEAM_ORANGE:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.orangeHorseTeam.get(i);
                    horses.add(new HorseOrange(point.x, point.y, team));
                }
                break;
            case TEAM_VIOLET:
                for (int i = 0; i < 4; i++) {
                    Point point = Constant.violetHorseTeam.get(i);
                    horses.add(new HorseViolet(point.x, point.y, team));
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
