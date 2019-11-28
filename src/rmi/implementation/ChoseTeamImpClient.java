package rmi.implementation;

import constant.TeamType;
import state.ChoseTeamState;
import state.client.ChoseTeamServer;
import state.server.ChoseTeamClient;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ChoseTeamImpClient extends UnicastRemoteObject implements ChoseTeamClient {

    private ChoseTeamState choseTeamState;

    public ChoseTeamImpClient(int id, ChoseTeamServer choseTeamServer) throws RemoteException {
        choseTeamServer.register(id, this);
    }

    @Override
    public void updateChoseTeam(int id, String name, TeamType teamType) throws RemoteException {
        System.out.println("Id = " + id + " Name = " + name + "team = " + teamType);
        switch (teamType) {
            case TEAM_BLUE:
                choseTeamState.getBlueTeam().setCantChose(false);
                choseTeamState.getBlueTeam().setName(name);
                choseTeamState.getBlueTeam().setId(id);
                break;
            case TEAM_RED:
                choseTeamState.getRedTeam().setCantChose(false);
                choseTeamState.getRedTeam().setName(name);
                choseTeamState.getRedTeam().setId(id);
                break;
            case TEAM_VIOLET:
                choseTeamState.getVioletTeam().setCantChose(false);
                choseTeamState.getVioletTeam().setName(name);
                choseTeamState.getVioletTeam().setId(id);
                break;
            case TEAM_ORANGE:
                choseTeamState.getOrangeTeam().setCantChose(false);
                choseTeamState.getOrangeTeam().setName(name);
                choseTeamState.getOrangeTeam().setId(id);
                break;
        }
    }

    @Override
    public void cancelChoseTeam(int id, String name, TeamType teamType) throws RemoteException {
        switch (teamType) {
            case TEAM_BLUE:
                choseTeamState.getBlueTeam().setCantChose(true);
                choseTeamState.getBlueTeam().setNameDefault();
                choseTeamState.getBlueTeam().setIdDefault();
                break;
            case TEAM_RED:
                choseTeamState.getRedTeam().setCantChose(true);
                choseTeamState.getRedTeam().setNameDefault();
                choseTeamState.getRedTeam().setIdDefault();
                break;
            case TEAM_VIOLET:
                choseTeamState.getVioletTeam().setCantChose(true);
                choseTeamState.getVioletTeam().setNameDefault();
                choseTeamState.getVioletTeam().setIdDefault();
                break;
            case TEAM_ORANGE:
                choseTeamState.getOrangeTeam().setCantChose(true);
                choseTeamState.getOrangeTeam().setNameDefault();
                choseTeamState.getOrangeTeam().setIdDefault();
                break;
        }
    }

    // thay đổi trạng thái sang GameState khi Server thông báo
    @Override
    public void changeState() throws RemoteException {
        choseTeamState.changeState();
    }

    // mở khóa cho Client đang đợi
    @Override
    public void unlockClient() throws RemoteException {
        if (choseTeamState.getDialogClientWait().isShowing()) {
            choseTeamState.getDialogClientWait().dispose();
        }
    }

    public void setChoseTeamState(ChoseTeamState choseTeamState) {
        this.choseTeamState = choseTeamState;
    }
}

