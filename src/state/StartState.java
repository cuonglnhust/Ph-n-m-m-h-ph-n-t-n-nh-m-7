package state;

import button.ButtonStart;
import graphics.CreateImage;

import javax.swing.*;
import java.awt.*;

public class StartState extends State {

    private ButtonStart buttonStart;

    public StartState() {
        buttonStart = new ButtonStart(285,315);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.drawImage(CreateImage.home_background, 0, 0, 930, 730, null);
        g.drawImage(CreateImage.mainLogo, 195, 50, null);
        buttonStart.render(g);
    }
}
