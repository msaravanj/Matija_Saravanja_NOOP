package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectCommand implements Command {

    private Connection connection;

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

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
