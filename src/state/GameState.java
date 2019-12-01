package state;

import button.PlayerData;
import main.Handler;
import map.local.Map;
import map.local.MapTemp;

import java.awt.*;
import java.util.List;

public class GameState extends State {

    private MapTemp mapTemp;

    public GameState(List<PlayerData> playerDataList) {
        Handler.getInstance().getMouse().setDefaultClick();
        mapTemp = new MapTemp(playerDataList);
        Handler.getInstance().setMapTemp(mapTemp);

    }


    @Override
    public void tick() {
        mapTemp.tick();
    }

    @Override
    public void render(Graphics g) {
        mapTemp.render(g);
    }


}
