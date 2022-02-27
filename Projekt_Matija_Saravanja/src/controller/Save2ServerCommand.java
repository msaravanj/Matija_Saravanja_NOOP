package controller;

import model.ChessPlayer;
import model.ChessTitleEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Save2ServerCommand implements Command {

    private Connection connection;
    private List<ChessPlayer> chessPlayers;

    public Save2ServerCommand(Connection connection, List<ChessPlayer> chessPlayers){

        this.connection = connection;
        this.chessPlayers = chessPlayers;
    }

    @Override
    public void runCommand() {
        if (connection != null){

            // SQL queries
            String countSQL = "select count(*) as count from ChessPlayerTbl where id=?";
            String insertSQL = "insert into ChessPlayerTbl (id,name,surname,gender,birth_year,country,elo_rating,fide_id,chess_title) values (?,?,?,?,?,?,?,?,?)";
            String updateSQL = "update ChessPlayerTbl set name=?,surname=?,gender=?,birth_year=?,country=?,elo_rating=?,fide_id=?,chess_title=? where id=?";

            // statements
            try {
                PreparedStatement countStm = connection.prepareStatement(countSQL);
                PreparedStatement insertStm = connection.prepareStatement(insertSQL);
                PreparedStatement updateStm = connection.prepareStatement(updateSQL);

                for (ChessPlayer chessPlayer : chessPlayers) {
                    int id = chessPlayer.getId();
                    String name = chessPlayer.getName();
                    String surname = chessPlayer.getSurname();
                    String country = chessPlayer.getCountry();
                    int birthYear = chessPlayer.getBirthYear();
                    int fideId = chessPlayer.getFideId();
                    String gender = chessPlayer.getGender();
                    ChessTitleEnum title = chessPlayer.getChessTitle();
                    int eloRating = chessPlayer.getEloRating();

                    countStm.setInt(1, id);
                    ResultSet result = countStm.executeQuery();
                    result.next();
                    int count = result.getInt(1);
                    System.out.println("Counted: " + count);
                    if (count == 0){
                        System.out.println("Insert new chess player in db -> id: " + id);

                        // insert cmd
                        int col = 1;
                        insertStm.setInt(col++, id);
                        insertStm.setString(col++, name);
                        insertStm.setString(col++, surname);
                        insertStm.setString(col++, gender);
                        insertStm.setInt(col++, birthYear);
                        insertStm.setString(col++, country);
                        insertStm.setInt(col++, eloRating);
                        insertStm.setInt(col++, fideId);
                        insertStm.setString(col++, title.name());

                        insertStm.executeUpdate();


                    } else{
                        System.out.println("Update a chess player in db with id -> "+id);
                        // update data cmd
                        int col = 1;
                        updateStm.setString(col++, name);
                        updateStm.setString(col++, surname);
                        updateStm.setString(col++, gender);
                        updateStm.setInt(col++, birthYear);
                        updateStm.setString(col++, country);
                        updateStm.setInt(col++, eloRating);
                        updateStm.setInt(col++, fideId);
                        updateStm.setString(col++, title.name());
                        updateStm.setInt(col++, id);

                        updateStm.executeUpdate();

                    }
                }

                countStm.close();
                insertStm.close();
                updateStm.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }
}
