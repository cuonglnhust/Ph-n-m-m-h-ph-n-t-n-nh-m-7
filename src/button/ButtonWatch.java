package button;

import SCCommon.ConnectionData;
import graphics.CreateImage;
import main.Handler;
import rmi.client.ClientViewer;
import state.GameState;
import state.HomeState;
import state.State;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;

public class ButtonWatch extends Button {

    private int serverId;

    public ButtonWatch(int x, int y, int serverId) {
        super(x, y);
        this.serverId = serverId;
    }

    public void tick() {
        if (isOver()) {
            if (Handler.getInstance().getMouse().isLeftClick() || Handler.getInstance().getMouse().isRightClick()) {
                Handler.getInstance().getMouse().setDefaultClick();

                // gửi yêu cầu lên Server để xem trận đấu
                try {

                    // id máy chủ
                    System.out.println("Id máy chủ = " + serverId);

                    ConnectionData connectionData = Handler.getInstance().getClientLogin().getiServer().requestViewMatch(serverId);

                    // lấy về kết nối
                    System.out.println("Connection data = " + connectionData.getIp() + connectionData.getPort() + connectionData.getBindName());

                    // sử dụng dữ liệu để tạo ClientViewer
                    ClientViewer clientViewer = new ClientViewer(connectionData);
                    Handler.getInstance().setClientViewer(clientViewer);
                    Handler.getInstance().getClientViewer().connection();
                    if (Handler.getInstance().getClientViewer().getWatchMatchServer().hello()) {
                        // nếu kết nối thành công thì chuyển trạng thái
                        State.setCurrentState(new GameState());
                        System.out.println("Đã kết nối ServerViewer thành công ");
                    } else {
                        // hiển thị hộp thoại
                        Object[] options = {"OK"};
                        int option = JOptionPane.showOptionDialog(null,
                                "Vui lòng chờ. Trận đấu đang được tạo ", "Message",
                                JOptionPane.PLAIN_MESSAGE,
                                JOptionPane.QUESTION_MESSAGE,
                                null,
                                options,
                                options[0]);
                    }

                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    @Override
    public void render(Graphics g) {
        if (isOver()) {
            g.drawImage(CreateImage.buttonWatch[1], x, y, null);
        } else {
            g.drawImage(CreateImage.buttonWatch[0], x, y, null);
        }
    }

    @Override
    public Rectangle getBound() {
        int width = CreateImage.buttonWatch[0].getWidth();
        int height = CreateImage.buttonWatch[0].getHeight();
        return new Rectangle(x, y, width, height);
    }


}
