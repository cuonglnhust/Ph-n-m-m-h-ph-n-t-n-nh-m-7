package button;

import graphics.CreateImage;

import java.awt.*;

public class ButtonStart extends Button {

    public ButtonStart(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonStart[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonStart[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonStart[0].getWidth();
        int height = CreateImage.buttonStart[0].getHeight();
        return new Rectangle(x, y, width, height);
    }
}
