//package rmi.client;
//
//import SCCommon.*;
//import rmi.client.ClientImplement.ClientImp;
//
//import java.net.MalformedURLException;
//import java.net.UnknownHostException;
//import java.rmi.Naming;
//import java.rmi.NotBoundException;
//import java.rmi.RemoteException;
//import java.sql.SQLException;
//import java.util.List;
//
//public class MyClient {
//    public static void main(String[] args) throws UnknownHostException {
//        try {
//
//            IServer iServer = (IServer) Naming.lookup("rmi://localhost:9999/abc");
//
//            Player player1 = iServer.signIn("khanh","khanh");
//
//            if(player1 != null) {                                                   // đăng nhập thành công
//                IClient iClient = new ClientImp(player1, iServer); // tạo ra đối tượng Client có chứa player
//
//                // lấy player 2 trong list player đang online sau khi đăng nhập mà không có player1 trong đó
//                List<Player> players = iServer.getPlayersOnline(player1);
//
//                // show history của player1
//                List<Match> matchesHistory = iServer.getMatchHistory(player1.getPid());
//                for (Match match : matchesHistory){
//                    System.out.println("match have id : " + match.getId() + "have : ");
//                    System.out.println("match duration : " + match.getDuration()+ " and ");
//                    System.out.println("match winner id : " + match.getWinner()+ " and ");
//                    List<Player> playerList = match.getPlayers();
//                    for (Player player : playerList){
//                        System.out.println(" have couter name is : " + player.getPname());
//                    }
//                }
//                //lấy các match đang diễn ra
//                List<Match> matches = iServer.getMatchsOnline();
//
//                //ví dụ mời player2 là player đang online có index là 1
//                if (players.size() > 0){
//
//                    Player player2 = players.get(1);
//
//                    //sau khi click mời thì sẽ gọi hàm sendInvitation của server
//                    // trả về câu trả lời player2 có đồng ý chơi cùng hay không
//                    iServer.sendInvitation(player1, player2, new ConnectionData("636531",5000,"8736187367"));
//                }else {
//                    System.out.println("there is noone online");
//                }
//
//
//                //nếu player 2 đồng ý chơi cùng thì ngay lập tức player1 tạo trận đấu
//
//             }else {
//                System.out.println("Username or password incorrect !");
//            }
//
//
//        } catch (RemoteException | NotBoundException | MalformedURLException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}
