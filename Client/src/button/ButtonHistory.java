package button;

import graphics.CreateImage;
import main.Handler;
import state.HistoryState;
import state.State;

import java.awt.*;

public class ButtonHistory extends Button {

    public ButtonHistory(int x, int y) {
        super(x, y);
    }


    public void tick() {
        if (isClick()) {
            State.setCurrentState(new HistoryState());
        }
    }


    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonHistory[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonHistory[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonHistory[0].getWidth();
        int height = CreateImage.buttonHistory[0].getHeight();
        return new Rectangle(x, y, width, height);
    }

    private boolean isClick() {
        if (isOver()) {
            return Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick();
        }
        return false;
    }

}
