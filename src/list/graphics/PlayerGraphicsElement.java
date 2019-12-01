package list.graphics;

import button.ButtonInvite;
import button.OnClickButton;
import graphics.CreateFont;
import list.constant.PlayerElementConstant;
import list.caculate.PlayerCaculateElement;
import list.data.PlayerDataElement;
import main.Handler;
import rmi.client.ClientPlayer;
import rmi.dataLogin.ConnectionData;
import rmi.server.ServerPlayer;
import state.ChoseTeamState;
import state.State;

import java.awt.*;

public class PlayerGraphicsElement {

    private int id;
    private TextGraphicsElement textGraphicsElement;
    private ButtonInvite buttonInvite;
    private PlayerDataElement playerDataElement;

    public PlayerGraphicsElement(int id, PlayerDataElement playerDataElement) {
        this.id = id;
        PlayerCaculateElement playerCaculateElement = new PlayerCaculateElement(id);
        this.textGraphicsElement = new TextGraphicsElement(PlayerElementConstant.FIRST_ELEMENT_X,
                PlayerElementConstant.TEXT_ELEMENT_WIDTH, PlayerElementConstant.TEXT_ELEMENT_HEIGHT,
                playerDataElement.getPlayerName(), playerCaculateElement.getBottomLine(), CreateFont.homeFont);
        Point buttonInviteCoordinate = playerCaculateElement.getButtonInvite();
        this.buttonInvite = new ButtonInvite(buttonInviteCoordinate.x, buttonInviteCoordinate.y, playerDataElement,
                    new PlayerDataElement(Handler.getInstance().getId(),Handler.getInstance().getName()));
       // this.buttonInvite.setOnClickButton(setOnClickButtonInvite());

    }

    private OnClickButton setOnClickButtonInvite() {
        return new OnClickButton() {
            @Override
            public void onClick() {
                // gửi yêu cầu lên Server

                // Nhận về thông tin dạng ConnectionData
                ConnectionData connectionData = new ConnectionData("127.0.0.1", 5000, "game");

                // Thiết lập kết nối
//                ServerPlayer clientPlayer = new ClientPlayer(connectionData);
//                Handler.getInstance().setServerPlayer(clientPlayer);

                // nếu kết nối thành công
                if (Handler.getInstance().getServerPlayer().connection()){
                    State.setCurrentState(new ChoseTeamState());
                }
                // nếu không thành công
                else{

                }
            }
        };
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
