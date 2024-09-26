package entity.changed.remote;

import constant.DiceValue;
import entity.Entity;
import graphics.Animation;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;
import mouse.Mouse;

import java.awt.*;

public class DiceCopy extends Entity {
    private static final int DICE_OFFSET = 25;
    private DiceValue diceValue;
    private boolean isClick;
    private Animation animation;
    private boolean onAnimation;
    private boolean firstTime;

    public DiceCopy(int x, int y) {
        super(x + DICE_OFFSET, y + DICE_OFFSET, EntitySize.DICE_WIDTH, EntitySize.DICE_HEIGHT);
        entity = CreateImage.dice;
        animation = new Animation(CreateImage.diceAnimation, 10);
        firstTime = true;
        onAnimation = false;
        diceValue = DiceValue.NONE;
        isClick = false;
    }

    public void tick() {
        // nếu đã click
        if (isClick) {
            // nếu là lần đầu chạy ngay sau khi click
            if (firstTime) {
                // đạt thời gian khởi đầu cho animation là thời điểm hiện tại
                animation.setLastTime(System.currentTimeMillis());
                // đánh dấu để lần sau chạy không vào nhánh này nữa
                firstTime = false;
            } else {
                // chạy animation
                animation.runTimerAnimation();
                // nếu animation kết thúc
                if (animation.isEnd()) {
                    // kiểm tra xem giá trị xúc xắc đã cập nhật hay chưa
                    // nếu đã cập nhật
                    if (diceValue != DiceValue.NONE) {
                        // thay đổi hình ảnh dice dựa vào giá trị nhận được
                        shake();
                        // đặt lại các giá trị đánh dấu về default
                        isClick = false;
                        firstTime = true;
                        animation.setEnd(false);
                        onAnimation = false;
                        diceValue = DiceValue.NONE;
                    }
                }
                // nếu animation chưa kết thúc thì onanimation = true để vẽ animation
                else {
                    onAnimation = true;
                }
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

    private void shake() {
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

    public void setDiceValue(DiceValue diceValue) {
        this.diceValue = diceValue;
    }

    public void setClick(boolean click) {
        isClick = click;
    }

    public DiceValue getDiceValue() {
        return diceValue;
    }
}
