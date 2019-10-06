package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Entity {

    int x;
    int y;
    int width,height;
    protected BufferedImage entity;

    Entity(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
}
