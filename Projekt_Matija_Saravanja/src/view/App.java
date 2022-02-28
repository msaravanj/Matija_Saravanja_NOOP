package view;

import javax.swing.*;

/**
 * Klasa sa main metodom koja pokrece aplikaciju
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable(){

            @Override
            public void run() {

                new MainFrame();
            }
        });
    }
}
