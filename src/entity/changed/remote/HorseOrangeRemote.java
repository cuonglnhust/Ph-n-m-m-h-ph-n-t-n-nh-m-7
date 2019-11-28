package entity.changed.remote;

import constant.TeamType;
import entity.changed.local.Horse;
import entity.unchanged.Rank;
import graphics.Constant;
import graphics.CreateImage;
import main.Handler;
import player.client.Player;
import player.server.PlayerRemote;

import java.awt.*;

public class HorseOrangeRemote extends HorseRemote {

    public HorseOrangeRemote(int id, int x, int y, PlayerRemote playerRemote) {
        super(id, x, y, playerRemote);
        team = TeamType.TEAM_ORANGE;
        entity = CreateImage.orangeHorse;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.orangeHorseFlip;
        }else if (rank != 0){
            entity = CreateImage.orangeHorseRank;
        }
        else entity = CreateImage.orangeHorse;
        super.render(g);
    }
}
