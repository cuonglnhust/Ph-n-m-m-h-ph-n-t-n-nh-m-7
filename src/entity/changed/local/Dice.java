package entity.changed.local;

import constant.DiceValue;
import entity.Entity;
import graphics.Animation;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;
import mouse.Mouse;

import java.awt.*;

public class Dice extends Entity {

    private static final int DICE_OFFSET = 25;
    private DiceValue diceValue;
    private Mouse mouse;
    private boolean shake;
    private Animation animation;

    // biến căn thời gian diễn ra animation
    private long lastTime = -1;

    // biến thông báo đang trong animation
    private boolean onAnimation;


    public Dice(int x, int y) {
        super(x + DICE_OFFSET, y + DICE_OFFSET, EntitySize.DICE_WIDTH, EntitySize.DICE_HEIGHT);
        mouse = Handler.getInstance().getMouse();
        shake = false;
        entity = CreateImage.dice;
    }

    @Override
    public void tick() {
        // nếu được click chuột
        if (isClicked()) {
            if (lastTime == -1) {
                lastTime = System.currentTimeMillis();
                animation = new Animation(CreateImage.diceAnimation, 2000);
            }
            if (System.currentTimeMillis() - lastTime <= 10000) {
                onAnimation = true;
                animation.DrawAnimation();
            }
            onAnimation = false;
            shake();
            shake = true;
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
        } else if (value >= 0.6 && value < 0.65) {
            entity = CreateImage.dice5;
            diceValue = DiceValue.FIVE;
        } else {
            entity = CreateImage.dice6;
            diceValue = DiceValue.SIX;
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
