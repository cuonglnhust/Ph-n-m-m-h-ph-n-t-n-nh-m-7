package main;

import map.local.MapTemp;
import mouse.Mouse;
import rmi.client.ClientLogin;
import rmi.client.ClientPlayer;
import SCCommon.ConnectionData;
import rmi.model.ModeViewer;
import rmi.server.ServerPlayer;

public class Handler {

    private Game game;
    private MapTemp mapTemp;
    private ServerPlayer serverPlayer;
    private ClientPlayer clientPlayer;
    private ModeViewer modeViewer;
    private ClientLogin clientLogin;
    private ConnectionData connection;

    private int id;
    private String name;
    private int playerCount;


    private static Handler handler;

    private Handler() {
        id = 2;
        name = "Cuong";
        playerCount = 2;
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

    public MapTemp getMapTemp() {
        return mapTemp;
    }

    public void setMapTemp(MapTemp mapTemp) {
        this.mapTemp = mapTemp;
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

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public ConnectionData getConnection() {
        return connection;
    }

    public void setConnection(ConnectionData connection) {
        this.connection = connection;
    }
}
