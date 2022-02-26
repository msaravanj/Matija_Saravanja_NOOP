package test_server_db;

import model.ChessPlayer;
import model.ChessTitleEnum;
import model.DataBase;

public class AppTestServer {

    public static void main(String[] args) {
        DataBase db = new DataBase();


        ChessPlayer chessPlayer1 = new ChessPlayer("Matija", "Saravanja", "Male", 2000, "Croatia", 1947, 14534177, ChessTitleEnum.none);
        ChessPlayer chessPlayer2 = new ChessPlayer("Sasa", "Rezan", "Male", 1978, "Croatia", 2396, 14504804, ChessTitleEnum.IM);
        ChessPlayer chessPlayer3 = new ChessPlayer("Magnus", "Carlsen", "Male", 1990, "Norway", 2865, 1503014, ChessTitleEnum.GM);
        db.addChessPlayer(chessPlayer1);
        db.addChessPlayer(chessPlayer2);
        db.addChessPlayer(chessPlayer3);
        db.listAllChessPlayersFromDB();
        TestDBServer test = new TestDBServer(db.getAllChessPlayers4DB());

        test.connect();
        test.saveData2DBServer();
        test.disconnect();
    }
}
