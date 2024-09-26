package state;

import Login.Login;
import button.ButtonStart;
import graphics.CreateImage;

import javax.swing.*;
import java.awt.*;

public class StartState extends State {

    private ButtonStart buttonStart;
    private Login login;

    public StartState() {
        buttonStart = new ButtonStart(285,315,this);
    }

    @Override
    public void tick() {
            buttonStart.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(CreateImage.home_background, 0, 0, 930, 730, null);
        g.drawImage(CreateImage.mainLogo, 195, 50, null);
        buttonStart.render(g);
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public Login getLogin() {
        return login;
    }
}
