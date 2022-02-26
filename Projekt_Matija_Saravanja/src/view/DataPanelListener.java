package view;

import model.ChessPlayer;

import java.util.EventListener;

public interface DataPanelListener extends EventListener {

    public void dataPanelEventOccured(ChessPlayer chessPlayer);
}
