package button;

import constant.TeamType;

import java.io.Serializable;

// thông tin để tạo Player trong GameState
public class PlayerData implements Serializable {
    private int id;
    private String name;
    private TeamType teamType;
    // flag chỉ ra rằng nó là Player Local
    private boolean flag;

    public PlayerData(int id, String name, TeamType teamType, boolean flag) {
        this.id = id;
        this.name = name;
        this.teamType = teamType;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TeamType getTeamType() {
        return teamType;
    }

    public boolean isFlag() {
        return flag;
    }
}
