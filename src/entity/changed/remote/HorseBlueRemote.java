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

public class HorseBlueRemote extends HorseRemote {

    public HorseBlueRemote(int id, int x, int y, PlayerRemote playerRemote) {
        super(id, x, y, playerRemote);
        team = TeamType.TEAM_BLUE;
        entity = CreateImage.blueHorse;
    }

    @Override
    public void render(Graphics g) {
        if (position >= 13 && position <= 41) {
            entity = CreateImage.blueHorseFlip;
        }else if (rank != 0){
            entity = CreateImage.blueHorseRank;
        }
        else entity = CreateImage.blueHorse;
        super.render(g);
    }
}
