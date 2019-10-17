package graphics;

import map.EntityPosition;

import java.awt.*;
import java.util.HashMap;

public class Constant {
    public static HashMap<Integer, Integer> teamLastPoint;

    public static HashMap<Integer, Point> blueHorseTeam, redHorseTeam, orangeHorseTeam, violetHorseTeam;

    public static final int TEAM_BLUE = 1;
    public static final int TEAM_RED = 4;
    public static final int TEAM_VIOLET = 2;
    public static final int TEAM_ORANGE = 3;

    public static void init() {
        initTeamLastPoint();
    }

    private static void initTeamLastPoint() {
        teamLastPoint.put(TEAM_VIOLET, 13);
        teamLastPoint.put(TEAM_ORANGE, 27);
        teamLastPoint.put(TEAM_RED, 41);
        teamLastPoint.put(TEAM_BLUE, 55);
    }

    // khởi tạo vị trí cho ngựa trong chuồng
    private static void initHorseTeam(int team) {
        switch (team) {
            case TEAM_BLUE:
                for (int i = 0; i < 4; i++) {
                    blueHorseTeam.put(i, new Point(EntityPosition.BLUE_HORSE_X_MIN + i * 30,
                            EntityPosition.BLUE_HORSE_Y));
                }
                break;
            case TEAM_RED:
                for (int i = 0; i < 4; i++) {
                    redHorseTeam.put(i, new Point(EntityPosition.RED_HORSE_X_MIN + i * 30,
                            EntityPosition.RED_HORSE_Y));
                }
                break;
            case TEAM_ORANGE:
                for (int i = 0; i < 4; i++) {
                    orangeHorseTeam.put(i, new Point(EntityPosition.ORANGE_HORSE_X_MIN + i * 30,
                            EntityPosition.ORANGE_HORSE_Y));
                }
                break;
            case TEAM_VIOLET:
                for (int i = 0; i < 4; i++) {
                    violetHorseTeam.put(i, new Point(EntityPosition.VIOLET_HORSE_X_MIN + i * 30,
                            EntityPosition.VIOLET_HORSE_Y));
                }
                break;

        }

    }



}
