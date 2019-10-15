package graphics;

import java.util.HashMap;

public class Constant {
    public static HashMap<Integer, Integer> teamLastPoint;

    public static int TEAM_BLUE = 1;
    public static int TEAM_RED = 4;
    public static int TEAM_VIOLET = 2;
    public static int TEAM_ORANGE = 3;

    public static void init() {
        initTeamLastPoint();
    }

    private static void initTeamLastPoint() {
        teamLastPoint.put(TEAM_VIOLET, 13);
        teamLastPoint.put(TEAM_ORANGE, 27);
        teamLastPoint.put(TEAM_RED, 41);
        teamLastPoint.put(TEAM_BLUE, 55);
    }
}
