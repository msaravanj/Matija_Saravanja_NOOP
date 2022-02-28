package controller;

import model.ChessPlayer;

import java.io.*;
import java.util.Arrays;
import java.util.List;
/**
 * Klasa koja implementira sucelje Command na nacin da ucitava podatke iz datoteke s racunala
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class LoadData4FileCommand implements Command {

    private File file;
    private List<ChessPlayer> chessPlayers;

    public LoadData4FileCommand(File file){
        this.file = file;
    }

    /**
     * Metoda koja izvrsava punjenje liste tj. aplikacijske "database" sa objektima tipa ChessPlayer ucitanim iz datoteke s racunala
     */
    @Override
    public void runCommand() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ChessPlayer[] players = (ChessPlayer[]) ois.readObject();
            chessPlayers = Arrays.asList(players);
            fis.close();
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    public List<ChessPlayer> getLoadedChessPlayers() {
        return chessPlayers;
    }
}
