package main;

import constant.Connection;
import graphics.CreateFont;
import mouse.Mouse;
import state.*;
import graphics.CreateImage;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    private Display display;
    private String title;
    private int width, height;
    private boolean running = false;
    private Thread thread;
    private BufferStrategy bs;
    private Graphics g;
    private State state;
    private Mouse mouse;


    Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        mouse = new Mouse();
    }

    private void init() {
        Handler.getInstance().setGame(this);
        display = new Display(title, width, height);
        display.getjFrame().addMouseListener(mouse);
        display.getjFrame().addMouseMotionListener(mouse);
        display.getCanvas().addMouseListener(mouse);
        display.getCanvas().addMouseMotionListener(mouse);
        CreateImage.create();
        CreateFont.create();
        Handler.getInstance().setConnectionToPlay(Connection.createToPlayer());
        Handler.getInstance().setConnectionToWatch(Connection.createToWatch());
        state = new StartState();
        State.setCurrentState(state);
    }

    private void tick() {
        if (State.getCurrentState() != null) {
            State.getCurrentState().tick();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        // Draw here
        if (State.getCurrentState() != null) {
            State.getCurrentState().render(g);
        }
        // End draw here
        bs.show();
        g.dispose();
    }

    @Override
    public void run() {
        init();
        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        while (running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    private synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Mouse getMouse() {
        return mouse;
    }

    public Display getDisplay() {
        return display;
    }

    public void setState(State state) {
        this.state = state;
    }


}
