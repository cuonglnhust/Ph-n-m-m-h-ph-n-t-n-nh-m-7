package graphics;

import java.util.HashMap;

public class Constant {
    public static HashMap<Integer, Integer> teamLastPoint;


    public static void init() {
        initTeamLastPoint();
    }

    private static void initTeamLastPoint() {
        teamLastPoint.put(1, 13);
        teamLastPoint.put(2, 27);
        teamLastPoint.put(3, 41);
        teamLastPoint.put(4, 55);
    }
}
