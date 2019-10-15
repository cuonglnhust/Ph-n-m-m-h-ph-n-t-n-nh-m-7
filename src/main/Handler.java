package main;

import map.Map;
import mouse.Mouse;

public class Handler {

    private Game game;
    private Map map;
    private Mouse mouse;

    private static Handler handler;

    private Handler() {

    }

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

    public Mouse getMouse() {
        return game.getMouse();
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
