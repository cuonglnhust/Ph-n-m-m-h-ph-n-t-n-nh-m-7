package map;


import constant.TeamType;
import entity.changed.Horse;
import entity.unchanged.Step;
import graphics.Constant;
import player.*;
import teamGraphics.Blue;
import teamGraphics.Orange;
import teamGraphics.Red;
import teamGraphics.Violet;
import graphics.CreateImage;

import java.awt.*;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        Stream.of(blueTeam.getSteps(), violetTeam.getSteps(), orangeTeam.getSteps(), redTeam.getSteps()).
                flatMap(m -> m.entrySet().stream()).collect(Collectors.toMap(java.util.Map.Entry::getKey, java.util.Map.Entry::getValue));
        initVirtualMap();
        initPlayer();
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
        playerBlue.render(g);
        playerRed.render(g);
        playerOrange.render(g);
        playerViolet.render(g);

//        g.drawImage(CreateImage.dice, EntityPosition.ORANGE_DICE_PLACE_X + 25, EntityPosition.ORANGE_DICE_PLACE_Y + 25, 50, 50, null);
//        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN, EntityPosition.RED_HORSE_Y, null);
//        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN + 30, EntityPosition.RED_HORSE_Y, null);
//        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN + 30 * 2, EntityPosition.RED_HORSE_Y, null);
//        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN + 30 * 3, EntityPosition.RED_HORSE_Y, null);
//
//        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN, EntityPosition.BLUE_HORSE_Y, null);
//        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN + 30, EntityPosition.BLUE_HORSE_Y, null);
//        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN + 30 * 2, EntityPosition.BLUE_HORSE_Y, null);
//        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN + 30 * 3, EntityPosition.BLUE_HORSE_Y, null);

    }

    public HashMap<Integer, Step> getMapGraphics() {
        return mapGraphics;
    }

    public TeamType[] getVirtualMap() {
        return virtualMap;
    }

    public void kickAss(int position) {

        switch (virtualMap[position]) {
            case TEAM_VIOLET: {
                if (playerViolet != null) {
                    for (Horse horse : playerViolet.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.iskickedAss();
                        }
                    }
                }
            }
            break;
            case TEAM_ORANGE: {
                if (playerOrange != null) {
                    for (Horse horse : playerOrange.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.iskickedAss();
                        }
                    }
                }
            }
            break;
            case TEAM_RED: {
                if (playerRed != null) {
                    for (Horse horse : playerRed.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.iskickedAss();
                        }
                    }
                }
            }
            break;
            case TEAM_BLUE: {
                if (playerBlue != null) {
                    for (Horse horse : playerBlue.getHorses()) {
                        if (horse.getPosition() == position) {
                            horse.iskickedAss();
                        }
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
}
