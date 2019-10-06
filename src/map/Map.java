package map;


import Team.Blue;
import Team.Orange;
import Team.Red;
import Team.Violet;
import graphics.CreateImage;

import java.awt.*;

public class Map {

    private Blue blueTeam;
    private Red redTeam;
    private Orange orangeTeam;
    private Violet violetTeam;

    public Map() {
        blueTeam = new Blue();
        redTeam = new Red();
        orangeTeam = new Orange();
        violetTeam = new Violet();
    }

    public void tick() {

    }

    public void render(Graphics g) {
        blueTeam.render(g);
        redTeam.render(g);
        orangeTeam.render(g);
        violetTeam.render(g);
        g.setColor(Color.red);
        g.drawRect(115, 15, 700, 700);
        g.drawImage(CreateImage.dice, 35, 40, 50, 50, null);
        g.drawImage(CreateImage.violetHo, EntityPosition.RED_FIRST_X, EntityPosition.RED_FIRST_Y - 28, null);
    }

}
