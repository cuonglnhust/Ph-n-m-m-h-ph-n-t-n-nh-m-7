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

public class Blue extends Team {

    private final Color BLUE_RANK_BACKGROUND = Color.decode("#8080ff");
    private final Position position = new Position(0, 55, 1, 12);


    public Blue() {
        cage = new Cage(CreateImage.blueBackground, EntityPosition.BLUE_CAGE_X, EntityPosition.BLUE_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.blueDice, EntityPosition.BLUE_DICE_PLACE_X, EntityPosition.BLUE_DICE_PLACE_Y);
        initialRank();
        initialStep();
    }

    @Override
    protected void initialRank() {
        ranks = new ArrayList<>();
        int blueYMin = EntityPosition.BLUE_RANK_Y_MIN;
        for (int i = 0; i < NUMBER_OF_RANK; i++) {
            ranks.add(new Rank(CreateImage.blueRanks.get(i), EntityPosition.BLUE_RANK_X, blueYMin, true, BLUE_RANK_BACKGROUND));
            blueYMin += RANK_DISTANCE;
        }
    }

    @Override
    protected void initialStep() {
        steps = new HashMap<>();
        // step First
        steps.put(position.getFirstPosition(), new Step(CreateImage.bluePoint, EntityPosition.BLUE_FIRST_X, EntityPosition.BLUE_FIRST_Y));

        // step End
        steps.put(position.getLastPosition(), new Step(CreateImage.blueCircle, EntityPosition.BLUE_END_X, EntityPosition.BLUE_END_Y));

        // step còn lại
        int blueHorizontalYMin = EntityPosition.BLUE_STEP_HORIZONTAL_FIRST_Y;
        int blueVerticalXMin = EntityPosition.BLUE_STEP_VERTICAL_FIRST_X;
        int firstHorizontalPosition = position.getFirstHorizontalPosition();
        int firstVerticalPosition = position.getFirstVerticalPosition();

        // dọc tăng position, ngang giảm position

        // horizontal end vertical initial
        for (int i = 0; i < NUMBER_OF_STEP; i++) {
            steps.put(firstHorizontalPosition, new Step(CreateImage.blueCircle, EntityPosition.BLUE_STEP_HORIZONTAL_X, blueHorizontalYMin));
            steps.put(firstVerticalPosition, new Step(CreateImage.blueCircle, blueVerticalXMin, EntityPosition.BLUE_STEP_VERTICAL_Y));
            blueHorizontalYMin += STEP_DISTANCE;
            blueVerticalXMin += STEP_DISTANCE;
            firstHorizontalPosition++;
            firstVerticalPosition--;
        }
    }
}
