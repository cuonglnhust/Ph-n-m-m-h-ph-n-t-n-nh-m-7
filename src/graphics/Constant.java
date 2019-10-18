package graphics;

import constant.TeamType;
import map.EntityPosition;

import java.awt.*;
import java.util.HashMap;

public class Constant {
    public static HashMap<TeamType, Integer> teamLastPoint;

    public static HashMap<Integer, Point> blueHorseTeam, redHorseTeam, orangeHorseTeam, violetHorseTeam;

    public static void init() {
        initTeamLastPoint();
    }

    private static void initTeamLastPoint() {
        teamLastPoint.put(TeamType.TEAM_VIOLET, 13);
        teamLastPoint.put(TeamType.TEAM_ORANGE, 27);
        teamLastPoint.put(TeamType.TEAM_RED, 41);
        teamLastPoint.put(TeamType.TEAM_BLUE, 55);
    }

    // khởi tạo vị trí cho ngựa trong chuồng
    private static void initHorseTeam(TeamType teamType) {
        switch (teamType) {
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
