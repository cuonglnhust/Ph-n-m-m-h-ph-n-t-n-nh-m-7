package rmi.interfaces;

import constant.TeamType;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChoseTeamClient extends Remote {
    public void updateChoseTeam(int id, String name, TeamType teamType) throws RemoteException;
    public void cancelChoseTeam(int id, String name, TeamType teamType) throws RemoteException;
    public void changeState() throws RemoteException;
    public void unlockClient() throws RemoteException;
}
