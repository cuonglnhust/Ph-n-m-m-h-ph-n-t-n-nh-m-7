package player.local;

import constant.DiceValue;
import constant.HorseState;
import constant.TeamType;
import entity.changed.local.Dice;
import entity.changed.local.Horse;
import graphics.CreateImage;
import main.Handler;
import state.HomeState;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class Player {

    // ngựa của người chơi
    ArrayList<Horse> horses;
    // Xúc xắc
    Dice dice;
    // đội màu gì
    protected TeamType team;

    // dấu hiệu bị đá
    private boolean isKick;
    private int kickedPosition = -1;

    // điều kiện thắng
    private boolean win;


    // ngựa trên bản đồ
    private HashMap<Integer, Horse> onCourt = new HashMap<>();

    // ngựa trên Rank
    private HashMap<Integer, Horse> onRank = new HashMap<>();

    // ngựa di chuyển được
    private ArrayList<Horse> horseCanMove = new ArrayList<>();

    public void tick() {
        action();
    }

    // xử lí hành động của Player
    private void action() {
        // nếu đúng lượt
        if (Handler.getInstance().getMapTemp().getTurn() == team) {
            // nếu đã lắc
            if (dice.isShake()) {
                // nếu không có ngựa trên sân
                if (onCourt.isEmpty()) {
                    // giai đoạn 1
                    // nếu diceValue khác 6
                    if (dice.getDiceValue() != DiceValue.SIX) {
                        // đổi lượt
                        System.out.println("Change Turn Player");
                        Handler.getInstance().getMapTemp().changeTurn();
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

                }
                // nếu có ngựa trên sân
                else {
                    // giai đoạn 2
                    // di chuyển theo giá trị diceValue
                    checkLineAllHorse();
                    // khi tất cả ngựa không thể di chuyển được
                    if (horseCanMove.isEmpty()) {
                        // khi lắc được giá trị khác 6
                        if (dice.getDiceValue() != DiceValue.SIX) {
                            Handler.getInstance().getMapTemp().changeTurn();
                            setMouseDefault();
                            setDiceDefault();
                        }
                        // nếu lắc được 6 thì lắc tiếp
                        else {
                            setMouseDefault();
                            setDiceDefault();
                        }
                    }
                    // khi có ngựa có thể di chuyển được
                    else {
                        for (Horse horse : horseCanMove) {
                            horse.tick();
//                            horse.printHorse();
                            // đã di chuyển hay chưa, nếu đã di chuyển rồi thì
                            if (horse.isTurn()) {
                                System.out.println("Finish Turn");
                                // ngựa đã đi xong lượt của mình
                                horse.setTurn(false);
                                onCourt.put(horse.getId(), horse);
                                setMouseDefault();
                                // nếu giá trị lắc được không là 6 thì chuyển lượt
                                if (dice.getDiceValue() != DiceValue.SIX) {
                                    System.out.println("Change Turn = " + DiceValue.values().toString());
                                    Handler.getInstance().getMapTemp().changeTurn();
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
        // nếu không đúng lượt
        if (isKick) {
            if (kickedPosition != -1) {
                // tìm ngựa ở vị trí này
                for (Horse horse : horses) {
                    if (horse.getPosition() == kickedPosition) {
                        horse.isKickedAss();
                        break;
                    }
                }
                kickedPosition = -1;
            }
            isKick = false;
        }
        // kiểm tra điều kiện thắng
        if (onRank.size() == 4) {
            if (!onRank.containsKey(1) && !onRank.containsKey(2)) {
                // quảng bá
                // nếu là client
                if (Handler.getInstance().getClientPlayer() != null) {
                    try {
                        Handler.getInstance().getClientPlayer().getPlayGameServer()
                                .updateResult(Handler.getInstance().getId());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                // nếu là server
                else if (Handler.getInstance().getServerPlayer() != null) {
                    try {
                        Handler.getInstance().getServerPlayer().getPlayGameServerImp().updateResultFromServer();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                // đánh dấu là thắng
                win = true;
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

        // nếu thắng
        if (win) {
            // hiển thị thông báo
            g.drawImage(CreateImage.win, 100, 100, 730, 530, null);
            Object[] options = {"OK"};
            int option = JOptionPane.showOptionDialog(null,
                    "Bạn đã thắng. Vui lòng nhấn OK để tiếp tục", "Message",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            // nếu click ok
            if (option == JOptionPane.OK_OPTION) {
                // chuyển trạng thái
                State.setCurrentState(new HomeState());


            }
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

    public void setKick(boolean kick) {
        isKick = kick;
    }

    public void setKickedPosition(int kickedPosition) {
        this.kickedPosition = kickedPosition;
    }
}
