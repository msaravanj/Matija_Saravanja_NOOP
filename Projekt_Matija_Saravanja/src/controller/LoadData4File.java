package controller;

import model.ChessPlayer;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class LoadData4File implements Command {

    private File file;
    private List<ChessPlayer> chessPlayers;

    public LoadData4File(File file){
        this.file = file;
    }


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
