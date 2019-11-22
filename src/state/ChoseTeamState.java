package state;

import button.ButtonPlay;
import button.ButtonTeam;
import graphics.CreateImage;

import java.awt.*;

public class ChoseTeamState extends State {

    private ButtonTeam blueTeam, redTeam, orangeTeam, violetTeam;
    private ButtonPlay buttonPlay;

    public ChoseTeamState() {
        blueTeam = new ButtonTeam(150, 240, CreateImage.blueBackground);
        redTeam = new ButtonTeam(540, 240, CreateImage.redBackground);
        orangeTeam = new ButtonTeam(150, 480, CreateImage.orangeBackground);
        violetTeam = new ButtonTeam(540, 480, CreateImage.violetBackground);
        buttonPlay = new ButtonPlay(800, 650);
    }

    @Override
    public void tick() {
        buttonPlay.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(CreateImage.home_background, 0, 0, 930, 730, null);
        g.drawImage(CreateImage.mainLogo, 195, 50, null);

        blueTeam.render(g);
        redTeam.render(g);
        orangeTeam.render(g);
        violetTeam.render(g);
        buttonPlay.render(g);
    }
}
