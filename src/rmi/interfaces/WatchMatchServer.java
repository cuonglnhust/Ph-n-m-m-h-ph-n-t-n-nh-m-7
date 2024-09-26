package rmi.interfaces;

import button.PlayerData;
import constant.DiceValue;
import entity.changed.data.HorseData;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;

public interface WatchMatchServer extends Remote {

    public boolean hello() throws RemoteException;

    public void register(int id, WatchMatchClient watchMatchClient) throws RemoteException;

    // lấy danh sách các đội chơi
    public List<PlayerData> getPlayerDataList() throws RemoteException;

    // lấy giá trị xúc xắc của đội chơi có id
    public DiceValue getPlayerDiceValue(int id) throws RemoteException;

    // lấy trạng thái của tất cả các ngựa của một đội chơi
    public HashMap<Integer, HorseData> getPlayerHorseList(int id) throws RemoteException;





}
