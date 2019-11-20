package button;

import graphics.CreateImage;

import java.awt.*;

public class ButtonInvite extends Button {

    public ButtonInvite(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonInvite[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonInvite[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonInvite[0].getWidth();
        int height = CreateImage.buttonInvite[0].getHeight();
        return new Rectangle(x, y, width, height);
    }
}
