package test_server_db;

import model.ChessPlayer;
import model.ChessTitleEnum;

import java.sql.*;
import java.util.List;

public class TestDBServer {

    private Connection connection;
    private List<ChessPlayer> chessPlayers;

    public TestDBServer(List<ChessPlayer> chessPlayers){
        this.chessPlayers = chessPlayers;
    }

    public void loadData4DServer(){
        ResultSet results = null;
        PreparedStatement selectStm = null;

        if (connection != null){
            System.out.println("Loading data from DB server");
            String selectsSQL = "select * from ChessPlayerTbl order by name";

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
                    chessPlayers.add(chessPlayer);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveData2DBServer(){
        if (connection != null){

            // SQL queries
            String countSQL = "select count(*) as count from ChessPlayerTbl where id=?";
            String insertSQL = "insert into ChessPlayerTbl (id, name, surname, gender, birth_year, country, elo_rating, fide_id, chess_title) values (?,?,?,?,?,?,?,?,?)";
            String updateSQL = "update ChessPlayerTbl set name=?, surname=?, gender=?, birth_year=?, country=?, elo_rating=?, fide_id=?, chess_title=? where id=?";

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
                    countStm.close();
                    insertStm.close();
                    updateStm.close();

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }


    public void connect(){
        System.out.println("Connecting to a server...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://db4free.net:3306/projektnabaza1";
            String user = "msaravanj";
            String pswd = "3gRMsa.+KStA2iz";

            connection = DriverManager.getConnection(url, user, pswd);
            System.out.println("Connected to: " + connection.toString());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }

    public void disconnect(){
        try {
            connection.close();
            System.out.println("Success-disconnected from MySQL DB!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


}
