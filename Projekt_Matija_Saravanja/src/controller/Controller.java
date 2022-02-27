package controller;

import model.ChessPlayer;
import model.DataBase;
import view.PresentationPanel;

import java.sql.Connection;
import java.util.List;

public class Controller {

    private DataBase db;

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

    public void replaceDBDataWithLoadedData(LoadData4FileCommand loadData4File, PresentationPanel presPanel){
        db.clearDBData();
        db.addAllChessPlayers2DB(loadData4File.getLoadedChessPlayers());

        showAllChessPlayersOnPresPanel(presPanel);
    }

    public Connection getConnection(ConnectCommand command) {
       return command.getConnection();
    }

    public void showAllChessPlayersOnPresPanel(PresentationPanel presPanel){
        for (ChessPlayer cp : db.getAllChessPlayers4DB()) {
            presPanel.showOnPanel(cp);
        }
    }

    public void replaceDBDataWithLoadedServerData(Load4ServerCommand cmd, PresentationPanel presPanel){
        List<ChessPlayer> list = cmd.getLoadedChessPlayers();

        presPanel.clearAll4TxtArea();
        db.clearDBData();
        db.addAllChessPlayers2DB(list);

        for (ChessPlayer cp : db.getAllChessPlayers4DB()) {
            presPanel.showOnPanel(cp);
        }
    }


    public int getSizeOfChessPlayerList(){
        return db.getAllChessPlayers4DB().size();
    }
}


