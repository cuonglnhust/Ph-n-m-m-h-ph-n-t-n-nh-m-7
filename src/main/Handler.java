package main;

import map.local.Map;
import mouse.Mouse;
import rmi.client.ClientLogin;
import rmi.client.ClientPlayer;
import rmi.model.ModePlayer;
import rmi.model.ModeViewer;
import rmi.server.ServerPlayer;

public class Handler {

    private Game game;
    private Map map;
    private ServerPlayer serverPlayer;
    private ClientPlayer clientPlayer;
    private ModeViewer modeViewer;
    private ClientLogin clientLogin;
    private int id;
    private String name;


    private static Handler handler;

    private Handler() {
        id = 2;
        name = "Cuong";
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

    public void setMap(Map map) {
        this.map = map;
    }

    public Mouse getMouse() {
        return game.getMouse();
    }

    void setGame(Game game) {
        this.game = game;
    }

    public ServerPlayer getServerPlayer() {
        return serverPlayer;
    }

    public ModeViewer getModeViewer() {
        return modeViewer;
    }

    public ClientLogin getClientLogin() {
        return clientLogin;
    }

    public void setServerPlayer(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
    }

    public void setModeViewer(ModeViewer modeViewer) {
        this.modeViewer = modeViewer;
    }

    public void setClientLogin(ClientLogin clientLogin) {
        this.clientLogin = clientLogin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientPlayer getClientPlayer() {
        return clientPlayer;
    }

    public void setClientPlayer(ClientPlayer clientPlayer) {
        this.clientPlayer = clientPlayer;
    }
}
