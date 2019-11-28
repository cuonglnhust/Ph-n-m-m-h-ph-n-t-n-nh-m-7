package map.remote;

import player.client.PlayerLocal;
import player.server.PlayerRemote;

public interface MapInterface {
    MapBuilder createPlayer(PlayerLocal playerLocal);
    MapBuilder createPlayerRemote(PlayerRemote playerRemote);
}
