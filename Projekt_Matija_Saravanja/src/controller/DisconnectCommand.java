package controller;

import view.CalculatorPanel;
import view.MainFrame;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
/**
 * Klasa koja implementira sucelje Command na nacin da izvrsava odspajanje sa server
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class DisconnectCommand implements Command {

    private Connection connection;

    public DisconnectCommand(Connection connection){
        this.connection = connection;
    }

    /**
     * metoda koja izvrsava odspajanje sa servera
     */
    @Override
    public void runCommand() {
        try {
            connection.close();
            System.out.println("Success-disconnected from MySQL DB!");
            JOptionPane.showMessageDialog(null,"Disconnected from a server!", "Server status", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection to close!");
            JOptionPane.showMessageDialog(null, "Disconnecting failed!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
}
