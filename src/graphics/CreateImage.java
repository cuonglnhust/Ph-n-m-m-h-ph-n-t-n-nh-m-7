package graphics;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class CreateImage {

    public static BufferedImage redHorse, blueHorse, orangeHorse, violetHorse;
    public static BufferedImage redBackground, blueBackground, orangeBackground, violetBackground;
    public static BufferedImage redCircle, blueCircle, orangeCircle, violetCircle;
    public static BufferedImage redPoint, bluePoint, orangePoint, violetPoint;
    public static BufferedImage redDice, blueDice, orangeDice, violetDice;
    public static ArrayList<BufferedImage> blueRanks, redRanks, orangeRanks, violetRanks;
    public static BufferedImage dice, dice1, dice2, dice3, dice4, dice5, dice6;
    public static BufferedImage[] diceAnimation;
    public static BufferedImage blueHorseRank, redHorseRank, orangeHorseRank, violetHorseRank;
    public static BufferedImage blueHorseFlip, redHorseFlip, orangeHorseFlip, violetHorseFlip;

    public static BufferedImage home_background;
    public static BufferedImage mainLogo, playerList, matchList, vs;
    public static BufferedImage[] buttonHistory, buttonInvite, buttonWatch;

    public static BufferedImage player, result, time;
    public static BufferedImage[] buttonBack, buttonMore, buttonPlay, buttonStart;

    public static BufferedImage player1, player2;
    public static BufferedImage lose, win;


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

        BufferedImage orangeNumber = ImageLoader.loadImage("res/images/number_3.png");
        orangeRanks = new ArrayList<>();
        orangeRanks.add(ImageLoader.crop(orangeNumber, 0, 0, 20, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 20, 0, 25, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 45, 0, 30, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 75, 0, 30, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 105, 0, 30, 36));
        orangeRanks.add(ImageLoader.crop(orangeNumber, 135, 0, 30, 36));

        BufferedImage violetNumber = ImageLoader.loadImage("res/images/number_2.png");
        violetRanks = new ArrayList<>();
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 0, 36, 20));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 20, 36, 25));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 45, 36, 30));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 75, 36, 30));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 105, 36, 30));
        violetRanks.add(ImageLoader.crop(violetNumber, 0, 135, 36, 30));

        BufferedImage blueNumber = ImageLoader.loadImage("res/images/number.png");
        blueRanks = new ArrayList<>();
        blueRanks.add(ImageLoader.crop(blueNumber, 145, 0, 20, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 120, 0, 25, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 90, 0, 30, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 60, 0, 30, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 30, 0, 30, 36));
        blueRanks.add(ImageLoader.crop(blueNumber, 0, 0, 30, 36));

        BufferedImage redNumber = ImageLoader.loadImage("res/images/number_1.png");
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

        diceAnimation = new BufferedImage[7];
        diceAnimation[0] = dice;
        diceAnimation[1] = dice1;
        diceAnimation[2] = dice2;
        diceAnimation[3] = dice3;
        diceAnimation[4] = dice4;
        diceAnimation[5] = dice5;
        diceAnimation[6] = dice6;

        blueHorseRank = ImageLoader.loadImage("res/images/blue_horse_rank.png");
        redHorseRank = ImageLoader.loadImage("res/images/red_horse_rank.png");
        violetHorseRank = ImageLoader.loadImage("res/images/violet_horse_rank.png");
        orangeHorseRank = ImageLoader.loadImage("res/images/orange_horse.png");

        blueHorseFlip = ImageLoader.loadImage("res/images/flip_blue_horse.png");
        redHorseFlip = ImageLoader.loadImage("res/images/flip_red_horse.png");
        violetHorseFlip = ImageLoader.loadImage("res/images/flip_violet_horse.png");
        orangeHorseFlip = ImageLoader.loadImage("res/images/flip_orange_horse.png");

        home_background = ImageLoader.loadImage("res/images/background_home.jpg");
        mainLogo = ImageLoader.loadImage("res/images/main_logo.png");
        playerList = ImageLoader.loadImage("res/images/player_list.png");
        matchList = ImageLoader.loadImage("res/images/match_list.png");
        vs = ImageLoader.loadImage("res/images/vs.png");

        buttonHistory = new BufferedImage[2];
        buttonHistory[0] = ImageLoader.loadImage("res/images/history_1.png");
        buttonHistory[1] = ImageLoader.loadImage("res/images/history_2.png");

        buttonInvite = new BufferedImage[2];
        buttonInvite[0] = ImageLoader.loadImage("res/images/invite_1.png");
        buttonInvite[1] = ImageLoader.loadImage("res/images/invite_2.png");

        buttonWatch = new BufferedImage[2];
        buttonWatch[0] = ImageLoader.loadImage("res/images/watch_1.png");
        buttonWatch[1] = ImageLoader.loadImage("res/images/watch_2.png");

        player = ImageLoader.loadImage("res/images/player.png");
        result = ImageLoader.loadImage("res/images/result_1.png");
        time = ImageLoader.loadImage("res/images/time.png");

        buttonBack = new BufferedImage[2];
        buttonBack[0] = ImageLoader.loadImage("res/images/back_1.png");
        buttonBack[1] = ImageLoader.loadImage("res/images/back_2.png");

        buttonMore = new BufferedImage[2];
        buttonMore[0] = ImageLoader.loadImage("res/images/more_1.png");
        buttonMore[1] = ImageLoader.loadImage("res/images/more_2.png");

        player1 = ImageLoader.loadImage("res/images/player_1.png");
        player2 = ImageLoader.loadImage("res/images/player_2.png");

        buttonPlay = new BufferedImage[2];
        buttonPlay[0] = ImageLoader.loadImage("res/images/play_1.png");
        buttonPlay[1] = ImageLoader.loadImage("res/images/play_2.png");

        buttonStart = new BufferedImage[2];
        buttonStart[0] = ImageLoader.loadImage("res/images/start_1.png");
        buttonStart[1] = ImageLoader.loadImage("res/images/start_2.png");

        lose = ImageLoader.loadImage("res/images/lose.jpg");
        win = ImageLoader.loadImage("res/images/win.jpg");
    }
}
