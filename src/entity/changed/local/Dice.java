package entity.changed.local;

import constant.DiceValue;
import entity.Entity;
import graphics.Animation;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;
import mouse.Mouse;

import java.awt.*;
import java.rmi.RemoteException;

public class Dice extends Entity {

    private static final int DICE_OFFSET = 25;
    private DiceValue diceValue;
    private Mouse mouse;
    private boolean shake;
    private boolean isClick;
    private Animation animation;

    // biến thông báo đang trong animation
    private boolean onAnimation;


    public Dice(int x, int y) {
        super(x + DICE_OFFSET, y + DICE_OFFSET, EntitySize.DICE_WIDTH, EntitySize.DICE_HEIGHT);
        mouse = Handler.getInstance().getMouse();
        shake = false;
        entity = CreateImage.dice;
        animation = new Animation(CreateImage.diceAnimation, 10);
        diceValue = DiceValue.NONE;
    }

    @Override
    public void tick() {
        // nếu đã được click chuột
        if (isClick) {
            // chạy animation
            animation.runTimerAnimation();
            // nếu animation kết thúc
            if (animation.isEnd()) {
                // lắc xúc xắc
                shake();

                // quảng bá giá trị này ra các máy khác
                // nếu là Client
                if (Handler.getInstance().getClientPlayer() != null) {
                    try {
                        Handler.getInstance().getClientPlayer().getPlayGameServer().
                                broadcastDiceValue(Handler.getInstance().getId(), diceValue);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                // nếu là server
                else if (Handler.getInstance().getServerPlayer() != null) {
                    Handler.getInstance().getServerPlayer().getPlayGameServerImp().
                            broadcastDiceValueFromServer(Handler.getInstance().getId(), diceValue);
                }


                // đã lắc
                shake = true;
                // đã hoàn thành nên isClick = false để lắc cho lần tiếp theo
                isClick = false;
                // đã hoàn thành nên làm mới cho lần sau
                animation.setEnd(false);
                // thoát khỏi animation
                onAnimation = false;
            }
            // nếu animation chưa kết thúc
            else {
                // set onAnimation = true để hàm render vẽ animation
                onAnimation = true;
            }
        }
        // nếu chưa được click chuột
        else {
            // nếu được click
            if (isClicked()) {
                Handler.getInstance().getMouse().setDefaultClick();
                // xét là đã click
                isClick = true;
                // quảng bá hành động này cho các máy khác
                // nếu là client
                if (Handler.getInstance().getClientPlayer() != null) {
                    try {
                        Handler.getInstance().getClientPlayer().getPlayGameServer().
                                broadcastDiceClick(Handler.getInstance().getId());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                // nếu là server
                else if (Handler.getInstance().getServerPlayer() != null) {
                    Handler.getInstance().getServerPlayer().getPlayGameServerImp().
                            broadcastDiceClickFromServer(Handler.getInstance().getId());
                }
                // bắt đầu đếm thời gian cho animation từ thời điểm click
                animation.setLastTime(System.currentTimeMillis());
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (onAnimation) {
            g.drawImage(animation.getCurrentImage(), x, y, width, height, null);
        } else {
            g.drawImage(entity, x, y, width, height, null);
        }
    }

    public DiceValue getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(DiceValue diceValue) {
        this.diceValue = diceValue;
    }

    private void shake() {
        double value = Math.random();
        if (value >= 0 && value < 0.15) {
            entity = CreateImage.dice1;
            diceValue = DiceValue.ONE;
        } else if (value >= 0.15 && value < 0.3) {
            entity = CreateImage.dice2;
            diceValue = DiceValue.TWO;
        } else if (value >= 0.3 && value < 0.45) {
            entity = CreateImage.dice3;
            diceValue = DiceValue.THREE;
        } else if (value >= 0.45 && value < 0.6) {
            entity = CreateImage.dice4;
            diceValue = DiceValue.FOUR;
        } else if (value >= 0.6 && value < 0.75) {
            entity = CreateImage.dice5;
            diceValue = DiceValue.FIVE;
        } else {
            entity = CreateImage.dice6;
            diceValue = DiceValue.SIX;
        }
    }

    private void diceGraphicsAccordingValue(){
        switch (diceValue) {
            case NONE:
                entity = CreateImage.diceAnimation[0];
                break;
            case ONE:
                entity = CreateImage.diceAnimation[1];
                break;
            case TWO:
                entity = CreateImage.diceAnimation[2];
                break;
            case THREE:
                entity = CreateImage.diceAnimation[3];
                break;
            case FOUR:
                entity = CreateImage.diceAnimation[4];
                break;
            case FIVE:
                entity = CreateImage.diceAnimation[5];
                break;
            case SIX:
                entity = CreateImage.diceAnimation[6];
                break;
        }
    }

    private Rectangle getBound() {
        return new Rectangle(x, y, 50, 50);
    }

    private boolean isClicked() {
        if (getBound().contains(mouse.getMouseX(), mouse.getMouseY())) {
            return mouse.isRightClick() || mouse.isLeftClick();
        }
        return false;
    }

    public boolean isShake() {
        return shake;
    }

    public void setShake(boolean shake) {
        this.shake = shake;
    }

}
