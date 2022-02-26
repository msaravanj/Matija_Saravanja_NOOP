package controller;

import model.ChessPlayer;
import model.DataBase;
import view.PresentationPanel;

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

    public void clearPresPanel(PresentationPanel presentationPanel){
        presentationPanel.clearAll4TxtArea();
    }

    public void replaceDBDataWithLoadedData(LoadData4File loadData4File, PresentationPanel presPanel){
        db.clearDBData();
        db.addAllChessPlayers2DB(loadData4File.getLoadedChessPlayers());

        for (ChessPlayer player : getAllChessPlayers4DB()) {
            presPanel.showOnPanel(player);
        }
    }

    public Connection getConnection(ConnectCommand command) {
       return command.getConnection();
    }
}
