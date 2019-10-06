package Team;

import entity.Cage;
import entity.DicePlace;
import entity.Rank;
import entity.Step;
import graphics.CreateImage;
import map.EntityPosition;

import java.awt.*;
import java.util.ArrayList;

public class Blue extends Team {

    private final Color BLUE_RANK_BACKGROUND = Color.decode("#8080ff");

    public Blue() {
        cage = new Cage(CreateImage.blueBackground, EntityPosition.BLUE_CAGE_X, EntityPosition.BLUE_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.blueDice, EntityPosition.BLUE_Dice_X, EntityPosition.BLUE_Dice_Y);
        initialRank();
        initialStep();
    }

    @Override
    public void initialRank() {
        ranks = new ArrayList<>();
        int blueYMin = EntityPosition.BLUE_RANK_Y_MIN;
        for (int i = 0; i < NUMBER_OF_RANK; i++) {
            ranks.add(new Rank(CreateImage.blueRanks.get(i), EntityPosition.BLUE_RANK_X, blueYMin, true, BLUE_RANK_BACKGROUND));
            blueYMin += RANK_DISTANCE;
        }
    }

    @Override
    public void initialStep() {
        steps = new ArrayList<>();
        // step First
        steps.add(new Step(CreateImage.bluePoint, EntityPosition.BLUE_FIRST_X, EntityPosition.BLUE_FIRST_Y));
        // step End
        steps.add(new Step(CreateImage.blueCircle, EntityPosition.BLUE_END_X, EntityPosition.BLUE_END_Y));
        // step còn lại
        int blueHorizontalYMin = EntityPosition.BLUE_STEP_HORIZONTAL_FIRST_Y;
        int blueVerticalXMin = EntityPosition.BLUE_STEP_VERTICAL_FIRST_X;
        // horizontal end vertical initial
        for (int i = 0; i < NUMBER_OF_STEP; i++) {
            steps.add(new Step(CreateImage.blueCircle, EntityPosition.BLUE_STEP_HORIZONTAL_X, blueHorizontalYMin));
            steps.add(new Step(CreateImage.blueCircle, blueVerticalXMin, EntityPosition.BLUE_STEP_VERTICAL_Y));
            blueHorizontalYMin += STEP_DISTANCE;
            blueVerticalXMin += STEP_DISTANCE;
        }
    }
}
