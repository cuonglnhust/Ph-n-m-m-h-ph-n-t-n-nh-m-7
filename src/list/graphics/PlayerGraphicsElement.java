package list.graphics;

import button.ButtonInvite;
import graphics.CreateFont;
import list.constant.PlayerElementConstant;
import list.caculate.PlayerCaculateElement;

import java.awt.*;

public class PlayerGraphicsElement {

    private int id;
    private TextGraphicsElement textGraphicsElement;
    private ButtonInvite buttonInvite;

    public PlayerGraphicsElement(int id, String player) {
        this.id = id;
        PlayerCaculateElement playerCaculateElement = new PlayerCaculateElement(id);
        this.textGraphicsElement = new TextGraphicsElement(PlayerElementConstant.FIRST_ELEMENT_X,
                PlayerElementConstant.TEXT_ELEMENT_WIDTH, PlayerElementConstant.TEXT_ELEMENT_HEIGHT,
                player, playerCaculateElement.getBottomLine(), CreateFont.homeFont);
        Point buttonInviteCoordinate = playerCaculateElement.getButtonInvite();
        this.buttonInvite = new ButtonInvite(buttonInviteCoordinate.x, buttonInviteCoordinate.y);
    }

    public void tick(){}

    public void render(Graphics g){
        textGraphicsElement.render(g);
        buttonInvite.render(g);
    }
}
