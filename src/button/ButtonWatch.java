package button;

import graphics.CreateImage;
import main.Handler;

import java.awt.*;

public class ButtonWatch extends Button {

    public ButtonWatch(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonWatch[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonWatch[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonWatch[0].getWidth();
        int height = CreateImage.buttonWatch[0].getHeight();
        return new Rectangle(x, y, width, height);
    }


}
