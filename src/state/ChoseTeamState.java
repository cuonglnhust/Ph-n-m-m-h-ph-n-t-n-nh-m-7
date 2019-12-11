package state;

import SCCommon.Match;
import SCCommon.Player;
import button.ButtonPlay;
import button.ButtonTeam;
import button.PlayerData;
import constant.TeamType;
import graphics.CreateImage;
import main.Handler;
import rmi.client.ClientPlayer;
import SCCommon.ConnectionData;
import rmi.server.ServerPlayer;
import rmi.server.ServerViewer;

import javax.swing.*;
import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public class ChoseTeamState extends State implements Remote {

    private ButtonTeam blueTeam, redTeam, orangeTeam, violetTeam;
    private ButtonPlay buttonPlay;
    private TeamType choseTeam;
    private JDialog dialogClientWait, dialogServerWait;

    public ChoseTeamState(ConnectionData connectionData, Player player2) {
        blueTeam = new ButtonTeam(150, 240, CreateImage.blueBackground, TeamType.TEAM_BLUE, this);
        redTeam = new ButtonTeam(540, 240, CreateImage.redBackground, TeamType.TEAM_RED, this);
        orangeTeam = new ButtonTeam(150, 480, CreateImage.orangeBackground, TeamType.TEAM_ORANGE, this);
        violetTeam = new ButtonTeam(540, 480, CreateImage.violetBackground, TeamType.TEAM_VIOLET, this);
        buttonPlay = new ButtonPlay(800, 650, this);
        choseTeam = TeamType.NONE;
        openServer(connectionData, player2);
    }

    public ChoseTeamState(ConnectionData connectionData) {
        blueTeam = new ButtonTeam(150, 240, CreateImage.blueBackground, TeamType.TEAM_BLUE, this);
        redTeam = new ButtonTeam(540, 240, CreateImage.redBackground, TeamType.TEAM_RED, this);
        orangeTeam = new ButtonTeam(150, 480, CreateImage.orangeBackground, TeamType.TEAM_ORANGE, this);
        violetTeam = new ButtonTeam(540, 480, CreateImage.violetBackground, TeamType.TEAM_VIOLET, this);
        buttonPlay = new ButtonPlay(800, 650, this);
        choseTeam = TeamType.NONE;
        connectToServer(connectionData);
    }


    private void openServer(ConnectionData connectionData, Player player2) {
        System.out.println("Connect Server IP - " + connectionData.getIp());
        ServerPlayer modePlayer = new ServerPlayer(connectionData);
        Handler.getInstance().setServerPlayer(modePlayer);
        if (Handler.getInstance().getServerPlayer().connection()) {
            System.out.println("Server OK");
            Handler.getInstance().getServerPlayer().getChoseTeamServerImp().setChoseTeamState(this);
            dialogServerWait = createWaitDialog("Others player are not ready. Please wait ... ", "Message");
            Match match = new Match();
            List<Player> players = new ArrayList<>();
            players.add(player2);
            players.add(Handler.getInstance().getClientLogin().getPlayer());
            match.setPlayers(players);
            match.setId(Handler.getInstance().getId());
            try {
                // Mở server cho người xem
                ServerViewer serverViewer = new ServerViewer(Handler.getInstance().getConnectionToWatch());
                Handler.getInstance().setServerViewer(serverViewer);
                if (Handler.getInstance().getServerViewer().connection()) {

                    // mở Server cho người xem thành công
                    System.out.println("Server Viewer OK");

                    // gửi thông tin connectionData cho Server trung gian
                    Handler.getInstance().getClientLogin().getiServer().createNewViewServer(Handler.getInstance().getId(),
                            Handler.getInstance().getConnectionToWatch());
                }

                // gửi match lên cho server và thông báo cho P2 là phòng đã tạo
                Handler.getInstance().getClientLogin().getiServer().sendMatchtoServer(Handler.getInstance().getId(), match, player2, connectionData);


            } catch (RemoteException e) {
                e.printStackTrace();
            }

        }

    }

    private void connectToServer(ConnectionData connectionData) {
        System.out.println("Connect Client IP - " + connectionData.getIp());
        ClientPlayer clientPlayer = new ClientPlayer(connectionData);
        Handler.getInstance().setClientPlayer(clientPlayer);
        if (Handler.getInstance().getClientPlayer().connection()) {
            System.out.println("Client connect");
            Handler.getInstance().getClientPlayer().getChoseTeamClientImp().setChoseTeamState(this);
            dialogClientWait = createWaitDialog("Please wait .... ", "Message");
            try {
                // lấy số người chơi trong trận
                Handler.getInstance().setPlayerCount(Handler.getInstance().getClientPlayer().getChoseTeamServer().getPlayerCount());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public void allReady() {
        Object[] option = {"OK"};
        int input = JOptionPane.showOptionDialog(null, "All player are ready. Click OK to play game.", "Message", JOptionPane.PLAIN_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
        System.out.println("Input = " + input);
        // nếu click ok
        if (input == 0) {
            // chuyển trạng thái GameState
            Handler.getInstance().getServerPlayer().getChoseTeamServerImp().setServerReady(true);
            this.changeState();
        }
    }

    public void changeState() {
        List<PlayerData> playerDataList = new ArrayList<>();
        PlayerData blue = createPlayerFromButton(blueTeam);
        PlayerData red = createPlayerFromButton(redTeam);
        PlayerData violet = createPlayerFromButton(violetTeam);
        PlayerData orange = createPlayerFromButton(orangeTeam);
        if (blue != null) playerDataList.add(blue);
        if (red != null) playerDataList.add(red);
        if (violet != null) playerDataList.add(violet);
        if (orange != null) playerDataList.add(orange);
        State.setCurrentState(new GameState(playerDataList));
    }

    private PlayerData createPlayerFromButton(ButtonTeam buttonTeam) {
        if (buttonTeam.isChose()) {
            return new PlayerData(buttonTeam.getId(), buttonTeam.getName(), buttonTeam.getTeamType(), true);
        } else if (!buttonTeam.isCanChose()) {
            return new PlayerData(buttonTeam.getId(), buttonTeam.getName(), buttonTeam.getTeamType(), false);
        } else return null;
    }

    private JDialog createWaitDialog(String message, String title) {
        JOptionPane optionPane = new JOptionPane(message, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = new JDialog(Handler.getInstance().getGame().getDisplay().getjFrame(), title);
        dialog.setModal(true);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        // căn giữa cho dialog
        dialog.setLocation(dialog.getParent().getWidth() / 2 - dialog.getWidth() / 2, dialog.getParent().getHeight() / 2 - dialog.getHeight() / 2);
        dialog.pack();
        return dialog;
    }

    @Override
    public void tick() {
        blueTeam.tick();
        redTeam.tick();
        violetTeam.tick();
        orangeTeam.tick();
        buttonPlay.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(CreateImage.home_background, 0, 0, 930, 730, null);
        g.drawImage(CreateImage.mainLogo, 195, 50, null);
        blueTeam.render(g);
        redTeam.render(g);
        orangeTeam.render(g);
        violetTeam.render(g);
        buttonPlay.render(g);
    }

    // số nút đã được chọn
    public int buttonClickCount() {
        int count = 0;
        if (blueTeam.isChose() || !blueTeam.isCanChose()) count++;
        if (redTeam.isChose() || !redTeam.isCanChose()) count++;
        if (violetTeam.isChose() || !violetTeam.isCanChose()) count++;
        if (orangeTeam.isChose() || !orangeTeam.isCanChose()) ++count;
        return count;
    }

    public ButtonTeam getBlueTeam() {
        return blueTeam;
    }

    public ButtonTeam getRedTeam() {
        return redTeam;
    }

    public ButtonTeam getOrangeTeam() {
        return orangeTeam;
    }

    public ButtonTeam getVioletTeam() {
        return violetTeam;
    }

    public ButtonPlay getButtonPlay() {
        return buttonPlay;
    }

    public TeamType getChoseTeam() {
        return choseTeam;
    }

    public void setChoseTeam(TeamType choseTeam) {
        this.choseTeam = choseTeam;
    }

    public JDialog getDialogClientWait() {
        return dialogClientWait;
    }

    public void setDialogClientWait(JDialog dialogClientWait) {
        this.dialogClientWait = dialogClientWait;
    }

    public JDialog getDialogServerWait() {
        return dialogServerWait;
    }

}
