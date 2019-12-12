package button;

import constant.StateTag;
import graphics.CreateImage;
import main.Handler;
import state.HomeState;
import state.State;

import java.awt.*;

public class ButtonBack extends Button {


    public ButtonBack(int x, int y) {
        super(x, y);
    }

    public void tick(StateTag stateTag) {
        if (isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Handler.getInstance().getMouse().setDefaultClick();
                switch (stateTag) {
                    case HISTORY_STATE:
                        State.setCurrentState(new HomeState());
                        break;
                    default:
                        break;
                }
            }
        }

    }


    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonBack[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonBack[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonBack[0].getWidth();
        int height = CreateImage.buttonBack[0].getHeight();
        return new Rectangle(x, y, width, height);
    }
}
