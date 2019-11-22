package button;

import graphics.CreateImage;
import main.Handler;
import state.GameState;
import state.State;

import java.awt.*;

public class ButtonPlay extends Button {

    public ButtonPlay(int x, int y) {
        super(x, y);
    }

    public void tick() {
        if (isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Handler.getInstance().getMouse().setDefaultClick();
                State.setCurrentState(new GameState());
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonPlay[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonPlay[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonPlay[0].getWidth();
        int height = CreateImage.buttonPlay[0].getHeight();
        return new Rectangle(x, y, width, height);
    }
}
