package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    protected int x;
    protected int y;
    protected int width, height;
    protected BufferedImage entity;

    public Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void tick() {}

    public abstract void render(Graphics g);

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
