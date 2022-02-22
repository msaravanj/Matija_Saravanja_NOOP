package model;

import java.util.ArrayList;

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

    public void listAllChessPlayers(){
        for (ChessPlayer player : chessPlayers) {
            System.out.println(player);
            System.out.println("--------------------------------");
        }
    }
}
