package controller;

import model.ChessPlayer;
import model.ChessTitleEnum;
import view.CalculatorPanel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 * Klasa koja implementira sucelje Command na nacin da ucitava podatke sa servera
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class Load4ServerCommand implements Command {

    private Connection connection;
    private List<ChessPlayer> chessPlayers;

    public Load4ServerCommand(Connection connection){
        this.connection = connection;
        chessPlayers = new ArrayList<ChessPlayer>();
    }

    /**
     * metoda koja puni listu aplikacijske "chessbase" sa sadrzajem sa serverske baze podataka
     */
    @Override
    public void runCommand() {
        ResultSet results = null;
        PreparedStatement selectStm = null;

        if (connection != null){
            System.out.println("Loading data from DB server");
            String selectsSQL = "select * from ChessPlayerTbl order by surname";

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
                JOptionPane.showMessageDialog(null, "Loading failed!",
                        "ERROR", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

    public List<ChessPlayer> getLoadedChessPlayers() {
        return this.chessPlayers;
    }
}
