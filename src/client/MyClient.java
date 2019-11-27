package client;

import client.ClientImplement.ClientImp;
import client.RemoteInterface.IClient;
import client.RemoteInterface.Player;
import server.RemoteInterface.IServer;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyClient {
    public static void main(String[] args) throws UnknownHostException {
        try {

            IServer iServer = (IServer) Naming.lookup("rmi://localhost:9999/hippocampus");

            Player player1 = new Player();// đối tượng player1 được tạo ra sau khi đăng nhập thành công
            IClient iClient = new ClientImp(player1,iServer); // tạo ra đối tượng Client có chứa player


            Player player2 = new Player();// lấy player 2 trong list player sau khi đăng nhập

            //sau khi click mời thì sẽ gọi hàm sendInvitation của server
            iServer.sendInvitation(player1,player2); // trả về câu trả lời player2 có đồng ý chơi cùng hay không

            //nếu player 2 đồng ý chơi cùng thì ngay lập tức player1 tạo trận đấu



        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
