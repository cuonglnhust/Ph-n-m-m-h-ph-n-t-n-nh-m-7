package list.graphics;

import graphics.CreateFont;
import list.caculate.HistoryCalculateElement;
import list.constant.HistoryElementConstant;
import list.data.HistoryDataElement;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HistoryGraphicsElement {
    private int id;
    private TextGraphicsElement player;
    private TextGraphicsElement result;
    private TextGraphicsElement times;
    private HistoryDataElement historyDataElement;
    private HistoryCalculateElement historyCalculateElement;

    public HistoryGraphicsElement(HistoryDataElement historyDataElement, int id) {
        this.historyDataElement = historyDataElement;
        historyCalculateElement = new HistoryCalculateElement(id);
        init();
    }

    private void init() {
        player = new TextGraphicsElement(HistoryElementConstant.PLAYER_X, HistoryElementConstant.TEXT_WIDTH,
                HistoryElementConstant.TEXT_HEIGHT, historyDataElement.getPlayer(),
                historyCalculateElement.getBottomLine(), CreateFont.historyFont);
        String res;
        if (historyDataElement.isResult()) {
            res = "Win";
        } else res = "Lose";
        result = new TextGraphicsElement(HistoryElementConstant.RESULT_X, HistoryElementConstant.TEXT_WIDTH,
                HistoryElementConstant.TEXT_HEIGHT, res,
                historyCalculateElement.getBottomLine(), CreateFont.historyFont);
        times = new TextGraphicsElement(HistoryElementConstant.TIME_X, HistoryElementConstant.TEXT_WIDTH,
                HistoryElementConstant.TEXT_HEIGHT, historyDataElement.getTimes(),
                historyCalculateElement.getBottomLine(), CreateFont.historyFont);
    }

    public void render(Graphics g) {
        player.render(g);
        result.render(g);
        times.render(g);

    }

}
