package rmi.implementation;

import constant.TeamType;
import main.Handler;
import state.ChoseTeamState;
import rmi.interfaces.ChoseTeamServer;
import rmi.interfaces.ChoseTeamClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChoseTeamServerImp extends UnicastRemoteObject implements ChoseTeamServer {

    private ChoseTeamState choseTeamState;
    private HashMap<Integer, ChoseTeamClient> clientHashMap;
    private ArrayList<ChoseTeamClient> clients;
    private List<Integer> playerReady;

    private boolean serverReady;

    public ChoseTeamServerImp() throws RemoteException {
        clientHashMap = new HashMap<>();
        playerReady = new ArrayList<>();
    }

    @Override
    public boolean hello() throws RemoteException {
        System.out.println("Chose Team");
        return true;
    }

    @Override
    public void cancelChoseTeam(int id, String name, TeamType teamType) throws RemoteException {
        switch (teamType) {
            case TEAM_BLUE:
                choseTeamState.getBlueTeam().setCanChose(true);
                choseTeamState.getBlueTeam().setNameDefault();
                choseTeamState.getBlueTeam().setIdDefault();
                break;
            case TEAM_RED:
                choseTeamState.getRedTeam().setCanChose(true);
                choseTeamState.getRedTeam().setNameDefault();
                choseTeamState.getRedTeam().setIdDefault();
                break;
            case TEAM_VIOLET:
                choseTeamState.getVioletTeam().setCanChose(true);
                choseTeamState.getVioletTeam().setNameDefault();
                choseTeamState.getVioletTeam().setIdDefault();
                break;
            case TEAM_ORANGE:
                choseTeamState.getOrangeTeam().setCanChose(true);
                choseTeamState.getOrangeTeam().setNameDefault();
                choseTeamState.getOrangeTeam().setIdDefault();
                break;
        }
    }

    @Override
    public void showChoseTeam(String name, TeamType teamType, int id) throws RemoteException {
        System.out.println(" Name = " + name + "team = " + teamType);
        switch (teamType) {
            case TEAM_BLUE:
                choseTeamState.getBlueTeam().setCanChose(false);
                choseTeamState.getBlueTeam().setName(name);
                choseTeamState.getBlueTeam().setId(id);
                break;
            case TEAM_RED:
                choseTeamState.getRedTeam().setCanChose(false);
                choseTeamState.getRedTeam().setName(name);
                choseTeamState.getRedTeam().setId(id);
                break;
            case TEAM_VIOLET:
                choseTeamState.getVioletTeam().setCanChose(false);
                choseTeamState.getVioletTeam().setName(name);
                choseTeamState.getVioletTeam().setId(id);
                break;
            case TEAM_ORANGE:
                choseTeamState.getOrangeTeam().setCanChose(false);
                choseTeamState.getOrangeTeam().setName(name);
                choseTeamState.getOrangeTeam().setId(id);
                break;
        }
        ArrayList<ChoseTeamClient> clients = new ArrayList<>(clientHashMap.values());
        clients.remove(clientHashMap.get(id));
        for (ChoseTeamClient client : clients) {
            client.updateChoseTeam(id, name, teamType);
        }
    }

    public void broadcastChose(int id, String name, TeamType teamType) throws RemoteException {
        clients = new ArrayList<>(clientHashMap.values());
        for (ChoseTeamClient client : clients) {
            client.updateChoseTeam(id, name, teamType);
        }
    }

    public void broadcastCancel(int id, String name, TeamType teamType) throws RemoteException {
        clients = new ArrayList<>(clientHashMap.values());
        for (ChoseTeamClient client : clients) {
            client.cancelChoseTeam(id, name, teamType);
        }
    }

    // kiểm tra client
    @Override
    public boolean messageReady(int id) throws RemoteException {
        boolean check = false;
        for (Integer integer : playerReady) {
            if (integer == id) {
                check = true;
                break;
            }
        }
        // nếu chưa có trong mảng thì add player v
        if (!check) {
            playerReady.add(id);
            System.out.println("Id = " + id);
        }
        // true nếu tất cả client đã sẵn sàng, false nếu chưa
        return playerReady.size() == clientHashMap.size();
    }

    // kiểm tra server đã sẵn sàng hay chưa
    @Override
    public boolean serverReady() throws RemoteException {
        return serverReady;
    }

    // thông báo cho server tất cả client đã sẵn sàng
    @Override
    public void allReady(int id) throws RemoteException {
        // chuyển trạng thái
        choseTeamState.allReady();
        // chuyển Client gửi đến sang GameState
        clientHashMap.get(id).changeState();
        // unlock cho các client khác
        clients = new ArrayList<>(clientHashMap.values());
        clients.remove(clientHashMap.get(id));
        for (ChoseTeamClient client : clients) {
            client.unlockClient();
        }

    }

    // mở khóa cho server
    @Override
    public boolean unlockServer() throws RemoteException {
        // TH1: đang khóa bằng dialog
        if (choseTeamState.getDialogServerWait().isShowing()) {
            choseTeamState.getDialogServerWait().dispose();
            choseTeamState.changeState();
            clients = new ArrayList<>(clientHashMap.values());
            // broadcast cho các máy đang chờ
            for (ChoseTeamClient client : clients) {
                client.unlockClient();
            }
            return true;
        }
        // TH2: không khóa
        return false;
    }

    // trả về số đội chơi
    @Override
    public int getPlayerCount() throws RemoteException {
        return Handler.getInstance().getPlayerCount();
    }

    @Override
    public void broadcastChangeState() throws RemoteException {
        ArrayList<ChoseTeamClient> clients = new ArrayList<>(clientHashMap.values());
        for (ChoseTeamClient client : clients) {
            client.unlockClient();
        }
    }

    @Override
    public void register(int id, ChoseTeamClient client) throws RemoteException {
        System.out.println("Register id = " + id);
        clientHashMap.put(id, client);
    }

    public void setChoseTeamState(ChoseTeamState choseTeamState) {
        this.choseTeamState = choseTeamState;
    }

    public HashMap<Integer, ChoseTeamClient> getClientHashMap() {
        return clientHashMap;
    }

    public List<Integer> getPlayerReady() {
        return playerReady;
    }

    public void setServerReady(boolean serverReady) {
        this.serverReady = serverReady;
    }
}
