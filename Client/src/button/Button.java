package button;

import main.Handler;

import java.awt.*;

public abstract class Button {
    protected int x;
    protected int y;
    protected boolean clicked;

    public Button(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void render(Graphics g);

    public abstract Rectangle getBound();

    public boolean isOver() {
        return getBound().contains(Handler.getInstance().getMouse().getMouseX(), Handler.getInstance().getMouse().getMouseY());
    }
}
