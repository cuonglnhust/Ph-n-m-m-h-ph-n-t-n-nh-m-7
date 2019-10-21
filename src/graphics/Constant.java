package graphics;

import constant.TeamType;
import map.EntityPosition;

import java.awt.*;
import java.util.HashMap;

public class Constant {

    public static HashMap<TeamType, Integer> teamLastPoint, teamFirstPoint;

    public static HashMap<Integer, Point> blueHorseTeam, redHorseTeam, orangeHorseTeam, violetHorseTeam;

    public static void init() {
        initTeamLastPoint();
        initTeamFirstPoint();
        initHorseBlue();
        initHorseViolet();
        initHorseOrange();
        initHorseRed();
    }

    private static void initTeamLastPoint() {
        teamLastPoint = new HashMap<>();
        teamLastPoint.put(TeamType.TEAM_VIOLET, 13);
        teamLastPoint.put(TeamType.TEAM_ORANGE, 27);
        teamLastPoint.put(TeamType.TEAM_RED, 41);
        teamLastPoint.put(TeamType.TEAM_BLUE, 55);
    }

    private static void initTeamFirstPoint() {
        teamFirstPoint = new HashMap<>();
        teamFirstPoint.put(TeamType.TEAM_BLUE, 0);
        teamFirstPoint.put(TeamType.TEAM_VIOLET, 14);
        teamFirstPoint.put(TeamType.TEAM_ORANGE, 28);
        teamFirstPoint.put(TeamType.TEAM_RED, 42);
    }

    public static void initHorseBlue(){
        blueHorseTeam = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            blueHorseTeam.put(i, new Point(EntityPosition.BLUE_HORSE_X_MIN + i * 30,
                    EntityPosition.BLUE_HORSE_Y));
        }
    }

    public static void initHorseViolet(){
        violetHorseTeam = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            violetHorseTeam.put(i, new Point(EntityPosition.VIOLET_HORSE_X_MIN + i * 30,
                    EntityPosition.VIOLET_HORSE_Y));
        }
    }

    public static void initHorseOrange(){
        orangeHorseTeam = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            orangeHorseTeam.put(i, new Point(EntityPosition.ORANGE_HORSE_X_MIN + i * 30,
                    EntityPosition.ORANGE_HORSE_Y));
        }
    }

    public static void initHorseRed(){
        redHorseTeam = new HashMap<>();
        for (int i = 0; i < 4; i++) {
            redHorseTeam.put(i, new Point(EntityPosition.RED_HORSE_X_MIN + i * 30,
                    EntityPosition.RED_HORSE_Y));
        }
    }


}
