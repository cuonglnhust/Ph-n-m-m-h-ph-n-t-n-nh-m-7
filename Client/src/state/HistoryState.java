package state;

import SCCommon.Match;
import button.ButtonBack;
import button.ButtonMore;
import constant.StateTag;
import graphics.CreateImage;
import list.data.HistoryDataElement;
import list.graphics.HistoryGraphicsElement;
import main.Handler;

import java.awt.*;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HistoryState extends State {

    private StateTag stateTag;
    private List<HistoryDataElement> historyDataElementList;
    private List<HistoryGraphicsElement> historyGraphicsElementList;
    private ButtonBack buttonBack;
    private ButtonMore buttonMore;


    public HistoryState() {
        stateTag = StateTag.HISTORY_STATE;
        updateHistoryDataElement();
        updateHistoryGraphicsElementList();
        buttonBack = new ButtonBack(30, 660);
        buttonMore = new ButtonMore(800, 660);
    }

    private void updateHistoryDataElement() {
        historyDataElementList = new ArrayList<>();

        try {
            List<Match> matches = Handler.getInstance().getClientLogin().getiServer().getMatchHistory(
                                                                Handler.getInstance().getClientLogin().getPlayer().getPid());

            for(Match match : matches){
                HistoryDataElement historyDataElement = new HistoryDataElement();
                historyDataElement.setOpponents(match.getPlayers());
                historyDataElement.setWinner(match.getWinner());
                historyDataElement.setDuration(match.getDuration());

                historyDataElementList.add(historyDataElement);
            }


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        historyDataElementList.add(new HistoryDataElement("Khanh", true, "18:45"));
//        historyDataElementList.add(new HistoryDataElement("Khanh", true, "18:45"));
//        historyDataElementList.add(new HistoryDataElement("Khanh", true, "18:45"));
//        historyDataElementList.add(new HistoryDataElement("Khanh", true, "18:45"));
    }

    private void updateHistoryGraphicsElementList() {
        historyGraphicsElementList = new ArrayList<>();
        for (int i = 0; i < historyDataElementList.size(); i++) {
            HistoryGraphicsElement historyGraphicsElement =
                    new HistoryGraphicsElement(historyDataElementList.get(i), i);
            historyGraphicsElementList.add(historyGraphicsElement);
        }
    }

    @Override
    public void tick() {
        buttonBack.tick(stateTag);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(CreateImage.home_background, 0, 0, 930, 730, null);
        g.drawImage(CreateImage.mainLogo, 195, 50, null);
        g.drawImage(CreateImage.player, 82, 250, null);
        g.drawImage(CreateImage.result, 364, 250, null);
        g.drawImage(CreateImage.time, 646, 250, null);
        buttonBack.render(g);
        buttonMore.render(g);

        for (HistoryGraphicsElement historyGraphicsElement : historyGraphicsElementList) {
            historyGraphicsElement.render(g);
        }
    }
}

