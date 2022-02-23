package view;

import javax.swing.*;
import java.awt.*;

public class PresentationPanel extends JPanel {

    private JTextPane textPane;
    private JScrollPane scrollPane;
    private MainFrame.MyMenuBar menuBar;

    public PresentationPanel(MainFrame.MyMenuBar menuBar){
        this.menuBar = menuBar;
        Dimension dims = getPreferredSize();
        dims.width = 700;
        dims.height = 250;
        setPreferredSize(dims);


        createComp();
        //activateComp();
        layoutComp();

    }

    private void layoutComp() {
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(menuBar, BorderLayout.NORTH);
    }

    private void createComp() {
        textPane = new JTextPane();
        textPane.setBackground(Color.DARK_GRAY);
        textPane.setForeground(Color.WHITE);
        textPane.setFont(new Font("Arial", Font.BOLD, 12));
        scrollPane = new JScrollPane(textPane, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }
}
