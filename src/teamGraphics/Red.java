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

public class Red extends Team {

    private final Color RED_RANK_BACKGROUND = Color.decode("#fc7c7c");
    private final int FIRST_POSITION = 43;
    private final int LAST_POSITION = 42;
    private final int FIRST_HORIZONTAL_POSITION = 55;
    private final int FIRST_VERTICAL_POSITION = 49;


    public Red() {
        cage = new Cage(CreateImage.redBackground, EntityPosition.RED_CAGE_X, EntityPosition.RED_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.redDice, EntityPosition.RED_DICE_PLACE_X, EntityPosition.RED_DICE_PLACE_Y);
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
        steps = new HashMap<>();
        steps.put(FIRST_POSITION,new Step(CreateImage.redPoint, EntityPosition.RED_FIRST_X, EntityPosition.RED_FIRST_Y));
        steps.put(LAST_POSITION,new Step(CreateImage.redCircle, EntityPosition.RED_END_X, EntityPosition.RED_END_Y));
        int redHorizontalMinY = EntityPosition.RED_STEP_HORIZONTAL_FIRST_Y;
        int redVerticalMinX = EntityPosition.RED_STEP_VERTICAL_FIRST_X;
        int firstHorizontalPosition = FIRST_HORIZONTAL_POSITION;
        int firstVerticalPosition = FIRST_VERTICAL_POSITION;
        // dọc giảm, ngang giảm
        for (int i = 0; i < NUMBER_OF_STEP; i++) {
            steps.put(firstHorizontalPosition,new Step(CreateImage.redCircle, EntityPosition.RED_STEP_HORIZONTAL_X, redHorizontalMinY));
            steps.put(firstVerticalPosition,new Step(CreateImage.redCircle, redVerticalMinX, EntityPosition.RED_STEP_VERTICAL_Y));
            redHorizontalMinY += STEP_DISTANCE;
            redVerticalMinX += STEP_DISTANCE;
            firstHorizontalPosition--;
            firstVerticalPosition--;
        }
    }
}
