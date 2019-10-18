package map;


import constant.TeamType;
import entity.unchanged.Step;
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

    private Blue blueTeam;
    private Red redTeam;
    private Orange orangeTeam;
    private Violet violetTeam;

    // Map để vẽ
    private HashMap<Integer, Step> mapGraphics = new HashMap<>();

    // Map để xem toàn bộ ngựa trên đường
    private int[] virtualMap = new int[56];

    public Map() {

        blueTeam = new Blue();
        violetTeam = new Violet();
        orangeTeam = new Orange();
        redTeam = new Red();

        Stream.of(blueTeam.getSteps(), violetTeam.getSteps(), orangeTeam.getSteps(), redTeam.getSteps()).
                flatMap(m -> m.entrySet().stream()).collect(Collectors.toMap(java.util.Map.Entry::getKey, java.util.Map.Entry::getValue));

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
        g.drawImage(CreateImage.dice, EntityPosition.ORANGE_DICE_PLACE_X + 25, EntityPosition.ORANGE_DICE_PLACE_Y + 25, 50, 50, null);
        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN, EntityPosition.RED_HORSE_Y, null);
        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN + 30, EntityPosition.RED_HORSE_Y, null);
        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN + 30 * 2, EntityPosition.RED_HORSE_Y, null);
        g.drawImage(CreateImage.redHorse, EntityPosition.RED_HORSE_X_MIN + 30 * 3, EntityPosition.RED_HORSE_Y, null);

        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN, EntityPosition.BLUE_HORSE_Y, null);
        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN + 30, EntityPosition.BLUE_HORSE_Y, null);
        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN + 30 * 2, EntityPosition.BLUE_HORSE_Y, null);
        g.drawImage(CreateImage.blueHorse, EntityPosition.BLUE_HORSE_X_MIN + 30 * 3, EntityPosition.BLUE_HORSE_Y, null);

    }

    public HashMap<Integer, Step> getMapGraphics() {
        return mapGraphics;
    }

    public int[] getVirtualMap() {
        return virtualMap;
    }
}
