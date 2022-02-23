package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private DataPanel dataPanel;
    private CalculatorPanel calculatorPanel;
    private PresentationPanel presentationPanel;
    private MyMenuBar menuBar;

    public MainFrame(){
        super("Chess Players & Rating Calculator App");

        setLayout(new BorderLayout());
        createComps();
        add(presentationPanel, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.WEST);
        add(calculatorPanel, BorderLayout.EAST);


        setSize(800,700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    private void createComps(){
        menuBar = new MyMenuBar();
        dataPanel = new DataPanel();
        presentationPanel = new PresentationPanel(menuBar);
        calculatorPanel = new CalculatorPanel();
    }


    class MyMenuBar extends JMenuBar {

        JMenu fileMenu;
        JMenu serverMenu;
        JMenuItem saveDataItem;
        JMenuItem loadDataItem;
        JMenuItem exitItem;

        JMenuItem connectItem;
        JMenuItem loadItem;
        JMenuItem saveItem;
        JMenuItem disconnectItem;

        public MyMenuBar() {
            fileMenu = new JMenu("File");
            serverMenu = new JMenu("Server");
            saveDataItem = new JMenuItem("Save data");
            loadDataItem = new JMenuItem("Load data");
            exitItem = new JMenuItem("Exit");

            connectItem = new JMenuItem("Connect");
            loadItem = new JMenuItem("Load");
            saveItem = new JMenuItem("Save");
            disconnectItem = new JMenuItem("Disconnect");

            fileMenu.add(saveDataItem);
            fileMenu.add(loadDataItem);
            fileMenu.addSeparator();
            fileMenu.add(exitItem);

            serverMenu.add(connectItem);
            serverMenu.add(saveItem);
            serverMenu.add(loadItem);
            serverMenu.addSeparator();
            serverMenu.add(disconnectItem);

            this.add(fileMenu);
            this.add(serverMenu);
        }
    }
}
