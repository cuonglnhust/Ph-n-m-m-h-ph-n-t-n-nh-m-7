package map.local;

import button.PlayerData;
import constant.TeamType;
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

public class MapTemp {
    // Graphics cho các đội
    private Blue blueTeam;
    private Red redTeam;
    private Orange orangeTeam;
    private Violet violetTeam;

    // đội chơi chính
    Player player;

    // đội remote
    HashMap<Integer, PlayerCopy> playerCopyHashMap;
    ArrayList<PlayerCopy> playerCopies;

    // Map để vẽ
    private HashMap<Integer, Step> mapGraphics = new HashMap<>();

    // Map để xem toàn bộ ngựa trên đường
    private TeamType[] virtualMap = new TeamType[56];

    // sắp xếp lượt các đội chơi
    private TeamType[] teamTypes;

    // cờ báo đổi lượt
    private boolean isChangeTurn;

    // Lượt chơi hiện tại
    private TeamType turn;

    // trạng thái kết quả
    private boolean isLose;

    // danh sách người chơi cùng
    List<SCCommon.Player> playerList = new ArrayList<>();

    // thời gian chơi
    private long duration;

    List<PlayerData> playerDataList;


    public MapTemp(List<PlayerData> playerDataList) {

        this.playerDataList = playerDataList;

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

        // thay đổi lượt
        isChangeTurn = false;


        initVirtualMap();

        initTurn(playerDataList);

        initPlayer(playerDataList);

        // khởi tạo danh sách các player copy
        playerCopies = new ArrayList<>(playerCopyHashMap.values());

        Constant.init();

        // khởi tạo lượt đầu tiên cho người chơi
        if (Handler.getInstance().getClientPlayer() != null) {
            Handler.getInstance().getClientPlayer().getPlayGameClientImp().setMapTemp(this);
            try {
                turn = Handler.getInstance().getClientPlayer().getPlayGameServer().getTurn();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else if (Handler.getInstance().getServerPlayer() != null) {
            Handler.getInstance().getServerPlayer().getPlayGameServerImp().setMapTemp(this);
            turn = player.getTeam();
        }

        duration = System.currentTimeMillis();
    }

    // khởi tạo map ảo
    private void initVirtualMap() {
        for (int i = 0; i < 56; i++) {
            virtualMap[i] = TeamType.NONE;
        }
    }

    // khởi tạo người chơi
    private void initPlayer(List<PlayerData> playerDataList) {
        // xét mảng PlayerData đầu vào
        for (PlayerData playerData : playerDataList) {
            // nếu flag = true -> là Player chính
            if (playerData.isFlag()) {
                switch (playerData.getTeamType()) {
                    case TEAM_RED:
                        player = new PlayerRed();
                        break;
                    case TEAM_BLUE:
                        player = new PlayerBlue();
                        break;
                    case TEAM_ORANGE:
                        player = new PlayerOrange();
                        break;
                    case TEAM_VIOLET:
                        player = new PlayerViolet();
                        break;
                }
            } else {
                switch (playerData.getTeamType()) {
                    case TEAM_RED:
                        PlayerCopy playerCopy1 = new PlayerCopyRed(playerData.getId());
                        playerCopyHashMap.put(playerData.getId(), playerCopy1);
                        break;
                    case TEAM_BLUE:
                        PlayerCopy playerCopy2 = new PlayerCopyBlue(playerData.getId());
                        playerCopyHashMap.put(playerData.getId(), playerCopy2);
                        break;
                    case TEAM_ORANGE:
                        PlayerCopy playerCopy3 = new PlayerCopyOrange(playerData.getId());
                        playerCopyHashMap.put(playerData.getId(), playerCopy3);
                        break;
                    case TEAM_VIOLET:
                        PlayerCopy playerCopy4 = new PlayerCopyViolet(playerData.getId());
                        playerCopyHashMap.put(playerData.getId(), playerCopy4);
                        break;
                }
            }

            SCCommon.Player player = new SCCommon.Player(playerData.getId(), playerData.getName());
            // thêm vào trong list
            playerList.add(player);
        }
    }

    // khởi tạo lượt chơi
    private void initTurn(List<PlayerData> playerDataList) {
        // tạo mảng các đội chơi
        teamTypes = new TeamType[playerDataList.size()];
        // lấy ra các đội chơi trong playerDataList
        for (int i = 0; i < teamTypes.length; i++) {
            teamTypes[i] = playerDataList.get(i).getTeamType();
        }

        // sắp xếp các thành phần trong mảng
        Arrays.sort(teamTypes, new Comparator<TeamType>() {
            @Override
            public int compare(TeamType o1, TeamType o2) {
                if (o1.ordinal() < o2.ordinal())
                    return -1;
                else return 1;
            }
        });
    }

    public void tick() {
        // nếu chưa thua
        if (!isLose) {

            // nếu cờ đổi lượt bật thì thay đổi lượt
            if (isChangeTurn) {
                changeTurnLocal();
                isChangeTurn = false;
            }

            // logic của player chính
            player.tick();
            for (PlayerCopy playerCopy : playerCopies) {
                playerCopy.tick();
            }
        } else {
            if (Handler.getInstance().getClientPlayer() != null) {
                Handler.getInstance().setClientPlayer(null);
            } else {
                Handler.getInstance().setServerPlayer(null);
                Handler.getInstance().setServerViewer(null);
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

        // vẽ player
        player.render(g);

        // vẽ các player copy
        for (PlayerCopy playerCopy : playerCopies) {
            playerCopy.render(g);
        }

        // nếu thua
        if (isLose) {
            // màn hình thua
            g.drawImage(CreateImage.lose, 100, 100, 730, 530, null);

            // hiển thị hộp thoại
            Object[] options = {"OK"};
            int option = JOptionPane.showOptionDialog(null,
                    "Bạn đã thua. Vui lòng nhấn OK để tiếp tục", "Message",
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

    // đổi lượt và quảng bá
    public void changeTurn() {
        System.out.println("Change Turn Map");
        changeTurnLocal();
        // quảng bá đổi lượt cho các máy khác
        // nếu là server
        if (Handler.getInstance().getServerPlayer() != null) {
            Handler.getInstance().getServerPlayer().getPlayGameServerImp().changeTurnFromServer();
        }
        // nếu là client
        else if (Handler.getInstance().getClientPlayer() != null) {
            try {
                Handler.getInstance().getClientPlayer().getPlayGameServer().changeTurn(Handler.getInstance().getId());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    // tự thay đổi lượt
    public void changeTurnLocal() {
        for (int i = 0; i < teamTypes.length; i++) {
            if (turn == teamTypes[i]) {
                if (i == teamTypes.length - 1) {
                    turn = teamTypes[0];
                } else {
                    turn = teamTypes[i + 1];
                }
                break;
            }
        }
        // thông báo đổi lượt cho các máy đang xem
        if (Handler.getInstance().getServerPlayer() != null) {
            if (Handler.getInstance().getServerViewer() != null) {
                Handler.getInstance().getServerViewer().getWatchMatchServerImp().changeTurn(turn);
            }
        }
    }

    // đá ngựa tự cập nhật trong nội bộ
    public void kickAss(int position) {
        TeamType teamTypes = virtualMap[position];
        playerCopies = new ArrayList<>(playerCopyHashMap.values());
        for (PlayerCopy playerCopy : playerCopies) {
            if (playerCopy.getTeam() == teamTypes) {
                for (HorseCopy horseCopy : playerCopy.getHorseCopies()) {
                    if (horseCopy.getPosition() == position) {
                        horseCopy.isKickedAss();
                        // quảng bá
                        // nếu là Client
                        if (Handler.getInstance().getClientPlayer() != null) {
                            try {
                                Handler.getInstance().getClientPlayer().getPlayGameServer().
                                        updateKickedHorsePosition(Handler.getInstance().getId(), playerCopy.getId()
                                                , position);
                            } catch (RemoteException e) {
                                e.printStackTrace();
                            }
                        }
                        // nếu là server
                        else if (Handler.getInstance().getServerPlayer() != null) {
                            Handler.getInstance().getServerPlayer().getPlayGameServerImp()
                                    .updateKickedHorsePositionFromServer(Handler.getInstance().getId(), playerCopy.getId(),
                                            position);
                        }
                        break;
                    }
                }
            }
        }
    }

    public TeamType[] getVirtualMap() {
        return virtualMap;
    }

    public HashMap<Integer, Step> getMapGraphics() {
        return mapGraphics;
    }

    public TeamType getTurn() {

        return turn;
    }

    public void updateVirtualMap(int position, TeamType teamType) {
        virtualMap[position] = teamType;
    }

    public void updateVirtualMapBroadcast(int position, TeamType teamType) {
        // tự cập nhật
        updateVirtualMap(position, teamType);
        System.out.println("Update local" + teamType.toString());
        // quảng bá
        // nếu là client
        if (Handler.getInstance().getClientPlayer() != null) {
            try {
                System.out.println("client - update");
                Handler.getInstance().getClientPlayer().getPlayGameServer().
                        updateVirtualMap(Handler.getInstance().getId(), position, teamType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        // nếu là server
        else if (Handler.getInstance().getServerPlayer() != null) {
            try {
                System.out.println("server  - update");
                Handler.getInstance().getServerPlayer().getPlayGameServerImp().
                        updateVirtualMapFromServer(Handler.getInstance().getId(), position, teamType);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

    }

    public HashMap<Integer, PlayerCopy> getPlayerCopyHashMap() {
        return playerCopyHashMap;
    }

    public void setChangeTurn(boolean changeTurn) {
        isChangeTurn = changeTurn;
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

    public Player getPlayer() {
        return player;
    }

    public void setLose(boolean lose) {
        isLose = lose;
    }

    public List<SCCommon.Player> getPlayerList() {
        return playerList;
    }

    public long getDuration() {
        return duration;
    }

    public List<PlayerData> getPlayerDataList() {
        return playerDataList;
    }
}
