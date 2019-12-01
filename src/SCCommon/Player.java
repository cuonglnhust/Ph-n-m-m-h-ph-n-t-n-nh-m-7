package SCCommon;

import SCCommon.Match;

import java.io.Serializable;
import java.util.List;

public class Player implements Serializable{

    private static final long serialVersionUID = 1L;

    private int pid;
    private String pname;
    private String username;
    private String password;

    public Player() {
    }

    public Player(int pid, String pname) {
        this.pid = pid;
        this.pname = pname;
    }

    private List<Match> matches;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
