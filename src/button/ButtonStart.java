package button;

import Login.Login;
import graphics.CreateImage;
import main.Handler;

import javax.swing.*;
import java.awt.*;
import java.util.Hashtable;

public class ButtonStart extends Button {



    public ButtonStart(int x, int y) {
        super(x, y);
    }
    public void  tick(){
        if(isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Login frame = new Login();
                frame.setTitle("Login form");
                frame.setVisible(true);
                frame.setBounds(10, 10, 370, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setResizable(false);
                frame.setLocation(500,100);
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
