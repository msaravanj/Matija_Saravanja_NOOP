package controller;

import model.ChessPlayer;

import javax.swing.*;
import java.io.*;
import java.util.List;
/**
 * Klasa koja implementira sucelje Command na nacin da izvrsava spremanje objekata u datoteku na racunalo
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class SaveData2FileCommand implements Command {

    private File file;
    private List<ChessPlayer> chessPlayers;

    public SaveData2FileCommand(File file, List<ChessPlayer> chessPlayers){
        this.file = file;
        this.chessPlayers = chessPlayers;
    }

    /**
     * metoda koja sprema objekte iz "database-a" u racunalnu datoteku
     */
    @Override
    public void runCommand() {

        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            ChessPlayer[] players = chessPlayers.toArray(new ChessPlayer[chessPlayers.size()]);
            oos.writeObject(players);

            fos.close();
            oos.close();

            JOptionPane.showMessageDialog(null, "Data saved to file: "+file.getName(), "Saving info", JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
