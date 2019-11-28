package state;

import button.PlayerData;
import main.Handler;
import map.local.Map;

import java.awt.*;
import java.util.List;

public class GameState extends State {

    private Map map;
    private List<PlayerData> playerDataList;

    public GameState(List<PlayerData> playerDataList) {
        map = new Map();
        Handler.getInstance().setMap(map);
        this.playerDataList = playerDataList;
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
