package map.remote;

import constant.TeamType;
import entity.changed.local.Horse;
import entity.unchanged.Step;
import graphics.Constant;
import player.client.*;
import player.server.PlayerRemote;
import teamGraphics.Blue;
import teamGraphics.Orange;
import teamGraphics.Red;
import teamGraphics.Violet;

import java.awt.*;
import java.util.HashMap;

public class MapRemote {
    // Graphics cho các đội
    private Blue blueTeam;
    private Red redTeam;
    private Orange orangeTeam;
    private Violet violetTeam;

    // Map để vẽ
    private HashMap<Integer, Step> mapGraphics = new HashMap<>();

    // Map để xem toàn bộ ngựa trên đường
    private TeamType[] virtualMap = new TeamType[56];

    // Lượt chơi cho các đội
    private TeamType turn;


    private void init() {
        blueTeam = new Blue();
        violetTeam = new Violet();
        orangeTeam = new Orange();
        redTeam = new Red();
        mapGraphics.putAll(blueTeam.getSteps());
        mapGraphics.putAll(violetTeam.getSteps());
        mapGraphics.putAll(orangeTeam.getSteps());
        mapGraphics.putAll(redTeam.getSteps());
        initVirtualMap();
        initTurn();
        Constant.init();
    }

    // khởi tạo map ảo
    private void initVirtualMap() {
        for (int i = 0; i < 56; i++) {
            virtualMap[i] = TeamType.NONE;
        }
    }

    // khởi tạo turn cho người chơi đầu tiên
    private void initTurn() {
        turn = TeamType.TEAM_BLUE;
    }

    public void tick() {

    }

    public void render(Graphics g) {
        blueTeam.render(g);
        redTeam.render(g);
        orangeTeam.render(g);
        violetTeam.render(g);
        g.setColor(Color.red);
        g.drawRect(115, 15, 700, 700);
    }

    public HashMap<Integer, Step> getMapGraphics() {
        return mapGraphics;
    }

    public TeamType[] getVirtualMap() {
        return virtualMap;
    }


    public void updateVirtualMapNone(int position) {
        virtualMap[position] = TeamType.NONE;
    }

    public void updateVirtualMap(int position, TeamType teamType) {
        virtualMap[position] = teamType;
    }

    public TeamType getTurn() {
        return turn;
    }

    public void changeTurn(TeamType turn) {
        switch (turn) {
            case TEAM_BLUE:
                turn = TeamType.TEAM_RED;
                break;
            case TEAM_RED:
                turn = TeamType.TEAM_ORANGE;
                break;
            case TEAM_ORANGE:
                turn = TeamType.TEAM_VIOLET;
                break;
            case TEAM_VIOLET:
                turn = TeamType.TEAM_BLUE;
                break;
        }
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
}
