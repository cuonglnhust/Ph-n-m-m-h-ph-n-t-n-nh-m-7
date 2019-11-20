package mouse;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouse implements MouseListener, MouseMotionListener {

    private int mouseX, mouseY;
    private boolean leftClick;
    private boolean rightClick;

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                leftClick = true;
                break;
            case MouseEvent.BUTTON3:
                rightClick = true;
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (e.getButton()) {
            case MouseEvent.BUTTON1:
                leftClick = false;
                break;
            case MouseEvent.BUTTON3:
                rightClick = false;
                break;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isLeftClick() {
        return leftClick;
    }

    public boolean isRightClick() {
        return rightClick;
    }

    public void setLeftClick(boolean leftClick) {
        this.leftClick = leftClick;
    }

    public void setRightClick(boolean rightClick) {
        this.rightClick = rightClick;
    }

    public void setDefaultClick() {
        leftClick = false;
        rightClick = false;
    }
}
