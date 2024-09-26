package button;

import graphics.CreateImage;
import main.Handler;
import state.ChoseTeamState;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class ButtonPlay extends Button {

    private ChoseTeamState choseTeamState;
    private boolean isClicked;

    public ButtonPlay(int x, int y, ChoseTeamState choseTeamState) {
        super(x, y);
        this.choseTeamState = choseTeamState;
        this.isClicked = false;
    }

    public void tick() {
        if (isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                isClicked = true;
                // nếu là Client
                if (Handler.getInstance().getClientPlayer() != null) {
                    try {
                        // nếu đủ số đội chơi click chọn
                        if (choseTeamState.buttonClickCount() == Handler.getInstance().getPlayerCount()) {
                            // kiểm tra các client khác đã sẵn sàng chưa
                            boolean ready = Handler.getInstance().getClientPlayer().getChoseTeamServer()
                                    .messageReady(Handler.getInstance().getId());
                            // nếu tất cả client đã sẵn sàng
                            if (ready) {
                                // kiểm tra xem server đã sẵn sàng chưa
                                boolean serverReady = Handler.getInstance().getClientPlayer().getChoseTeamServer()
                                        .serverReady();
                                // nếu đã sẵn sàng
                                if (serverReady) {
                                    // mở khóa cho server
                                    Handler.getInstance().getClientPlayer().getChoseTeamServer().unlockServer();
                                    choseTeamState.changeState();
                                }
                                // nếu chưa, thông báo cho server
                                else {
                                    Handler.getInstance().getClientPlayer().getChoseTeamServer().allReady(Handler.getInstance().getId());
                                }
                            }
                            // nếu có client chưa sẵn sàng
                            else {
                                // mở hộp thoại
                                choseTeamState.getDialogClientWait().setVisible(true);
                            }
                        }
                        // nếu chưa đủ đội chọn, thông báo
                        else {
                            JOptionPane.showMessageDialog(null,
                                    "Others player have not aready chose their team. Please wait ... ",
                                    "Message", JOptionPane.PLAIN_MESSAGE);
                            Handler.getInstance().getMouse().setDefaultClick();
                        }

                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
                // nếu là Server
                if (Handler.getInstance().getServerPlayer() != null) {
                    // nếu đủ số đội chơi click chọn đội
                    if (choseTeamState.buttonClickCount() == Handler.getInstance().getPlayerCount()) {
                        Handler.getInstance().getServerPlayer().getChoseTeamServerImp().setServerReady(true);
                        // nếu các máy khác đã ready rồi
                        System.out.println("Server need click Play");
                        System.out.println(checkClientPlayerReady());
                        // nếu có client chưa sẵn sàng
                        if (!checkClientPlayerReady()) {
                            // hiển thị thông báo
                            choseTeamState.getDialogServerWait().setVisible(true);
                        }
                    }
                    // nếu không đủ
                    else {
                        JOptionPane.showMessageDialog(null,
                                "Others player have not aready chose their team. Please wait ... ",
                                "Message", JOptionPane.PLAIN_MESSAGE);
                        Handler.getInstance().getMouse().setDefaultClick();
                    }


                }
            }
        }
    }


    // kiểm tra xem các Client khác đã vào hay chưa
    private boolean checkClientPlayerReady() {
        return Handler.getInstance().getServerPlayer().getChoseTeamServerImp().getPlayerReady().size()
                == Handler.getInstance().getServerPlayer().getChoseTeamServerImp().getClientHashMap().size();
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonPlay[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonPlay[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonPlay[0].getWidth();
        int height = CreateImage.buttonPlay[0].getHeight();
        return new Rectangle(x, y, width, height);
    }

    public boolean isClicked() {
        return isClicked;
    }
}
