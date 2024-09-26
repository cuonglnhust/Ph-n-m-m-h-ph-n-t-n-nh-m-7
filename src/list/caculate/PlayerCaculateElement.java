package list.caculate;

import list.constant.PlayerElementConstant;

import java.awt.*;

public class PlayerCaculateElement {

    private int id;

    public PlayerCaculateElement(int id) {
        this.id = id;
    }

    public Point getButtonInvite() {
        int x = PlayerElementConstant.BUTTON_INVITE_X;
        int y = PlayerElementConstant.FIRST_ELEMENT_Y + PlayerElementConstant.ELEMENT_HEIGHT * id;
        return new Point(x, y);
    }

    public int getBottomLine() {
        return PlayerElementConstant.FIRST_ELEMENT_Y + PlayerElementConstant.ELEMENT_HEIGHT * id +
                PlayerElementConstant.ELEMENT_HEIGHT;
    }
}
