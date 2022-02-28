package model;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasa koja predstavlja spremnik podataka tj. objekata tipa ChessBase koji će se spremati ili ucitavati.
 * Mozemo je promatrati kao jednostavnu bazu podataka.
 *
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class DataBase {

    /**
     * Objekti sahista se spremaju u strukturu ArrayList
     */
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

    /**
     * metoda koja izlistava objekte iz "spremnika" u konzoli
     */
    public void listAllChessPlayersFromDB(){
        for (ChessPlayer player : chessPlayers) {
            System.out.println(player);
            System.out.println("--------------------------------");
        }
    }

    public void clearDBData(){
        chessPlayers.clear();
    }

    /**
     * metoda koja dodaje objekte u listu
     * @param players
     *          lista objekata tipa ChessPlayer
     */
    public void addAllChessPlayers2DB(List<ChessPlayer> players){
        for (ChessPlayer cp : players) {
            chessPlayers.add(cp);
        }
    }
}
