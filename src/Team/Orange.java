package Team;

import entity.Cage;
import entity.DicePlace;
import entity.Rank;
import entity.Step;
import graphics.CreateImage;
import map.EntityPosition;

import java.awt.*;
import java.util.ArrayList;

public class Orange extends Team {

    private final Color ORANGE_RANK_BACKGROUND = Color.decode("#ffb163");

    public Orange() {
        cage = new Cage(CreateImage.orangeBackground, EntityPosition.ORANGE_CAGE_X, EntityPosition.ORANGE_CAGE_Y);
        dicePlace = new DicePlace(CreateImage.orangeDice, EntityPosition.ORANGE_Dice_X, EntityPosition.ORANGE_Dice_Y);
        initialRank();
        initialStep();
    }

    @Override
    public void initialRank() {
        ranks = new ArrayList<>();
        int orangeYMax = EntityPosition.ORANGE_RANK_Y_MAX;
        for (int i = 0; i < NUMBER_OF_RANK; i++) {
            ranks.add(new Rank(CreateImage.orangeRanks.get(i), EntityPosition.ORANGE_RANK_X, orangeYMax, true, ORANGE_RANK_BACKGROUND));
            orangeYMax -= RANK_DISTANCE;
        }
    }

    @Override
    public void initialStep() {
        steps = new ArrayList<>();
        steps.add(new Step(CreateImage.orangePoint, EntityPosition.ORANGE_FIRST_X, EntityPosition.ORANGE_FIRST_Y));
        steps.add(new Step(CreateImage.orangeCircle, EntityPosition.ORANGE_END_X, EntityPosition.ORANGE_END_Y));
        int orangeHorizontalMinY = EntityPosition.ORANGE_STEP_HORIZONTAL_FIRST_Y;
        int orangeVerticalMinX = EntityPosition.ORANGE_STEP_VERTICAL_FIRST_X;
        for (int i = 0; i < NUMBER_OF_STEP; i++) {
            steps.add(new Step(CreateImage.orangeCircle, EntityPosition.ORANGE_STEP_HORIZONTAL_X, orangeHorizontalMinY));
            steps.add(new Step(CreateImage.orangeCircle, orangeVerticalMinX, EntityPosition.ORANGE_STEP_VERTICAL_Y));
            orangeHorizontalMinY += STEP_DISTANCE;
            orangeVerticalMinX += STEP_DISTANCE;
        }
    }
}
