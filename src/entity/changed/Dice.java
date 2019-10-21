package entity.changed;

import constant.DiceValue;
import entity.Entity;
import main.Handler;
import map.EntitySize;
import mouse.Mouse;
import player.Player;

import java.awt.*;

public class Dice extends Entity {

    private static final int DICE_OFFSET = 25;
    private DiceValue diceValue;
    private Player player;
    private Mouse mouse;
    private boolean shake;

    public Dice(int x, int y, Player player) {
        super(x + DICE_OFFSET, y + DICE_OFFSET, EntitySize.DICE_WIDTH, EntitySize.DICE_HEIGHT);
        mouse = Handler.getInstance().getMouse();
        shake = false;
    }

    @Override
    public void tick() {
        // nếu được click chuột
        if (isClicked()) {
            shake();
            shake = true;
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(entity, x, y, width, height, null);
    }

    public DiceValue getDiceValue() {
        return diceValue;
    }

    public void setDiceValue(DiceValue diceValue) {
        this.diceValue = diceValue;
    }

    private DiceValue shake() {
        double value = Math.random();
        if (value >= 0 && value < 0.15) {
            return DiceValue.ONE;
        } else if (value >= 0.15 && value < 0.3) {
            return DiceValue.TWO;
        } else if (value >= 0.3 && value < 0.45) {
            return DiceValue.THREE;
        } else if (value >= 0.45 && value < 0.6) {
            return DiceValue.FOUR;
        } else if (value >= 0.6 && value < 0.75) {
            return DiceValue.FIVE;
        } else {
            return DiceValue.SIX;
        }
    }

    private Rectangle getBound() {
        return new Rectangle(x, y, 50, 50);
    }

    public boolean isClicked() {
        if (player.isTurn()) {
            if (getBound().contains(mouse.getMouseX(), mouse.getMouseY())) {
                return mouse.isRightClick() || mouse.isLeftClick();
            }
            return false;
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
