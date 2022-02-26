package model;

import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private ArrayList<ChessPlayer> chessPlayers;

    public DataBase(){
        chessPlayers = new ArrayList<ChessPlayer>();
    }


    public void addChessPlayer(ChessPlayer chessPlayer){
        chessPlayers.add(chessPlayer);
    }

    public ArrayList<ChessPlayer> getAllChessPlayers4DB(){
        return chessPlayers;
    }

    public void listAllChessPlayersFromDB(){
        for (ChessPlayer player : chessPlayers) {
            System.out.println(player);
            System.out.println("--------------------------------");
        }
    }

    public void clearDBData(){
        chessPlayers.clear();
    }

    public void addAllChessPlayers2DB(List<ChessPlayer> players){
        chessPlayers.addAll(players);
    }
}
