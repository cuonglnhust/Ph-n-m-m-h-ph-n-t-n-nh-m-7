package graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CreateImage {

    public static BufferedImage redHorse, blueHorse, orangeHorse, violetHorse;
    public static BufferedImage redBackground, blueBackground, orangeBackground, violetBackground;
    public static BufferedImage redCircle, blueCircle, orangeCircle, violetCircle;
    public static BufferedImage redPoint, bluePoint, orangePoint, violetPoint;
    public static BufferedImage redDice, blueDice, orangeDice, violetDice;
    public static BufferedImage blueNumber, redNumber, orangeNumber, violetNumber;
    public static ArrayList<BufferedImage> blueRanks, redRanks, orangeRanks, violetRanks;
    public static BufferedImage dice,dice1, dice2, dice3, dice4, dice5, dice6;
    public static BufferedImage violetHo;

    public static void create() {
        redHorse = ImageLoader.loadImage("res/images/red_horse.png");
        blueHorse = ImageLoader.loadImage("res/images/blue_horse.png");
        orangeHorse = ImageLoader.loadImage("res/images/orange_horse.png");
        violetHorse = ImageLoader.loadImage("res/images/violet_horse.png");

        redBackground = ImageLoader.loadImage("res/images/red_background.png");
        blueBackground = ImageLoader.loadImage("res/images/blue_background.png");
        orangeBackground = ImageLoader.loadImage("res/images/orange_background.png");
        violetBackground = ImageLoader.loadImage("res/images/violet_background.png");

        redCircle = ImageLoader.loadImage("res/images/circle_red.png");
        blueCircle = ImageLoader.loadImage("res/images/circle_blue.png");
        orangeCircle = ImageLoader.loadImage("res/images/circle_orange.png");
        violetCircle = ImageLoader.loadImage("res/images/circle_violet.png");

        redPoint = ImageLoader.loadImage("res/images/red_first.png");
        bluePoint = ImageLoader.loadImage("res/images/blue_first.png");
        orangePoint = ImageLoader.loadImage("res/images/orange_first.png");
        violetPoint = ImageLoader.loadImage("res/images/violet_first.png");

        redDice = ImageLoader.loadImage("res/images/red_dice.png");
        blueDice = ImageLoader.loadImage("res/images/blue_dice.png");
        orangeDice = ImageLoader.loadImage("res/images/orange_dice.png");
        violetDice = ImageLoader.loadImage("res/images/violet_dice.png");

        orangeNumber = ImageLoader.loadImage("res/images/number_3.png");
        orangeRanks = new ArrayList<>();
        orangeRanks.add(ImageLoader.crop(orangeNumber, 0, 0, 20, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 20, 0, 25, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 45, 0, 30, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 75, 0, 30, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 105, 0, 30, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 135, 0, 30, 36));

        violetNumber = ImageLoader.loadImage("res/images/number_2.png");
        violetRanks = new ArrayList<>();
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 0, 36, 20));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 20, 36, 25));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 45, 36, 30));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 75, 36, 30));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 105, 36, 30));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 135, 36, 30));

        blueNumber = ImageLoader.loadImage("res/images/number.png");
        blueRanks = new ArrayList<>();
        blueRanks.add(ImageLoader.crop(blueNumber, 145, 0, 20, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 120, 0, 25, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 90, 0, 30, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 60, 0, 30, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 30, 0, 30, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 0, 0, 30, 36));

        redNumber = ImageLoader.loadImage("res/images/number_1.png");
        redRanks = new ArrayList<>();
        redRanks.add(ImageLoader.crop(redNumber, 0, 145, 36, 20));
        redRanks.add(ImageLoader.crop(redNumber, 0, 120, 36, 25));
        redRanks.add(ImageLoader.crop(redNumber, 0, 90, 36, 30));
        redRanks.add(ImageLoader.crop(redNumber, 0, 60, 36, 30));
        redRanks.add(ImageLoader.crop(redNumber, 0, 30, 36, 30));
        redRanks.add(ImageLoader.crop(redNumber, 0, 0, 36, 30));

        dice = ImageLoader.loadImage("res/images/dice.png");
        dice1 = ImageLoader.loadImage("res/images/dice_1.png");
        dice2 = ImageLoader.loadImage("res/images/dice_2.png");
        dice3 = ImageLoader.loadImage("res/images/dice_3.png");
        dice4 = ImageLoader.loadImage("res/images/dice_4.png");
        dice5 = ImageLoader.loadImage("res/images/dice_5.png");
        dice6 = ImageLoader.loadImage("res/images/dice_6.png");

        violetHo = ImageLoader.loadImage("res/images/violet_ho.png");
    }
}
