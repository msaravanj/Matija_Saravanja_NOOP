package controller;

import model.ChessPlayer;
import model.ChessTitleEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Load4ServerCommand implements Command {

    private Connection connection;
    private List<ChessPlayer> chessPlayers;

    public Load4ServerCommand(Connection connection){
        this.connection = connection;
        chessPlayers = new ArrayList<ChessPlayer>();
    }

    @Override
    public void runCommand() {
        ResultSet results = null;
        PreparedStatement selectStm = null;

        if (connection != null){
            System.out.println("Loading data from DB server");
            String selectsSQL = "select * from ChessPlayerTbl order by elo_rating desc";

            try {
                selectStm = connection.prepareStatement(selectsSQL);
                results = selectStm.executeQuery();
                chessPlayers.clear();

                while (results.next()){
                    int col = 1;
                    int id = results.getInt(col++);
                    String name = results.getString(col++);
                    String surname = results.getString(col++);
                    String gender = results.getString(col++);
                    int birthYear = results.getInt(col++);
                    String country = results.getString(col++);
                    int eloRating = results.getInt(col++);
                    int fideId = results.getInt(col++);
                    ChessTitleEnum chessTitle = ChessTitleEnum.valueOf(results.getString(col++));

                    ChessPlayer chessPlayer = new ChessPlayer(id, name, surname, gender, birthYear, country, eloRating, fideId, chessTitle);
                    this.chessPlayers.add(chessPlayer);

                }
                System.out.println("Data loaded from server!");
                System.out.println("List: " + chessPlayers);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ChessPlayer> getLoadedChessPlayers() {
        return this.chessPlayers;
    }
}
