package state;

import SCCommon.Player;
import button.ButtonHistory;
import graphics.CreateFont;
import graphics.CreateImage;
import list.data.MatchDataElement;
import list.data.PlayerDataElement;
import list.graphics.MatchGraphicsElement;
import list.graphics.PlayerGraphicsElement;
import main.Handler;

import javax.swing.*;
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

    private List<PlayerDataElement> playerDataElementList;
    private List<PlayerGraphicsElement> playerGraphicsElementList;


    public HomeState() {
 //      updateMatchGraphicsElementList();
       updatePlayerGraphicsElementList();
        buttonHistory = new ButtonHistory(50, 621);
    }

    private void updateMatchGraphicsElementList() {
        matchGraphicsElementList = new ArrayList<>();
        for (int i = 0; i < matchDataElementList.size(); i++) {
            MatchGraphicsElement matchGraphicsElement = new MatchGraphicsElement(matchDataElementList.get(i), i);
            matchGraphicsElementList.add(matchGraphicsElement);
        }
    }

    private void updatePlayerGraphicsElementList() {

        playerGraphicsElementList = new ArrayList<>();
        playerDataElementList = new ArrayList<>();
        try {
            List<Player> players = Handler.getInstance().getClientLogin().getiServer().getPlayersOnline(
                                        new Player(Handler.getInstance().getId(),Handler.getInstance().getName()));
            System.out.println("số người đang online là : "+players.size());
            for (Player player : players){

                PlayerDataElement playerDataElement = new PlayerDataElement();
                playerDataElement.setIdPlayer(player.getPid());
                playerDataElement.setPlayerName(player.getPname());

                playerDataElementList.add(playerDataElement);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < playerDataElementList.size(); i++) {
            PlayerGraphicsElement playerGraphicsElement = new PlayerGraphicsElement(i, playerDataElementList.get(i));
            playerGraphicsElementList.add(playerGraphicsElement);
        }
    }

    @Override
    public void tick() {

        // nếu nhận được lời mời từ Server

        // Hiển thị thông báo về lời mời: Tên người chơi + lời mời

        // Trả lời thông báo

        // mời người chơi khác
//        for (PlayerGraphicsElement playerGraphicsElement : playerGraphicsElementList) {
//            playerGraphicsElement.tick();
//        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(CreateImage.home_background, 0, 0, 930, 730, null);
        g.drawImage(CreateImage.mainLogo, 195, 50, null);
        g.drawImage(CreateImage.playerList, 50, 210, null);
        g.drawImage(CreateImage.matchList, 517, 210, null);
        buttonHistory.render(g);


//        for (MatchGraphicsElement matchGraphicsElement : matchGraphicsElementList) {
//            matchGraphicsElement.render(g);
//        }
//
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
}
