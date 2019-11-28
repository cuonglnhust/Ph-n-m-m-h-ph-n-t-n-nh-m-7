package map.remote;

import map.local.Map;
import player.client.PlayerLocal;
import player.server.PlayerRemote;

public class MapBuilder implements MapInterface {
    private PlayerLocal playerLocal;
    private PlayerRemote playerRemote1, playerRemote2, playerRemote3;


    @Override
    public MapBuilder createPlayer(PlayerLocal playerLocal) {
        this.playerLocal = playerLocal;
        return this;
    }

    @Override
    public MapBuilder createPlayerRemote(PlayerRemote playerRemote) {
        if (playerRemote1 == null) {
            this.playerRemote1 = playerRemote;
        }else if (playerRemote2 == null){
            this.playerRemote2 = playerRemote;
        }else {
            this.playerRemote3 = playerRemote;
        }
        return this;
    }

    public MapRemote build(){
        return new MapRemote();
    }
}
