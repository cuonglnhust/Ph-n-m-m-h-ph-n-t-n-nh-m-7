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

public class HorseRedRemote extends HorseRemote {

    public HorseRedRemote(int id, int x, int y, PlayerRemote playerRemote) {
        super(id, x, y, playerRemote);
        team = TeamType.TEAM_RED;
        entity = CreateImage.redHorse;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.redHorseFlip;
        } else if (rank != 0) {
            entity = CreateImage.redHorseRank;
        } else entity = CreateImage.redHorse;
        super.render(g);
    }
}
