package controller;

import model.ChessPlayer;
import model.DataBase;

import java.sql.Connection;
import java.util.List;

public class Controller {

    private DataBase db;
    private Connection connection;

    public Controller(){
        db = new DataBase();
    }

    public void addChessPlayer2DB(ChessPlayer chessPlayer){
        db.addChessPlayer(chessPlayer);
    }

    public List<ChessPlayer> getAllChessPlayers4DB(){
        return db.getAllChessPlayers4DB();
    }


}
