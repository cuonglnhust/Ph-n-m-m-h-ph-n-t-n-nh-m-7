package entity.changed.remote;

import constant.TeamType;
import entity.changed.local.Horse;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import map.EntitySize;
import player.client.Player;
import player.server.PlayerRemote;

import java.awt.*;

public class HorseVioletRemote extends HorseRemote {

    public HorseVioletRemote(int id, int x, int y, PlayerRemote playerRemote) {
        super(id, x, y, playerRemote);
        team = TeamType.TEAM_VIOLET;
        entity = CreateImage.violetHorse;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.violetHorseFlip;
        } else if (rank != 0) {
            entity = CreateImage.violetHorseRank;
        } else {
            entity = CreateImage.violetHorse;
        }
        super.render(g);
    }
}
