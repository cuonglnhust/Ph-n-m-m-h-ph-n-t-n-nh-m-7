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
    private final Position position = new Position(42,41,54,48);

    public Red() {
        cage = new Cage(CreateImage.redBackground, EntityPosition.RED_CAGE_X, EntityPosition.RED_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.redDice, EntityPosition.RED_DICE_PLACE_X, EntityPosition.RED_DICE_PLACE_Y);
        initialRank();
        initialStep();

    }

    @Override
    protected void initialRank() {
        ranks = new ArrayList<>();
        int redXMax = EntityPosition.RED_RANK_X_MAX;
        for (int i = 0; i < NUMBER_OF_RANK; i++) {
            ranks.add(new Rank(CreateImage.redRanks.get(i), redXMax, EntityPosition.RED_RANK_Y, false, RED_RANK_BACKGROUND));
            redXMax -= RANK_DISTANCE;
        }
    }

    @Override
    protected void initialStep() {
        steps = new HashMap<>();
        steps.put(position.getFirstPosition(),new Step(CreateImage.redPoint, EntityPosition.RED_FIRST_X, EntityPosition.RED_FIRST_Y));
        steps.put(position.getLastPosition(),new Step(CreateImage.redCircle, EntityPosition.RED_END_X, EntityPosition.RED_END_Y));
        int redHorizontalMinY = EntityPosition.RED_STEP_HORIZONTAL_FIRST_Y;
        int redVerticalMinX = EntityPosition.RED_STEP_VERTICAL_FIRST_X;
        int firstHorizontalPosition = position.getFirstHorizontalPosition();
        int firstVerticalPosition = position.getFirstVerticalPosition();
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
