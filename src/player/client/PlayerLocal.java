package player.client;

import constant.DiceValue;
import constant.HorseState;
import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.Horse;
import main.Handler;
import player.server.PlayerServerInterface;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayerLocal {
    // ngựa của người chơi
    ArrayList<Horse> horses;
    // Xúc xắc
    Dice dice;
    // đội màu gì
    protected TeamType team;

    // ngựa trên bản đồ
    private HashMap<Integer, Horse> onCourt = new HashMap<>();

    // ngựa trên Rank
    private HashMap<Integer, Horse> onRank = new HashMap<>();

    // ngựa di chuyển được
    private ArrayList<Horse> horseCanMove = new ArrayList<>();


    private PlayerServerInterface playerServerInterface;

    public PlayerLocal(ArrayList<Horse> horses, PlayerServerInterface playerServerInterface) {
        this.horses = horses;
        this.playerServerInterface = playerServerInterface;
    }



    public void tick() {
        action();
    }

    // xử lí hành động của Player
    private void action() {
        // nếu đúng lượt
        if (Handler.getInstance().getMap().getTurn() == team) {
            // nếu đã lắc
            if (dice.isShake()) {
                // nếu không có ngựa trên sân
                if (onCourt.isEmpty()) {
                    // giai đoạn 1
                    // nếu diceValue khác 6
                    if (dice.getDiceValue() != DiceValue.SIX) {
                        // đổi lượt
                        Handler.getInstance().getMap().changeTurn();
                        setDiceDefault();
                        setMouseDefault();
                    } else {
                        // xuất quân
                        for (Horse horse : horses) {
                            // chạy hàm check để thay đổi trạng thái ngựa
                            horse.checkState(dice.getDiceValue());
                        }

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
                    checkLineAllHorse();
                    System.out.println("GĐ 2 - Team " + team);
                    // khi tất cả ngựa không thể di chuyển được
                    if (horseCanMove.isEmpty()) {
                        System.out.println("Horse Can move Empty");
                        // khi lắc được giá trị khác 6
                        if (dice.getDiceValue() != DiceValue.SIX) {
                            System.out.println("Team " + team + " đổi lượt");
                            Handler.getInstance().getMap().changeTurn();
                            setMouseDefault();
                            setDiceDefault();
                        }
                        // nếu lắc được 6 thì lắc tiếp
                        else {
                            System.out.println("Lắc tiếp");
                            setMouseDefault();
                            setDiceDefault();
                        }
                    }
                    // khi có ngựa có thể di chuyển được
                    else {

                        for (Horse horse : horseCanMove) {
                            horse.tick();
                            horse.printHorse();
                            // đã di chuyển hay chưa, nếu đã di chuyển rồi thì
                            if (horse.isTurn()) {
                                // ngựa đã đi xong lượt của mình
                                horse.setTurn(false);
                                onCourt.put(horse.getId(), horse);
                                setMouseDefault();
                                // nếu giá trị lắc được không là 6 thì chuyển lượt
                                if (dice.getDiceValue() != DiceValue.SIX) {
                                    Handler.getInstance().getMap().changeTurn();
                                }
                                setDiceDefault();
                                horseCanMove.clear();
                                break;
                            }
                        }
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

    // kiểm tra đường đi của ngựa
    private void checkLineAllHorse() {
        /* kiểm tra tính toán checkLine cho từng ngựa
         *
         *  TH di chuyển được có hai loại là đá
         *  và di chuyển thông thường được checkLine trả
         *  về = 2 giá trị là != CANT_MOVE(=56)
         *
         * */
        for (Horse horse : horses) {
            horse.checkState(dice.getDiceValue());
            if (horse.getHorseState() != HorseState.CANT_MOVE) {
                horseCanMove.add(horse);
            }
        }
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

    public HashMap<Integer, Horse> getOnRank() {
        return onRank;
    }


}
