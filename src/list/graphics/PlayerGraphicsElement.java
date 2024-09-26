package list.graphics;

import SCCommon.Player;
import button.ButtonInvite;
import graphics.CreateFont;
import list.constant.PlayerElementConstant;
import list.caculate.PlayerCaculateElement;
import list.data.PlayerDataElement;

import java.awt.*;

public class PlayerGraphicsElement {

    private int id;
    private TextGraphicsElement textGraphicsElement;
    private ButtonInvite buttonInvite;
    private Player player;

    public PlayerGraphicsElement(int id, Player player) {
        this.id = id;
        PlayerCaculateElement playerCaculateElement = new PlayerCaculateElement(id);
        this.textGraphicsElement = new TextGraphicsElement(PlayerElementConstant.FIRST_ELEMENT_X,
                PlayerElementConstant.TEXT_ELEMENT_WIDTH, PlayerElementConstant.TEXT_ELEMENT_HEIGHT,
                player.getPname(), playerCaculateElement.getBottomLine(), CreateFont.homeFont);
        Point buttonInviteCoordinate = playerCaculateElement.getButtonInvite();
        this.buttonInvite = new ButtonInvite(buttonInviteCoordinate.x, buttonInviteCoordinate.y, player);
    }


    public void tick() {
        buttonInvite.tick();
    }

    public void render(Graphics g) {
        textGraphicsElement.render(g);
        buttonInvite.render(g);
    }

    public ButtonInvite getButtonInvite() {
        return buttonInvite;
    }

    public void setButtonInvite(ButtonInvite buttonInvite) {
        this.buttonInvite = buttonInvite;
    }
}
