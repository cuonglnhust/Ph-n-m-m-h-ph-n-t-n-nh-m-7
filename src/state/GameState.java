package state;

import graphics.CreateImage;
import main.Handler;
import map.EntityPosition;
import map.Map;

import java.awt.*;

public class GameState extends State {


    private Map map;

    public GameState() {
        map = new Map();
        Handler.getInstance().setMap(map);
    }

    @Override
    public void tick() {
        map.tick();
    }

    @Override
    public void render(Graphics g) {
        map.render(g);
    }


}
