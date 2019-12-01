package map.local;


import constant.TeamType;
import entity.changed.local.Horse;
import entity.unchanged.Step;
import graphics.Constant;
import player.local.*;
import teamGraphics.Blue;
import teamGraphics.Orange;
import teamGraphics.Red;
import teamGraphics.Violet;

import java.awt.*;
import java.util.HashMap;

public class Map {

    // Graphics cho các đội
    private Blue blueTeam;
    private Red redTeam;
    private Orange orangeTeam;
    private Violet violetTeam;

    // Các đội chơi
    private Player playerBlue, playerRed, playerOrange, playerViolet;

    // Map để vẽ
    private HashMap<Integer, Step> mapGraphics = new HashMap<>();

    // Map để xem toàn bộ ngựa trên đường
    private TeamType[] virtualMap = new TeamType[56];

    // Lượt chơi cho các đội
    private TeamType turn;

    public Map() {
        blueTeam = new Blue();
        violetTeam = new Violet();
        orangeTeam = new Orange();
        redTeam = new Red();
        mapGraphics.putAll(blueTeam.getSteps());
        mapGraphics.putAll(violetTeam.getSteps());
        mapGraphics.putAll(orangeTeam.getSteps());
        mapGraphics.putAll(redTeam.getSteps());
        initVirtualMap();
        initPlayer();
        initTurn();
        Constant.init();
    }

    // khởi tạo map ảo
    private void initVirtualMap() {
        for (int i = 0; i < 56; i++) {
            virtualMap[i] = TeamType.NONE;
        }
    }

    // khởi tạo người chơi
    private void initPlayer() {
        playerBlue = new PlayerBlue();
        playerOrange = new PlayerOrange();
        playerRed = new PlayerRed();
        playerViolet = new PlayerViolet();
    }

    // khởi tạo turn cho người chơi đầu tiên
    private void initTurn() {
        turn = TeamType.TEAM_BLUE;
    }

    public void tick() {
        playerBlue.tick();
        playerRed.tick();
        playerOrange.tick();
        playerViolet.tick();
    }

    public void render(Graphics g) {
        blueTeam.render(g);
        redTeam.render(g);
        orangeTeam.render(g);
        violetTeam.render(g);
        g.setColor(Color.red);
        g.drawRect(115, 15, 700, 700);
        playerBlue.render(g);
        playerRed.render(g);
        playerOrange.render(g);
        playerViolet.render(g);

    }




    public void kickAss(int position) {
        System.out.println("Map - kickAss position : " + position);
        switch (virtualMap[position]) {
            case TEAM_VIOLET: {
                if (playerViolet != null) {
                    for (Horse horse : playerViolet.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.isKickedAss();
                        }
                        horse.printHorse();
                    }
                }
            }
            break;
            case TEAM_ORANGE: {
                if (playerOrange != null) {
                    for (Horse horse : playerOrange.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.isKickedAss();
                        }
                        horse.printHorse();
                    }
                }
            }
            break;
            case TEAM_RED: {
                if (playerRed != null) {
                    for (Horse horse : playerRed.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.isKickedAss();
                        }
                        horse.printHorse();
                    }
                }
            }
            break;
            case TEAM_BLUE: {
                if (playerBlue != null) {
                    for (Horse horse : playerBlue.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.isKickedAss();
                        }
                        horse.printHorse();
                    }
                }
            }
            break;
        }
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

    public void changeTurn() {
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

    public HashMap<Integer, Step> getMapGraphics() {
        return mapGraphics;
    }

    public TeamType[] getVirtualMap() {
        return virtualMap;
    }

}
