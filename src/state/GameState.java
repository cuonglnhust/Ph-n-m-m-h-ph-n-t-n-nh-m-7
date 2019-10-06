package state;

import graphics.CreateImage;
import map.EntityPosition;
import map.Map;

import java.awt.*;

public class GameState extends State {

    private Map map = new Map();

    public GameState() {

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
