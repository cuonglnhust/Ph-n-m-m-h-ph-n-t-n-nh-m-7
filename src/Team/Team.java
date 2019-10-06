package Team;

import entity.Cage;
import entity.DicePlace;
import entity.Rank;
import entity.Step;

import java.awt.*;
import java.util.ArrayList;

public abstract class Team {

    final int RANK_DISTANCE = 40;
    final int STEP_DISTANCE = 48;
    final int NUMBER_OF_RANK = 6;
    final int NUMBER_OF_STEP = 5;

    Cage cage;
    DicePlace dicePlace;
    ArrayList<Rank> ranks;
    ArrayList<Step> steps;

    public abstract void initialRank();

    public abstract void initialStep();

    public void render(Graphics g) {
        cage.render(g);
        dicePlace.render(g);
        for (Rank rank : ranks) {
            rank.render(g);
        }
        for (Step step : steps) {
            step.render(g);
        }
    }

    ;

}
