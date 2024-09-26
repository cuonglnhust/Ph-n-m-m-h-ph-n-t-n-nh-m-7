package rmi.interfaces;

import constant.TeamType;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ChoseTeamServer extends Remote {
    public boolean hello() throws RemoteException;

    public void showChoseTeam(String name, TeamType teamType, int id) throws RemoteException;

    public void cancelChoseTeam(int id, String name, TeamType teamType) throws RemoteException;

    public void broadcastChose(int id, String name, TeamType teamType) throws RemoteException;

    public void broadcastCancel(int id, String name, TeamType teamType) throws RemoteException;

    public void broadcastChangeState() throws RemoteException;

    // kiểm tra xem các client đã sẵn sàng hay chưa
    public boolean messageReady(int id) throws RemoteException;

    // kiểm tra server đã click chưa
    public boolean serverReady() throws RemoteException;

    // báo cho server là tất cả các máy đã sẵn sàng
    public void allReady(int id) throws RemoteException;

    // mở khóa cho server
    public boolean unlockServer() throws RemoteException;

    // lấy số lượng đội chơi
    public int getPlayerCount() throws RemoteException;

    public void register(int id, ChoseTeamClient client) throws RemoteException;
}
