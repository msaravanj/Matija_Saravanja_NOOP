package view;

import model.ChessPlayer;

import java.util.EventListener;

/**
 * Sucelje osluskivac koje slusa kad će se dogoditi dogadaj te proslijeduje objekt tipa ChessPlayer
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public interface DataPanelListener extends EventListener {

    public void dataPanelEventOccured(ChessPlayer chessPlayer);
}
