package player;

import constant.DiceValue;
import constant.TeamType;
import entity.changed.*;
import graphics.Constant;
import main.Handler;
import map.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    // ngựa của người chơi
    protected ArrayList<Horse> horses;
    // Xúc xắc
    protected Dice dice;
    // đội màu gì
    protected TeamType team;

    // giai đoạn nào
    private HashMap<Integer, Horse> onCourt = new HashMap<>();

    public void tick() {
        diceShake();
        // kiểm tra lượt và đã lắc hay chưa
//        if (Handler.getInstance().getMap().getTurn() == team && !dice.isShake()) {

        // lắc xúc xắc
//            dice.tick();
//            if (dice.isShake()) {
//                System.out.println("Team : " + team + "Value -- " + dice.getDiceValue());
//                // ngựa di chuyển theo xúc xắc
//                for (Horse horse : horses) {
//                    horse.tick();
//                }
//                // nếu giá trị là khác 6 thì chuyển lượt
//                if (dice.getDiceValue() != DiceValue.SIX) {
//                    Handler.getInstance().getMap().changeTurn();
//                }
//
//                // đặt lại xúc xắc
//                dice.setShake(false);
//                dice.setDiceValue(DiceValue.NONE);
//                Handler.getInstance().getMouse().setLeftClick(false);
//                Handler.getInstance().getMouse().setRightClick(false);
//            }

//        }

    }

    // thực hiện lắc xúc xắc
    private void diceShake() {
        // nếu đúng lượt
        if (Handler.getInstance().getMap().getTurn() == team) {
            // nếu đã lắc
            if (dice.isShake()) {
                // nếu không có ngựa trên sân
                if (onCourt.isEmpty()) {
                    System.out.println("onCourt = 0");
                    // giai đoạn 1
                    // nếu diceValue khác 6
                    if (dice.getDiceValue() != DiceValue.SIX) {
                        // đổi lượt
                        Handler.getInstance().getMap().changeTurn();
                        setDiceDefault();
                        setMouseDefault();
                    } else {
                        System.out.println("Player - XQ" + "Team : " + team + " diceValue : " + dice.getDiceValue());
                        // xuất quân
                        for (Horse horse : horses) {
                            horse.tick();
                            if (horse.isTurn()) {
                                horse.setTurn(false);
                                onCourt.put(horse.getId(), horse);
                                setDiceDefault();
                                setMouseDefault();
                                break;
                            }
                        }
                    }

                } else {
                    // giai đoạn 2
                    // di chuyển theo giá trị diceValue
                    System.out.println("Player - GĐ2" + "Team : " + team + " diceValue : " + dice.getDiceValue());
                    int cantMove = 0;

                    for (Horse horse : horses) {
                        horse.setCantMove(false);
                    }

                    for (Horse horse : horses) {
                        horse.tick();
                        if (horse.isCantMove()) {
                            horse.printHorse();
                            cantMove++;
                            continue;
                        }
                        if (horse.isTurn()) {
                            horse.setTurn(false);
                            onCourt.put(horse.getId(), horse);
                            setMouseDefault();
                            if (dice.getDiceValue() != DiceValue.SIX) {
                                Handler.getInstance().getMap().changeTurn();
                            }
                            setDiceDefault();
                            break;
                        }
                    }
                    System.out.println("CANT MOVE = " + cantMove);
                    System.out.println("ON COURT " + onCourt.size());
                    if (cantMove == onCourt.size() && dice.getDiceValue() != DiceValue.SIX) {
                        setMouseDefault();
                        setDiceDefault();
                        Handler.getInstance().getMap().changeTurn();
                    }
                }
            } else {
                // lắc xúc xắc
                dice.tick();
            }

        }
    }

    // xét default cho dice
    private void setDiceDefault() {
        dice.setShake(false);
        dice.setDiceValue(DiceValue.NONE);
    }

    // xét lại default cho chuột
    private void setMouseDefault() {
        Handler.getInstance().getMouse().setLeftClick(false);
        Handler.getInstance().getMouse().setRightClick(false);
    }

    // thực hiện di chuyển ngựa
    private void horseMove() {

    }

    private void horseStarting() {
    }


    public void render(Graphics g) {
        dice.render(g);
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

    public HashMap<Integer, Horse> getOnCourt() {
        return onCourt;
    }
}
