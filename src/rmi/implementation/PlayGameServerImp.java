package rmi.implementation;

import SCCommon.Match;
import constant.DiceValue;
import constant.TeamType;
import entity.changed.data.HorseData;
import main.Handler;
import map.local.MapTemp;
import rmi.interfaces.PlayGameClient;
import rmi.interfaces.PlayGameServer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayGameServerImp extends UnicastRemoteObject implements PlayGameServer {

    HashMap<Integer, PlayGameClient> playGameClientHashMap;
    ArrayList<PlayGameClient> playGameClients;
    MapTemp mapTemp;

    public PlayGameServerImp() throws RemoteException {
        playGameClientHashMap = new HashMap<>();
    }

    @Override
    public boolean hello() throws RemoteException {
        return true;
    }

    // quảng bá sự kiện click vào xúc xắc
    @Override
    public void broadcastDiceClick(int id) throws RemoteException {
        // broadcast cho các Client còn lại
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        playGameClients.remove(playGameClientHashMap.get(id));
        for (PlayGameClient client : playGameClients) {
            client.updateDiceClick(id);
        }
        // tự cập nhật cho mình
        mapTemp.getPlayerCopyHashMap().get(id).getDiceCopy().setClick(true);

        // quảng bá cho các máy xem nếu có
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateDiceClick(id);
        }
    }

    // quảng báo giá trị diceValue
    @Override
    public void broadcastDiceValue(int id, DiceValue diceValue) throws RemoteException {
        // broadcast cho các client còn lại
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        playGameClients.remove(playGameClientHashMap.get(id));
        for (PlayGameClient client : playGameClients) {
            client.updateDiceValue(id, diceValue);
        }
        // tự cập nhật cho mình
        mapTemp.getPlayerCopyHashMap().get(id).getDiceCopy().setDiceValue(diceValue);

        // quảng bá cho các máy xem nếu có
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateDiceValue(id, diceValue);
        }

    }

    // quảng báo sự kiện click vào xúc xắc từ server
    public void broadcastDiceClickFromServer(int id) {
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        for (PlayGameClient client : playGameClients) {
            try {
                client.updateDiceClick(id);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        // quảng bá cho các máy xem nếu có
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateDiceClick(id);
        }
    }

    // quảng báo diceValue vào ngựa từ server
    public void broadcastDiceValueFromServer(int id, DiceValue diceValue) {
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        for (PlayGameClient client : playGameClients) {
            try {
                client.updateDiceValue(id, diceValue);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        // quảng bá cho các máy xem nếu có
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateDiceValue(id, diceValue);
        }

    }

    @Override
    public void register(int id, PlayGameClient playGameClient) throws RemoteException {
        playGameClientHashMap.put(id, playGameClient);
    }

    @Override
    public TeamType getTurn() throws RemoteException {
        return mapTemp.getTurn();
    }

    @Override
    public void changeTurn(int id) throws RemoteException {
        // broadcast cho tất cả các máy client còn lại
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        playGameClients.remove(playGameClientHashMap.get(id));
        for (PlayGameClient client : playGameClients) {
            client.changeTurn();
        }

        // tự cập nhật
        mapTemp.setChangeTurn(true);
    }

    // quảng bá change Turn cho các máy client
    public void changeTurnFromServer() {
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        for (PlayGameClient client : playGameClients) {
            try {
                client.changeTurn();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    // cập nhật virtual Map từ Client
    @Override
    public void updateVirtualMap(int id, int position, TeamType teamType) throws RemoteException {
        // cập nhật cho máy khác
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        playGameClients.remove(playGameClientHashMap.get(id));
        for (PlayGameClient client : playGameClients) {
            client.updateVirtualMap(position, teamType);
        }
        // Tự cập nhật cho mình
        mapTemp.updateVirtualMap(position, teamType);
    }

    // quảng bá virtual Map từ Server
    public void updateVirtualMapFromServer(int id, int position, TeamType teamType) throws RemoteException {
        // cập nhật cho các khác
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        for (PlayGameClient client : playGameClients) {
            client.updateVirtualMap(position, teamType);
        }
    }

    // cập nhật vị trí một ngựa
    @Override
    public void updateHorse(int id, int horseId, int position, int rank) throws RemoteException {
        // broadcast cho các client khác
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        playGameClients.remove(playGameClientHashMap.get(id));
        for (PlayGameClient client : playGameClients) {
            client.updateHorse(id, horseId, position, rank);
        }
        // tự cập nhật
        mapTemp.getPlayerCopyHashMap().get(id).setHorseData(new HorseData(horseId, position, rank));

        // quảng bá cho các máy xem
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateHorsePosition(id, horseId, position, rank);
        }
    }

    // quảng bá di chuyển của ngựa ở Server
    public void updateHorseFromServer(int id, int horseId, int position, int rank) throws RemoteException {
        // broadcast cho các client khác
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        for (PlayGameClient client : playGameClients) {
            client.updateHorse(id, horseId, position, rank);
        }

        // quảng bá cho các máy xem
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateHorsePosition(id, horseId, position, rank);
        }
    }

    // cập nhật vị trí bị đá bởi 1 client
    @Override
    public void updateKickedHorsePosition(int id, int kickedId, int position) throws RemoteException {
        // nếu là ngựa của Server bị đá
        if (Handler.getInstance().getId() == kickedId) {
            // server tự cập nhật cho player của mình
            mapTemp.getPlayer().setKick(true);
            mapTemp.getPlayer().setKickedPosition(position);

            // quảng bá cho client
            playGameClients = new ArrayList<>(playGameClientHashMap.values());
            playGameClients.remove(playGameClientHashMap.get(id));
            for (PlayGameClient client : playGameClients) {
                client.updateKickedHorsePosition(kickedId, position);
            }

            // quảng bá cho người xem
            if (Handler.getInstance().getServerViewer() != null) {
                Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateKickedHorsePosition(id, position);
            }
        }
        // nếu là ngựa của client khác bị đá
        else {
            // tự cập nhật cho playerCopy của mình
            mapTemp.getPlayerCopyHashMap().get(kickedId).setKick(true);
            mapTemp.getPlayerCopyHashMap().get(kickedId).setKickedPosition(position);

            // quảng bá đến tất cả các client khác
            playGameClients = new ArrayList<>(playGameClientHashMap.values());
            playGameClients.remove(playGameClientHashMap.get(id));
            for (PlayGameClient client : playGameClients) {
                client.updateKickedHorsePosition(kickedId, position);
            }

            // quảng bá cho người xem
            if (Handler.getInstance().getServerViewer() != null) {
                Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateKickedHorsePosition(id, position);
            }
        }
    }

    public void updateKickedHorsePositionFromServer(int id, int kickedId, int position) {
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        for (PlayGameClient client : playGameClients) {
            try {
                client.updateKickedHorsePosition(kickedId, position);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        // quảng bá cho người xem
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateKickedHorsePosition(id, position);
        }
    }

    // quảng bá kết quả trận đấu
    @Override
    public void updateResult(int id) throws RemoteException {
        // quảng bá ra các máy khác
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        playGameClients.remove(playGameClientHashMap.get(id));
        for (PlayGameClient client : playGameClients) {
            client.updateResultLose();
        }

        // tự cập nhật
        mapTemp.setLose(true);

        // khởi tạo kết quả trận đấu
        Match match = new Match();
        match.setWinner(id);
        String duration = String.valueOf(System.currentTimeMillis() - mapTemp.getDuration());
        match.setDuration(duration);
        match.setPlayers(mapTemp.getPlayerList());

        // Gửi kết quả trận đấu lên Server
        Handler.getInstance().getClientLogin().getiServer().updateMatchHistory(Handler.getInstance()
                .getId(), match);

        // quảng bá cho người xem
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateResult(id);
        }
    }

    // quảng bá kết quả trận đấu
    public void updateResultFromServer() throws RemoteException {
        // quảng bá ra các máy khác
        playGameClients = new ArrayList<>(playGameClientHashMap.values());
        for (PlayGameClient client : playGameClients) {
            client.updateResultLose();
        }

        // khởi tạo kết quả trận đấu
        Match match = new Match();
        match.setWinner(Handler.getInstance().getId());
        String duration = String.valueOf(System.currentTimeMillis() - mapTemp.getDuration());
        match.setDuration(duration);
        match.setPlayers(mapTemp.getPlayerList());

        // Gửi kết quả trận đấu lên Server
        Handler.getInstance().getClientLogin().getiServer().updateMatchHistory(Handler.getInstance()
                .getId(), match);

        // quảng bá cho người xem
        if (Handler.getInstance().getServerViewer() != null) {
            Handler.getInstance().getServerViewer().getWatchMatchServerImp().updateResult(Handler.getInstance().getId());
        }
    }


    public void setMapTemp(MapTemp mapTemp) {
        this.mapTemp = mapTemp;
    }
}
