package button;

import graphics.CreateImage;

import java.awt.*;

public class ButtonMore extends Button {

    OnClickButton onClickButton;
    public ButtonMore(int x, int y) {
        super(x, y);
    }

    public void tick(){
        onClickButton.onClick();
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonMore[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonMore[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonMore[0].getWidth();
        int height = CreateImage.buttonMore[0].getHeight();
        return new Rectangle(x, y, width, height);
    }


}
