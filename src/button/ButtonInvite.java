package button;

import SCCommon.Player;
import graphics.CreateImage;
import main.Handler;
import state.ChoseTeamState;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class ButtonInvite extends Button {

    private Player player2;
    private JDialog dialogMessage;

    public ButtonInvite(int x, int y, Player player2) {
        super(x, y);
        this.player2 = player2;
        this.dialogMessage = createMessage();
    }

    public void tick() {
        if (isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Handler.getInstance().getMouse().setDefaultClick();
                try {
                    // mở hộp thoại thông báo mời
                    dialogMessage.setVisible(true);
                    Player player1 = new Player(Handler.getInstance().getId(), Handler.getInstance().getName());
                    Player player2 = new Player(this.player2.getPid(), this.player2.getPname());
                    if (Handler.getInstance().getClientLogin().getiServer().
                            sendInvitation(player1, player2, Handler.getInstance().getConnectionToPlay())) {
                        // nếu nhận lời mời
                        // tắt hộp thoại
                        dialogMessage.dispose();
                        // Mở Server, truyền vào P2 dùng để thông báo lên Server rằng đã mở phòng
                        State.setCurrentState(new ChoseTeamState(Handler.getInstance().getConnectionToPlay(), player2));

                    } else {
                        JOptionPane.showMessageDialog(null,
                                this.player2.getPname().toUpperCase() + " từ chối chơi cùng bạn hoặc đang trong trận ! ");
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                System.out.println("bạn có id là " + Handler.getInstance().getName() + " vừa mời người chơi có id là : " +
                        this.player2.getPid());

            }
        }
    }

    private JDialog createMessage() {
        JDialog jDialog = new JDialog(Handler.getInstance().getGame().getDisplay().getjFrame(), "Invitation !");
        JLabel l = new JLabel("             Bạn vừa mời " +
                this.player2.getPname().toUpperCase() + " chơi cùng !");
        jDialog.add(l);
        jDialog.setLocation(jDialog.getParent().getWidth() / 2 - jDialog.getWidth() / 2, jDialog.getParent().getHeight() / 2 - jDialog.getHeight() / 2);
        jDialog.setSize(265, 100);
        return jDialog;
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonInvite[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonInvite[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonInvite[0].getWidth();
        int height = CreateImage.buttonInvite[0].getHeight();
        return new Rectangle(x, y, width, height);
    }


}
