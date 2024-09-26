package list.graphics;

import SCCommon.Player;
import graphics.CreateFont;
import list.caculate.HistoryCalculateElement;
import list.constant.HistoryElementConstant;
import list.data.HistoryDataElement;
import main.Handler;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Iterator;

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

        String opponent = "";
//        Iterator<Player> iterator = historyDataElement.getOpponents().iterator();
//        while (iterator.hasNext()){
//            opponent = iterator.next().getPname() + ",";
//        }
        for (int i=0; i< historyDataElement.getOpponents().size();i++){
            opponent = opponent + historyDataElement.getOpponents().get(i).getPname().toUpperCase();
            if (i == historyDataElement.getOpponents().size()-1){
                opponent = opponent + ".";
            }else {
                opponent = opponent + ",";
            }
        }
        player = new TextGraphicsElement(HistoryElementConstant.PLAYER_X, HistoryElementConstant.TEXT_WIDTH,
                HistoryElementConstant.TEXT_HEIGHT,opponent,
                historyCalculateElement.getBottomLine(), CreateFont.historyFont);

        String res;
        if (historyDataElement.getWinner() == Handler.getInstance().getId()) {
            res = "Win";
        } else res = "Lose";
        result = new TextGraphicsElement(HistoryElementConstant.RESULT_X, HistoryElementConstant.TEXT_WIDTH,
                HistoryElementConstant.TEXT_HEIGHT, res,
                historyCalculateElement.getBottomLine(), CreateFont.historyFont);
        times = new TextGraphicsElement(HistoryElementConstant.TIME_X, HistoryElementConstant.TEXT_WIDTH,
                HistoryElementConstant.TEXT_HEIGHT, historyDataElement.getDuration(),
                historyCalculateElement.getBottomLine(), CreateFont.historyFont);
    }

    public void render(Graphics g) {
        player.render(g);
        result.render(g);
        times.render(g);

    }

}
