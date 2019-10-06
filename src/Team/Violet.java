package Team;

import entity.Cage;
import entity.DicePlace;
import entity.Rank;
import entity.Step;
import graphics.CreateImage;
import map.EntityPosition;

import java.awt.*;
import java.util.ArrayList;

public class Violet extends Team {

    private final Color VIOLET_RANK_BACKGROUND = Color.decode("#b560b4");

    public Violet() {
        cage = new Cage(CreateImage.violetBackground, EntityPosition.VIOLET_CAGE_X, EntityPosition.VIOLET_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.violetDice, EntityPosition.VIOLET_Dice_X, EntityPosition.VIOLET_Dice_Y);
        initialRank();
        initialStep();
    }

    @Override
    public void initialRank() {
        ranks = new ArrayList<>();
        int violetXMin = EntityPosition.VIOLET_RANK_X_MIN;
        for (int i = 0; i < NUMBER_OF_RANK; i++) {
            ranks.add(new Rank(CreateImage.violetRanks.get(i), violetXMin, EntityPosition.VIOLET_RANK_Y, false, VIOLET_RANK_BACKGROUND));
            violetXMin += RANK_DISTANCE;
        }
    }

    @Override
    public void initialStep() {
        steps = new ArrayList<>();
        steps.add(new Step(CreateImage.violetPoint, EntityPosition.VIOLET_FIRST_X, EntityPosition.VIOLET_FIRST_Y));
        steps.add(new Step(CreateImage.violetCircle, EntityPosition.VIOLET_END_X, EntityPosition.VIOLET_END_Y));
        int violetHorizontalMinY = EntityPosition.VIOLET_STEP_HORIZONTAL_FIRST_Y;
        int violetVerticalMinX = EntityPosition.VIOLET_STEP_VERTICAL_FIRST_X;
        for (int i = 0; i < NUMBER_OF_STEP; i++) {
            steps.add(new Step(CreateImage.violetCircle, violetVerticalMinX, EntityPosition.VIOLET_STEP_VERTICAL_Y));
            steps.add(new Step(CreateImage.violetCircle, EntityPosition.VIOLET_STEP_HORIZONTAL_X, violetHorizontalMinY));
            violetHorizontalMinY += STEP_DISTANCE;
            violetVerticalMinX += STEP_DISTANCE;
        }
    }
}
