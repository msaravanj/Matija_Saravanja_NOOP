package view;

import controller.*;
import model.ChessPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.sql.Connection;
import java.text.ParseException;
/**
 * Klasa koja predstavlja glavni prozor aplikacije i na koji se postavljaju ostali paneli i menu bar
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class MainFrame extends JFrame {

    private DataPanel dataPanel;
    private CalculatorPanel calculatorPanel;
    private PresentationPanel presentationPanel;
    private MyMenuBar menuBar;
    private DataPanelListener listener;
    private Controller controller;
    private Command command;
    private Connection connection;

    public MainFrame() {
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

        activateApp();
    }

    /**
     * Metoda koja aktivira aplikaciju tako sto postavlja DataPanelListener i
     * dostavlja objekt tipa ChessPlayer kad se dogodi dogadaj u Data Panelu
     *
     */
    private void activateApp() {

       dataPanel.setListener(new DataPanelListener() {
           @Override
           public void dataPanelEventOccured(ChessPlayer chessPlayer) {

               chessPlayer.chessPlayerInfo();
               presentationPanel.showOnPanel(chessPlayer);
               controller.addChessPlayer2DB(chessPlayer);
           }
       });

    }

    /**
     * Metoda koja inicijalizira komponente u glavnom prozoru
     */
    private void createComps() {
        controller = new Controller();
        menuBar = new MyMenuBar();
        try {
            dataPanel = new DataPanel(controller);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        presentationPanel = new PresentationPanel(menuBar);
        calculatorPanel = new CalculatorPanel();
    }


    /**
     * Unutarnja klasa koja predstavlja Menu Bar aplikacije
     */
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

        JFileChooser fileChooser;

        public MyMenuBar() {

            createComp();
            layoutComp();
            activateMenuBar();
        }

        /**
         * Metoda koja aktivira dugmad na menu baru
         */
        private void activateMenuBar() {
            exitItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int res = JOptionPane.showOptionDialog(new JFrame(), "Do you want to exit?","Exit?",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                            new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
                    if (res == JOptionPane.YES_OPTION) {
                        System.out.println("Exit app...");
                        System.exit(0);
                    } else if (res == JOptionPane.NO_OPTION) {
                        System.out.println("Refused to exit...");
                    } else if (res == JOptionPane.CLOSED_OPTION) {
                        System.out.println("Window closed without selecting...");
                    }
                }
            });

            saveDataItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int val = fileChooser.showSaveDialog(null);
                    if (val == JFileChooser.APPROVE_OPTION){
                        File file = fileChooser.getSelectedFile();
                        System.out.println("file: " + file.getAbsolutePath());
                        command = new SaveData2FileCommand(file, controller.getAllChessPlayers4DB());
                        command.runCommand();
                    }
                }
            });

            loadDataItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int val = fileChooser.showOpenDialog(null);
                    if (val == JFileChooser.APPROVE_OPTION){
                        File file = fileChooser.getSelectedFile();
                        System.out.println("File: " + file.getAbsolutePath());
                        command = new LoadData4FileCommand(file);
                        command.runCommand();

                        controller.replaceDBDataWithLoadedData((LoadData4FileCommand) command, presentationPanel);

                    }
                }
            });

            connectItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    command = new ConnectCommand();
                    command.runCommand();
                    connection = controller.getConnection((ConnectCommand) command);
                }
            });

            disconnectItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (connection != null){
                        command = new DisconnectCommand(connection);
                        command.runCommand();
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this, "There is no active connection!", "Server status", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            });

            saveItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (connection != null){
                        int res = JOptionPane.showOptionDialog(new JFrame(), "Save data to server?","MySQL server",
                                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                                new Object[] { "Yes", "No" }, JOptionPane.YES_OPTION);
                        if (res == JOptionPane.YES_OPTION) {
                            System.out.println("Saving data to server...");
                            command = new Save2ServerCommand(connection, controller.getAllChessPlayers4DB());
                            command.runCommand();
                        } else if (res == JOptionPane.NO_OPTION) {
                            System.out.println("User refused to save data...");
                        } else if (res == JOptionPane.CLOSED_OPTION) {
                            System.out.println("Window closed without selecting...");
                        }
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this,"Unable to save data to server while application is disconnected from server!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            loadItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (connection != null){
                        command = new Load4ServerCommand(connection);
                        command.runCommand();
                        controller.replaceDBDataWithLoadedServerData((Load4ServerCommand) command, presentationPanel);

                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this,"Unable to load data from server while application is disconnected from server!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


            setMnemonicsAndAcceleratorsForMenuBarItems();

        }

        /**
         * Metoda koja postavlja mnemonike i akceleratore na Menu Bar Items
         */
        private void setMnemonicsAndAcceleratorsForMenuBarItems(){
            serverMenu.setMnemonic(KeyEvent.VK_E);
            connectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.CTRL_MASK));
            disconnectItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
            saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
            loadItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

            fileMenu.setMnemonic(KeyEvent.VK_E);
            saveDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
            loadDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
        }

        /**
         * Metoda koja postavlja menu bar items na menu bar
         */
        private void layoutComp() {
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

        /**
         * Metoda koja inicijalizira komponente menu bara
         */
        private void createComp() {
            fileMenu = new JMenu("File");
            serverMenu = new JMenu("Server");
            saveDataItem = new JMenuItem("Save data");
            loadDataItem = new JMenuItem("Load data");
            exitItem = new JMenuItem("Exit");

            connectItem = new JMenuItem("Connect");
            loadItem = new JMenuItem("Load");
            saveItem = new JMenuItem("Save");
            disconnectItem = new JMenuItem("Disconnect");
            fileChooser = new JFileChooser();
        }

    }
}
