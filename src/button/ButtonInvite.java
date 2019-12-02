package button;

import SCCommon.Player;
import graphics.CreateImage;
import list.data.PlayerDataElement;
import main.Handler;
import state.ChoseTeamState;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class ButtonInvite extends Button {

    // OnClickButton onClickButton;
    private PlayerDataElement player2;

    private PlayerDataElement player1;

    public ButtonInvite(int x, int y, PlayerDataElement player2,PlayerDataElement player1) {
        super(x, y);
        this.player1 = player1;
        this.player2 = player2;
    }

    public void tick() {
        if (isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Handler.getInstance().getMouse().setDefaultClick();
//                if (onClickButton != null) {
//                    onClickButton.onClick();
//                }

                JDialog jd = new JDialog(Handler.getInstance().getGame().getDisplay().getjFrame(),"Invitation !");
                JLabel l = new JLabel("             Bạn vừa mời " +
                        this.player2.getPlayerName().toUpperCase() + " chơi cùng !");

                jd.add(l);
                jd.setLocation(jd.getParent().getWidth() / 2 - jd.getWidth() / 2, jd.getParent().getHeight() / 2 - jd.getHeight() / 2);
                jd.setSize(265, 100);

                jd.setVisible(true);

                try {
                    if(Handler.getInstance().getClientLogin().getiServer().
                            sendInvitation(new Player(this.player1.getIdPlayer(),this.player1.getPlayerName()),
                                    new Player(this.player2.getIdPlayer(),this.player2.getPlayerName()),Handler.getInstance().getConnection())){


                        State.setCurrentState(new ChoseTeamState(true,Handler.getInstance().getConnection()));
                        // neu true thi la player2 dong y choi va chuyen sang man hinh chon doi
//                        Match match = new Match();
//                        ConnectionData connectionData=null;
//                        try {
//                             connectionData = new ConnectionData(InetAddress.getLocalHost().getHostName(),5000,"conToPlay");
//                        } catch (UnknownHostException e) {
//                            e.printStackTrace();
//                        }
//                        ClientPlayer clientPlayer = new ClientPlayer(connectionData);
//
//                            Handler.getInstance().setClientPlayer(clientPlayer);
//                            // gửi match lên cho server
//                            Handler.getInstance().getClientLogin().getiServer().sendMatchtoServer(match,
//                                                            new Player(this.player1.getIdPlayer(),this.player1.getPlayerName()));

                    }else{
                        JOptionPane.showMessageDialog(null,
                                this.player2.getPlayerName().toUpperCase() + " từ chối chơi cùng bạn ! ");
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }


//                JOptionPane.showMessageDialog(null, "Bạn vừa mời " +
//                        this.player2.getPlayerName().toUpperCase() + " chơi cùng ! ");

                System.out.println("bạn có id là " + this.player1.getIdPlayer() + " vừa mời người chơi có id là : " +
                                                        this.player2.getIdPlayer());

            }
        }
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



    //    public void setOnClickButton(OnClickButton onClickButton) {
//        this.onClickButton = onClickButton;
//    }

}
