package map.local;

import button.PlayerData;
import constant.DiceValue;
import constant.TeamType;
import entity.changed.data.HorseData;
import entity.changed.remote.HorseCopy;
import entity.unchanged.Step;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import player.local.*;
import player.remote.*;
import state.HomeState;
import state.State;
import teamGraphics.Blue;
import teamGraphics.Orange;
import teamGraphics.Red;
import teamGraphics.Violet;

import javax.swing.*;
import java.awt.*;
import java.rmi.RemoteException;
import java.util.*;
import java.util.List;

public class MapCopy {
    // Graphics cho các đội
    private Blue blueTeam;
    private Red redTeam;
    private Orange orangeTeam;
    private Violet violetTeam;

    // đội remote
    HashMap<Integer, PlayerCopy> playerCopyHashMap;
    ArrayList<PlayerCopy> playerCopies;

    // Map để vẽ
    private HashMap<Integer, Step> mapGraphics = new HashMap<>();

    // Lượt chơi hiện tại
    private TeamType turn;

    // kết quả
    private boolean end;


    public MapCopy(List<PlayerData> playerDataList) {

        // các đội mô phỏng
        playerCopyHashMap = new HashMap<>();

        // giao diện nền đội chơi
        blueTeam = new Blue();
        violetTeam = new Violet();
        orangeTeam = new Orange();
        redTeam = new Red();

        // giao diện bước đi từng đội chơi
        mapGraphics.putAll(blueTeam.getSteps());
        mapGraphics.putAll(violetTeam.getSteps());
        mapGraphics.putAll(orangeTeam.getSteps());
        mapGraphics.putAll(redTeam.getSteps());

        // khởi tạo người chơi ảo
        initPlayerCopy(playerDataList);

        // khởi tạo danh sách các player copy
        playerCopies = new ArrayList<>(playerCopyHashMap.values());

        // khởi tạo trạng thái ban đầu cho các đội chơi
        initPlayerCopyState();

        Constant.init();

    }

    // khởi tạo các đội chơi mô phỏng
    private void initPlayerCopy(List<PlayerData> playerDataList) {
        for (PlayerData playerData : playerDataList) {
            switch (playerData.getTeamType()) {
                case TEAM_BLUE:
                    playerCopyHashMap.put(playerData.getId(), new PlayerCopyBlue(playerData.getId()));
                    break;
                case TEAM_RED:
                    playerCopyHashMap.put(playerData.getId(), new PlayerCopyRed(playerData.getId()));
                    break;
                case TEAM_ORANGE:
                    playerCopyHashMap.put(playerData.getId(), new PlayerCopyOrange(playerData.getId()));
                    break;
                case TEAM_VIOLET:
                    playerCopyHashMap.put(playerData.getId(), new PlayerCopyViolet(playerData.getId()));
                    break;
            }
        }
    }

    // khởi tạo trạng thái cho các đội chơi mô phỏng
    private void initPlayerCopyState() {
        try {
            for (PlayerCopy playerCopy : playerCopies) {
                // giá trị xúc xắc
                DiceValue diceValue = Handler.getInstance().getClientViewer().getWatchMatchServer().getPlayerDiceValue(playerCopy.getId());
                playerCopy.getDiceCopy().setDiceValue(diceValue);
                // vị trí ngựa
                HashMap<Integer, HorseData> horseDataHashMap = Handler.getInstance().getClientViewer()
                        .getWatchMatchServer().getPlayerHorseList(playerCopy.getId());
                for (HorseCopy horseCopy : playerCopy.getHorseCopies()) {
                    horseCopy.setData(horseDataHashMap.get(horseCopy.getId()));
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void tick() {
        if (!end) {
            for (PlayerCopy playerCopy : playerCopies) {
                playerCopy.tick();
            }
        } else {
            if (Handler.getInstance().getClientViewer() != null) {
                Handler.getInstance().setClientViewer(null);
            }
        }
    }

    public void render(Graphics g) {
        blueTeam.render(g);
        redTeam.render(g);
        orangeTeam.render(g);
        violetTeam.render(g);
        g.setColor(Color.red);
        g.drawRect(115, 15, 700, 700);

        // vẽ các player copy
        for (PlayerCopy playerCopy : playerCopies) {
            playerCopy.render(g);
        }

        if (end) {
            // hiển thị hộp thoại
            Object[] options = {"OK"};
            int option = JOptionPane.showOptionDialog(null,
                    "Trận đấu đã kết thúc. Vui lòng click OK để về trang chủ", "Message",
                    JOptionPane.PLAIN_MESSAGE,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);

            // nếu click ok
            if (option == JOptionPane.OK_OPTION) {

                // gửi thông tin lịch sử lên Server

                // chuyển trạng thái
                State.setCurrentState(new HomeState());
            }

        }
    }

    public HashMap<Integer, Step> getMapGraphics() {
        return mapGraphics;
    }

    public TeamType getTurn() {

        return turn;
    }

    public HashMap<Integer, PlayerCopy> getPlayerCopyHashMap() {
        return playerCopyHashMap;
    }

    public Blue getBlueTeam() {
        return blueTeam;
    }

    public Red getRedTeam() {
        return redTeam;
    }

    public Orange getOrangeTeam() {
        return orangeTeam;
    }

    public Violet getVioletTeam() {
        return violetTeam;
    }

    public void setTurn(TeamType turn) {
        this.turn = turn;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
