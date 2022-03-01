package controller;

import view.CalculatorPanel;
import view.MainFrame;
import view.PresentationPanel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Klasa koja implementira sucelje Command na nacin da izvrsava spajanje na server
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */

public class ConnectCommand implements Command {

    private Connection connection;

    /**
     * Metoda koja povezuje aplikaciju sa serverom
     */
    @Override
    public void runCommand() {
        System.out.println("Connecting to a server...");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://db4free.net:3306/projektnabaza1";
            String user = "msaravanj";
            String pswd = "3gRMsa.+KStA2iz";

            connection = DriverManager.getConnection(url, user, pswd);
            System.out.println("Connected to: " + connection.toString());

            JOptionPane.showMessageDialog(null,"Connection to server has been established!","Server status", JOptionPane.INFORMATION_MESSAGE);

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Connecting failed!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Connecting failed!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
