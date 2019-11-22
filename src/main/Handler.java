package main;

import map.Map;
import mouse.Mouse;
import rmi.model.Mode;
import rmi.client.ClientLogin;
import rmi.model.ModePlayer;
import rmi.model.ModeViewer;

public class Handler {

    private Game game;
    private Map map;
    private ModePlayer modePlayer;
    private ModeViewer modeViewer;
    private ClientLogin clientLogin;


    private static Handler handler;

    private Handler() {}

    public static Handler getInstance() {
        synchronized (Handler.class) {
            if (handler == null) {
                handler = new Handler();
            }
        }
        return handler;
    }

    public Game getGame() {
        return game;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Mouse getMouse() {
        return game.getMouse();
    }

    void setGame(Game game) {
        this.game = game;
    }

    public ModePlayer getModePlayer() {
        return modePlayer;
    }

    public ModeViewer getModeViewer() {
        return modeViewer;
    }

    public ClientLogin getClientLogin() {
        return clientLogin;
    }

    public void setModePlayer(ModePlayer modePlayer) {
        this.modePlayer = modePlayer;
    }

    public void setModeViewer(ModeViewer modeViewer) {
        this.modeViewer = modeViewer;
    }

    public void setClientLogin(ClientLogin clientLogin) {
        this.clientLogin = clientLogin;
    }
}
