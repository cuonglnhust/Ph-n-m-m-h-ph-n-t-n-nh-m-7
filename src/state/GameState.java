package state;

import button.PlayerData;
import main.Handler;
import map.local.Map;
import map.local.MapCopy;
import map.local.MapTemp;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.List;

public class GameState extends State {

    private MapTemp mapTemp;
    private MapCopy mapCopy;

    // Trạng thái chơi game
    public GameState(List<PlayerData> playerDataList) {
        Handler.getInstance().getMouse().setDefaultClick();
        mapTemp = new MapTemp(playerDataList);
        Handler.getInstance().setMapTemp(mapTemp);
        // nếu là Server
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().setMapTemp(mapTemp);
        }
    }

    // Trạng thái xem game
    public GameState() {
        // nếu là ClientViewer
        if (Handler.getInstance().getClientViewer() != null) {
            // Lấy thông tin từ ServerViewer
            try {
                List<PlayerData> playerDataList = Handler.getInstance().getClientViewer().getWatchMatchServer().getPlayerDataList();
                // khởi tạo Map từ thông tin lấy được
                mapCopy = new MapCopy(playerDataList);
                Handler.getInstance().setMapCopy(mapCopy);
                Handler.getInstance().getClientViewer().getWatchMatchClientImp().setMapCopy(Handler.getInstance().getMapCopy());
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void tick() {
        if (mapTemp != null) {
            mapTemp.tick();
        }
        if (mapCopy != null) {
            mapCopy.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        if (mapTemp != null) {
            mapTemp.render(g);
        }
        if (mapCopy != null) {
            mapCopy.render(g);
        }
    }


}
