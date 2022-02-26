package view;

import controller.*;
import model.ChessPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.text.ParseException;

public class MainFrame extends JFrame {

    private DataPanel dataPanel;
    private CalculatorPanel calculatorPanel;
    private PresentationPanel presentationPanel;
    private MyMenuBar menuBar;
    private DataPanelListener listener;
    private Controller controller;
    private Command command;
    private Connection connection;

    public MainFrame() throws ParseException {
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

    private void createComps() throws ParseException {
        menuBar = new MyMenuBar();
        dataPanel = new DataPanel();
        presentationPanel = new PresentationPanel(menuBar);
        calculatorPanel = new CalculatorPanel();
        controller = new Controller();
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

        JFileChooser fileChooser;

        public MyMenuBar() {

            createComp();
            layoutComp();
            activateMenuBar();
        }

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
                        command = new LoadData4File(file);
                        command.runCommand();

                        controller.clearPresPanel(presentationPanel);
                        controller.replaceDBDataWithLoadedData((LoadData4File) command, presentationPanel);

                    }
                }
            });

            connectItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    command = new ConnectCommand();
                    command.runCommand();
                    JOptionPane.showMessageDialog(MainFrame.this, "Server status","Connection to server has been established!", JOptionPane.INFORMATION_MESSAGE);
                    connection = controller.getConnection((ConnectCommand) command);
                }
            });

            disconnectItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (connection != null){
                        command = new DisconnectCommand(connection);
                        command.runCommand();
                        JOptionPane.showMessageDialog(MainFrame.this, "Server status","Disconnected from a server!", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(MainFrame.this, "Server status","There is no active connection!", JOptionPane.INFORMATION_MESSAGE);

                    }
                }
            });



        }

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
