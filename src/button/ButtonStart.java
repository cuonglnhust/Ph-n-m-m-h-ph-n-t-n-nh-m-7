package button;

import Login.Login;
import graphics.CreateImage;
import main.Handler;

import javax.swing.*;
import java.awt.*;

public class ButtonStart extends Button {

    public ButtonStart(int x, int y) {
        super(x, y);
    }

    public void tick() {
        if (isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Handler.getInstance().getMouse().setDefaultClick();
                 Login login = new Login();
                 login.setVisible(true);
                 login.setTitle("Login form");
                 login.setBounds(500,10,370,600);
                 login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                 login.setResizable(false);
            }
        }
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
