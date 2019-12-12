package main;

import constant.Connection;
import map.local.MapCopy;
import map.local.MapTemp;
import mouse.Mouse;
import rmi.client.ClientLogin;
import rmi.client.ClientPlayer;
import SCCommon.ConnectionData;
import rmi.client.ClientViewer;
import rmi.server.ServerPlayer;
import rmi.server.ServerViewer;

public class Handler {

    private Game game;
    private MapTemp mapTemp;
    private MapCopy mapCopy;
    private ServerPlayer serverPlayer;
    private ClientPlayer clientPlayer;
    private ClientViewer clientViewer;
    private ServerViewer serverViewer;
    private ClientLogin clientLogin;
    private ConnectionData connectionToPlay;
    private ConnectionData connectionToWatch;
    private int id;
    private String name;
    private int playerCount;


    private static Handler handler;

    private Handler() {
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

    public ClientLogin getClientLogin() {
        return clientLogin;
    }

    public void setServerPlayer(ServerPlayer serverPlayer) {
        this.serverPlayer = serverPlayer;
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

    public ConnectionData getConnectionToPlay() {
        return connectionToPlay;
    }

    public void setConnectionToPlay(ConnectionData connectionToPlay) {
        this.connectionToPlay = connectionToPlay;
    }

    public ClientViewer getClientViewer() {
        return clientViewer;
    }

    public void setClientViewer(ClientViewer clientViewer) {
        this.clientViewer = clientViewer;
    }

    public ServerViewer getServerViewer() {
        return serverViewer;
    }

    public void setServerViewer(ServerViewer serverViewer) {
        this.serverViewer = serverViewer;
    }

    public ConnectionData getConnectionToWatch() {
        return connectionToWatch;
    }

    public void setConnectionToWatch(ConnectionData connectionToWatch) {
        this.connectionToWatch = connectionToWatch;
    }

    public MapCopy getMapCopy() {
        return mapCopy;
    }

    public void setMapCopy(MapCopy mapCopy) {
        this.mapCopy = mapCopy;
    }
}
