package view;

import model.ChessPlayer;

import javax.swing.*;
import java.awt.*;
/**
 * Klasa koja predstavlja prezentacijski panel s text area na kojem će se ispisivati objekti ChessPlayer
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class PresentationPanel extends JPanel {

    private JTextArea textArea;
    private JScrollPane scrollPane;
    private MainFrame.MyMenuBar menuBar;

    public PresentationPanel(MainFrame.MyMenuBar menuBar){
        this.menuBar = menuBar;
        Dimension dims = getPreferredSize();
        dims.width = 700;
        dims.height = 250;
        setPreferredSize(dims);


        createComp();
        layoutComp();

    }

    /**
     * Metoda koja postavlja komponente na panel
     */
    private void layoutComp() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(menuBar, BorderLayout.NORTH);
    }

    /**
     * Metoda koja inicijalizira komponente
     */
    private void createComp() {
        textArea = new JTextArea();
        textArea.setBackground(Color.DARK_GRAY);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Arial", Font.BOLD, 12));
        scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    /**
     * Metoda koja ispisuje objekte sahiste na text area
     * @param chessPlayer
     *          objekt tipa ChessPlayer koji ce se prikazati na text area
     */
    public void showOnPanel(ChessPlayer chessPlayer){
        this.textArea.append(chessPlayer.toString()+"\n");
    }

    /**
     * Metoda koja brise sve sa text area
     */
    public void clearAll4TxtArea(){
        this.textArea.selectAll();
        this.textArea.replaceSelection("");
    }
}
