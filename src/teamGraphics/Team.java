package teamGraphics;

import entity.unchanged.Cage;
import entity.unchanged.DicePlace;
import entity.unchanged.Rank;
import entity.unchanged.Step;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Team {

    final int RANK_DISTANCE = 40;
    final int STEP_DISTANCE = 40;
    final int NUMBER_OF_RANK = 6;
    final int NUMBER_OF_STEP = 6;

    Cage cage;
    DicePlace dicePlace;
    ArrayList<Rank> ranks;
    HashMap<Integer, Step> steps;

    protected abstract void initialRank();

    protected abstract void initialStep();

    public void render(Graphics g) {
        cage.render(g);
        dicePlace.render(g);
        for (Rank rank : ranks) {
            rank.render(g);
        }
        ArrayList<Step> stepTemp = new ArrayList<>(steps.values());
        for (Step step : stepTemp) {
            step.render(g);
        }
    }

    public HashMap<Integer, Step> getSteps() {
        return steps;
    }

    public ArrayList<Rank> getRanks() {
        return ranks;
    }
}
