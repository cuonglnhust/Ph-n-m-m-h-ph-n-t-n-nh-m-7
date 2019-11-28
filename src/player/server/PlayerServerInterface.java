package player.server;

import constant.TeamType;

public interface PlayerServerInterface {

    public void setDiceValueDefault();

    public void updateDiceClick(TeamType teamType);

    public void updateDiceValue(TeamType teamType);

    public void updateChangeTurn(TeamType teamType);

    public void updateHorse();

    public void updateVirtualMap();


}
