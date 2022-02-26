package view;

import javax.swing.*;
import java.text.ParseException;

public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {

                try {
                    new MainFrame();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
