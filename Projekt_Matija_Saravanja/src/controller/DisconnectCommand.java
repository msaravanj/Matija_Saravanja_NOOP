package controller;

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
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("No connection to close!");
        }
    }
}
