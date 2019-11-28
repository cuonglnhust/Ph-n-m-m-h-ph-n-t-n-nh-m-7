package entity.changed.remote;

import constant.DiceValue;
import entity.Entity;
import graphics.Animation;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;
import mouse.Mouse;

import java.awt.*;

public class DiceRemote extends Entity {
    private static final int DICE_OFFSET = 25;
    private DiceValue diceValue;
    private Animation animation;

    // biến căn thời gian diễn ra animation
    private long lastTime = -1;

    // biến thông báo đang trong animation
    private boolean onAnimation;


    public DiceRemote(int x, int y) {
        super(x + DICE_OFFSET, y + DICE_OFFSET, EntitySize.DICE_WIDTH, EntitySize.DICE_HEIGHT);
        entity = CreateImage.dice;
    }

    @Override
    public void tick() {

    }

    public void updateAnimation() {
        if (lastTime == -1) {
            lastTime = System.currentTimeMillis();
            animation = new Animation(CreateImage.diceAnimation, 2000);
        }
        if (System.currentTimeMillis() - lastTime <= 10000) {
            onAnimation = true;
            animation.DrawAnimation();
        }
        onAnimation = false;
    }

    public void updateDiceValue(DiceValue diceValue) {
        switch (diceValue) {
            case NONE:
                entity = CreateImage.dice;
                break;
            case ONE:
                entity = CreateImage.dice1;
                break;
            case TWO:
                entity = CreateImage.dice2;
                break;
            case THREE:
                entity = CreateImage.dice3;
                break;
            case FOUR:
                entity = CreateImage.dice4;
                break;
            case FIVE:
                entity = CreateImage.dice5;
                break;
            case SIX:
                entity = CreateImage.dice6;
                break;
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

    public void setDiceValue(DiceValue diceValue) {
        this.diceValue = diceValue;
    }

    private Rectangle getBound() {
        return new Rectangle(x, y, 50, 50);
    }

}