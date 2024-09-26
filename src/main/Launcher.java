package main;

import map.EntitySize;

public class Launcher {
    public static void main(String[] args) {
        Game game = new Game("Cờ cá ngựa", EntitySize.SCREEN_WIDTH, EntitySize.SCREEN_HEIGHT);
        game.start();
    }
}
