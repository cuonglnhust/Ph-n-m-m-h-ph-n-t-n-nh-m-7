package state;

import SCCommon.Match;
import SCCommon.Player;
import button.ButtonHistory;
import graphics.CreateImage;
import list.data.MatchDataElement;
import list.data.PlayerDataElement;
import list.graphics.MatchGraphicsElement;
import list.graphics.PlayerGraphicsElement;
import main.Handler;

import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeState extends State {

    private List<MatchDataElement> matchDataElementList;
    private List<MatchGraphicsElement> matchGraphicsElementList;
    private ButtonHistory buttonHistory;

    private List<Player> playerList;
    private List<PlayerGraphicsElement> playerGraphicsElementList;

    private boolean updateData;


    public HomeState() {
        Handler.getInstance().getClientLogin().getIClientImp().setHomeState(this);
        playerList = new ArrayList<>();
        playerGraphicsElementList = new ArrayList<>();
        matchDataElementList = new ArrayList<>();
        matchGraphicsElementList = new ArrayList<>();
        Handler.getInstance().getGame().getDisplay().getjFrame().setTitle(Handler.getInstance().getName());
        updatePlayerGraphicsElementList();
        updateMatchGraphicsElementList();
        buttonHistory = new ButtonHistory(50, 621);
    }

    private void updateMatchGraphicsElementList() {
        try {
            List<Match> matches = Handler.getInstance().getClientLogin().getiServer().getMatchsOnline();

            for (Match match : matches) {
                MatchDataElement matchDataElement = new MatchDataElement(match.getPlayers().get(0).getPname(),
                        match.getPlayers().get(1).getPname(), match.getId());
                matchDataElementList.add(matchDataElement);
            }

            for (int i = 0; i < matchDataElementList.size(); i++) {
                MatchGraphicsElement matchGraphicsElement = new MatchGraphicsElement(matchDataElementList.get(i), i);
                matchGraphicsElementList.add(matchGraphicsElement);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private void updatePlayerGraphicsElementList() {
        try {
            playerList = Handler.getInstance().getClientLogin().getiServer().getPlayersOnline(
                    new Player(Handler.getInstance().getId(), Handler.getInstance().getName()));
            System.out.println("số người đang online là : " + playerList.size());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < playerList.size(); i++) {
            PlayerGraphicsElement playerGraphicsElement = new PlayerGraphicsElement(i, playerList.get(i));
            playerGraphicsElementList.add(playerGraphicsElement);
        }
    }

    @Override
    public void tick() {

        // nếu nhận được lời mời từ Server
        // Hiển thị thông báo về lời mời: Tên người chơi + lời mời
        // Trả lời thông báo

        if (updateData) {
            playerGraphicsElementList = new ArrayList<>();
            updatePlayerGraphicsElementList();
            matchDataElementList = new ArrayList<>();
            matchGraphicsElementList = new ArrayList<>();
            updateMatchGraphicsElementList();
            updateData = false;
        }

        for (PlayerGraphicsElement playerGraphicsElement : playerGraphicsElementList) {
            playerGraphicsElement.tick();
        }

        for (MatchGraphicsElement matchGraphicsElement : matchGraphicsElementList) {
            matchGraphicsElement.tick();
        }

        buttonHistory.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(CreateImage.home_background, 0, 0, 930, 730, null);
        g.drawImage(CreateImage.mainLogo, 195, 50, null);
        g.drawImage(CreateImage.playerList, 50, 210, null);
        g.drawImage(CreateImage.matchList, 517, 210, null);
        buttonHistory.render(g);

        for (MatchGraphicsElement matchGraphicsElement : matchGraphicsElementList) {
            matchGraphicsElement.render(g);
        }

        for (PlayerGraphicsElement playerGraphicsElement : playerGraphicsElementList) {
            playerGraphicsElement.render(g);
        }
    }

    private Font scaleFontWidth(Graphics g, String name) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Font font = Font.decode("Michroma");
        Rectangle2D rectangle2D = graphics2D.getFontMetrics(font).getStringBounds(name, g);
        float newSize = (float) (font.getSize2D() * 100 / rectangle2D.getWidth());
        Font font1 = font.deriveFont(4 * newSize / 5);
        graphics2D.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Map<TextAttribute, Object> atts = new HashMap<TextAttribute, Object>();
        atts.put(TextAttribute.KERNING, TextAttribute.KERNING_ON);
        Font font2 = font1.deriveFont(atts);
        return font2;
    }

    private int scaleFontHeight(Graphics g, String name, int height, int bottomLine) {
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS,
                RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        Font font = Font.decode("Michroma");
        Rectangle2D rectangle2D = graphics2D.getFontMetrics(font).getStringBounds(name, g);
        float baseLine = bottomLine - ((float) (height - rectangle2D.getHeight())) / 2;
        return (int) baseLine;
    }

    public void setUpdateData(boolean updateData) {
        this.updateData = updateData;
    }
}
