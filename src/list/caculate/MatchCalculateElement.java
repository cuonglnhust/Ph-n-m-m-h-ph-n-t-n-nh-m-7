package list.caculate;

import list.constant.MatchElementConstant;

import java.awt.*;

public class MatchCalculateElement {

    private int id;

    public MatchCalculateElement(int id) {
        this.id = id;
    }

    public Point getButtonWatch() {
        int x = MatchElementConstant.FIRST_ELEMENT_X + MatchElementConstant.VS_WIDTH +
                MatchElementConstant.SPACE * 3 + MatchElementConstant.TEXT_WIDTH * 2;
        int y = MatchElementConstant.FIRST_ELEMENT_Y + MatchElementConstant.ELEMENT_HEIGHT * id;
        return new Point(x, y);
    }

    public int getSecondTextX() {
        return MatchElementConstant.FIRST_ELEMENT_X + MatchElementConstant.TEXT_WIDTH +
                MatchElementConstant.SPACE * 2 + MatchElementConstant.VS_WIDTH;
    }

    public Point getVs() {
        int x = MatchElementConstant.FIRST_ELEMENT_X + MatchElementConstant.TEXT_WIDTH +
                MatchElementConstant.SPACE;
        int y = MatchElementConstant.FIRST_ELEMENT_Y + MatchElementConstant.ELEMENT_HEIGHT * id + 5;
        return new Point(x, y);
    }

    public int getBottomLine() {
        return MatchElementConstant.FIRST_ELEMENT_Y + MatchElementConstant.ELEMENT_HEIGHT * id
                + MatchElementConstant.ELEMENT_HEIGHT;
    }
}
