package controller;

import model.ChessPlayer;
import model.DataBase;
import view.PresentationPanel;

import java.sql.Connection;
import java.util.List;

/**
 * Klasa koja je posrednik izmedu view-a i modela. Sadrzi metode koje delegiraju metodama iz paketa model i paketa view. Dio je MVC predloska.
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */

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

    /**
     * metoda koja brise dotadasnji sadrzaj sa prezentacijskog panela i brise sahiste iz "database" tj. liste koja sadrzi objekte tipa ChessPlayer
     * @param loadData4File
     *          objekt koji ce vratiti listu objekata tipa ChessPlayer (ucitane iz datoteke) cijim cemo sadrzajem zamijeniti dotadasnji sadrzaj iz "database"
     * @param presPanel
     *          panel u kojem se nalazi Text Area s kojeg brisemo tekst i stavljamo novi sadrzaj iz "database"
     */
    public void replaceDBDataWithLoadedData(LoadData4FileCommand loadData4File, PresentationPanel presPanel){
        presPanel.clearAll4TxtArea();
        db.clearDBData();
        db.addAllChessPlayers2DB(loadData4File.getLoadedChessPlayers());

        showAllChessPlayersOnPresPanel(presPanel);
    }

    public Connection getConnection(ConnectCommand command) {
       return command.getConnection();
    }

    /**
     * metoda koja ispisuje sadrzaj liste iz database na prezentacijskom panelu
     * @param presPanel
     *          objekt klase PresentationPanel na kojem ce se prikazivati podaci aplikacije
     */
    public void showAllChessPlayersOnPresPanel(PresentationPanel presPanel){
        for (ChessPlayer cp : db.getAllChessPlayers4DB()) {
            presPanel.showOnPanel(cp);
        }
    }

    /**
     * metoda koja brise dotadasnji sadrzaj sa prezentacijskog panela i briše sahiste iz "database" tj. liste koja sadrzi objekte tipa ChessPlayer
     * @param cmd
     *          objekt koji će vratiti listu objekata tipa ChessPlayer (ucitane sa servera) cijim ćemo sadrzajem zamijeniti dotadasnji sadržaj iz "database"
     * @param presPanel
     *          panel u kojem se nalazi Text Area s kojeg brisemo tekst i stavljamo novi sadrzaj iz "database"
     */
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


