package rmi.interfaces;

import constant.DiceValue;
import constant.TeamType;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PlayGameServer extends Remote {

    public boolean hello() throws RemoteException;

    // quảng bá sự kiện lắc xúc xắc của một client ra tất cả các máy
    public void broadcastDiceClick(int id) throws RemoteException;

    // quảng bá giá trị diceValue của một client ra tất cả các máy
    public void broadcastDiceValue(int id, DiceValue diceValue) throws RemoteException;

    public void register(int id, PlayGameClient playGameClient) throws RemoteException;

    public TeamType getTurn() throws RemoteException;

    // đổi lượt chơi
    public void changeTurn(int id) throws RemoteException;

    // cập nhật virtual Map từ Client
    public void updateVirtualMap(int id, int position,TeamType teamType) throws RemoteException;

    // cập nhật vị trí cho một ngựa
    public void updateHorse(int id, int horseId, int position, int rank) throws RemoteException;

    // cập nhật vị trí đá của ngựa bị đá
    public void updateKickedHorsePosition(int id, int kickedId, int position) throws RemoteException;

    // cập nhật thắng
    public void updateResult (int id) throws RemoteException;
}
