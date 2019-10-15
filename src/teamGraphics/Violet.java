package teamGraphics;

import entity.unchanged.Cage;
import entity.unchanged.DicePlace;
import entity.unchanged.Rank;
import entity.unchanged.Step;
import graphics.CreateImage;
import map.EntityPosition;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Violet extends Team {

    private final Color VIOLET_RANK_BACKGROUND = Color.decode("#b560b4");
    private final int FIRST_POSITION = 14;
    private final int LAST_POSITION = 13;
    private final int FIRST_HORIZONTAL_POSITION = 21;
    private final int FIRST_VERTICAL_POSITION = 15;



    public Violet() {
        cage = new Cage(CreateImage.violetBackground, EntityPosition.VIOLET_CAGE_X, EntityPosition.VIOLET_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.violetDice, EntityPosition.VIOLET_DICE_PLACE_X, EntityPosition.VIOLET_DICE_PLACE_Y);
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
        steps = new HashMap<>();
        steps.put(FIRST_POSITION,new Step(CreateImage.violetPoint, EntityPosition.VIOLET_FIRST_X, EntityPosition.VIOLET_FIRST_Y));
        steps.put(LAST_POSITION,new Step(CreateImage.violetCircle, EntityPosition.VIOLET_END_X, EntityPosition.VIOLET_END_Y));
        int violetHorizontalMinY = EntityPosition.VIOLET_STEP_HORIZONTAL_FIRST_Y;
        int violetVerticalMinX = EntityPosition.VIOLET_STEP_VERTICAL_FIRST_X;
        int firstHorizontalPosition = FIRST_HORIZONTAL_POSITION;
        int firstVerticalPosition = FIRST_VERTICAL_POSITION;
        // ngang tăng, dọc tăng
        for (int i = 0; i < NUMBER_OF_STEP; i++) {
            steps.put(firstVerticalPosition,new Step(CreateImage.violetCircle, violetVerticalMinX, EntityPosition.VIOLET_STEP_VERTICAL_Y));
            steps.put(firstHorizontalPosition,new Step(CreateImage.violetCircle, EntityPosition.VIOLET_STEP_HORIZONTAL_X, violetHorizontalMinY));
            violetHorizontalMinY += STEP_DISTANCE;
            violetVerticalMinX += STEP_DISTANCE;
            firstHorizontalPosition++;
            firstVerticalPosition++;
        }
    }
}
