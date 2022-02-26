package controller;

import java.sql.Connection;
import java.sql.SQLException;

public class DisconnectCommand implements Command {

    private Connection connection;

    public DisconnectCommand(Connection connection){
        this.connection = connection;
    }

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
