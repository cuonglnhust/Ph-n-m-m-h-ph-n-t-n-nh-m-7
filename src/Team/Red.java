package Team;

import entity.Cage;
import entity.DicePlace;
import entity.Rank;
import entity.Step;
import graphics.CreateImage;
import map.EntityPosition;

import java.awt.*;
import java.util.ArrayList;

public class Red extends Team {

    private final Color RED_RANK_BACKGROUND = Color.decode("#fc7c7c");

    public Red() {
        cage = new Cage(CreateImage.redBackground, EntityPosition.RED_CAGE_X, EntityPosition.RED_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.redDice, EntityPosition.RED_Dice_X, EntityPosition.RED_Dice_Y);
        initialRank();
        initialStep();
    }

    @Override
    public void initialRank() {
        ranks = new ArrayList<>();
        int redXMax = EntityPosition.RED_RANK_X_MAX;
        for (int i = 0; i < NUMBER_OF_RANK; i++) {
            ranks.add(new Rank(CreateImage.redRanks.get(i), redXMax, EntityPosition.RED_RANK_Y, false, RED_RANK_BACKGROUND));
            redXMax -= RANK_DISTANCE;
        }
    }

    @Override
    public void initialStep() {
        steps = new ArrayList<>();
        steps.add(new Step(CreateImage.redPoint, EntityPosition.RED_FIRST_X, EntityPosition.RED_FIRST_Y));
        steps.add(new Step(CreateImage.redCircle, EntityPosition.RED_END_X, EntityPosition.RED_END_Y));
        int redHorizontalMinY = EntityPosition.RED_STEP_HORIZONTAL_FIRST_Y;
        int redVerticalMinX = EntityPosition.RED_STEP_VERTICAL_FIRST_X;
        for (int i = 0; i < NUMBER_OF_STEP; i++) {
            steps.add(new Step(CreateImage.redCircle, EntityPosition.RED_STEP_HORIZONTAL_X, redHorizontalMinY));
            steps.add(new Step(CreateImage.redCircle, redVerticalMinX, EntityPosition.RED_STEP_VERTICAL_Y));
            redHorizontalMinY += STEP_DISTANCE;
            redVerticalMinX += STEP_DISTANCE;
        }
    }
}
