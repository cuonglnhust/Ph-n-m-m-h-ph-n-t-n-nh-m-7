package state;

import constant.ModeType;
import main.Handler;
import map.Map;
import rmi.client.ClientPlayer;
import rmi.server.ServerPlayer;

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
