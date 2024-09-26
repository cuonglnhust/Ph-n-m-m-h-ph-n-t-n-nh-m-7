package list.graphics;

import button.ButtonWatch;
import graphics.CreateFont;
import graphics.CreateImage;
import list.constant.MatchElementConstant;
import list.caculate.MatchCalculateElement;
import list.data.MatchDataElement;

import java.awt.*;

public class MatchGraphicsElement {

    private MatchDataElement matchDataElement;
    private int id;
    private TextGraphicsElement firstPlayer;
    private TextGraphicsElement secondPlayer;
    private ButtonWatch buttonWatch;
    private MatchCalculateElement matchCalculateElement;

    public MatchGraphicsElement(MatchDataElement matchDataElement, int id) {
        this.matchDataElement = matchDataElement;
        this.id = id;
        this.matchCalculateElement = new MatchCalculateElement(id);
        init();
    }

    private void init() {
        firstPlayer = new TextGraphicsElement(MatchElementConstant.FIRST_ELEMENT_X, MatchElementConstant.TEXT_WIDTH
                , MatchElementConstant.TEXT_HEIGHT, matchDataElement.getFirstPlayer(),
                matchCalculateElement.getBottomLine(), CreateFont.homeFont);
        secondPlayer = new TextGraphicsElement(matchCalculateElement.getSecondTextX(), MatchElementConstant.TEXT_WIDTH
                , MatchElementConstant.TEXT_HEIGHT, matchDataElement.getSecondPlayer(),
                matchCalculateElement.getBottomLine(), CreateFont.homeFont);
        Point buttonWatchCoordinate = matchCalculateElement.getButtonWatch();
        buttonWatch = new ButtonWatch(buttonWatchCoordinate.x, buttonWatchCoordinate.y,matchDataElement.getServerId());
    }

    public void tick() {
        buttonWatch.tick();
    }

    public void render(Graphics g) {
        firstPlayer.render(g);
        secondPlayer.render(g);
        Point vs = matchCalculateElement.getVs();
        g.drawImage(CreateImage.vs, vs.x, vs.y, null);
        buttonWatch.render(g);
    }


}
