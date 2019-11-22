package button;

import constant.ModeType;
import graphics.CreateImage;
import main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ButtonTeam extends Button {

    private BufferedImage team;
    private boolean isChose;

    public ButtonTeam(int x, int y, BufferedImage team) {
        super(x, y);
        this.team = team;
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(team, x, y, null);
        if (isOver()) {
            g.setColor(new Color(145, 143, 135, 90));
            g.fillRect(getBound().x, getBound().y, getBound().width, getBound().height);
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Handler.getInstance().getMouse().setDefaultClick();
                isChose = !isChose;
            }
        }
        if (isChose) {
            g.drawImage(CreateImage.player1, x, y + (getBound().height / 2 - 40), getBound().width, 80, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.blueBackground.getWidth();
        int height = CreateImage.blueBackground.getHeight();
        return new Rectangle(x, y, width, height);
    }
}
