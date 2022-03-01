package view;

import org.json.JSONObject;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
/**
 * Klasa koja predstavlja desni panel za izracunavanje promjene rejtinga nakon odigranog meca
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class CalculatorPanel extends JPanel {

    private MaskFormatter rtgMask;
    private JFormattedTextField yourRatingField;
    private JFormattedTextField oppRatingField;
    private JRadioButton wonRadio;
    private JRadioButton drawRadio;
    private JRadioButton lostRadio;
    private ButtonGroup bg2;
    private JComboBox<Integer> koefCombo;
    private JButton calcButton;
    private JTextField resultField;
    private JButton loadYrsRtgBtn;
    private JButton loadOppRtgBtn;

    private HttpURLConnection connection;

    public CalculatorPanel(){

        Dimension dims = getPreferredSize();
        dims.width = 390;
        dims.height = 350;
        setPreferredSize(dims);


        createComps();
        activateComp();
        layoutComp();
        setBorders();
    }

    /**
     * GET metoda koja dohvaca rejting igraca iz API-ja s interneta po igracevom FIDE rejtingu koji je potrebno upisati u skocni prozor
     *
     * @param fideId
     *      sluzbeni ID od organizacije FIDE
     * @return
     *      vraca JSON object s podacima o rejtingu
     */
    private JSONObject getRating4APIByFideId(int fideId){
        JSONObject myResponse = null;
        try {
            URL url = new URL("https://fide-ratings-scraper.herokuapp.com/player/"+fideId+"/elo");
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            myResponse = new JSONObject(response.toString());

        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return myResponse;
    }

    /**
     * Metoda koja vraca komponente u pocetno stanje
     */
    private void resetPanel(){
        oppRatingField.setValue(null);
        yourRatingField.setValue(null);
        koefCombo.setSelectedIndex(0);
        bg2.setSelected(drawRadio.getModel(), true);
        yourRatingField.requestFocus();
    }

    /**
     * Metoda koja aktivira komponente ovog panela
     *
     */
    private void activateComp() {

        loadYrsRtgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fideId = JOptionPane.showInputDialog("Enter FIDE id of player: ", 1503014);

                if (fideId != null){
                    try{
                        yourRatingField.setText(getRating4APIByFideId(Integer.parseInt(fideId)).getString("standard_elo"));
                    } catch (NumberFormatException nfe){
                        nfe.printStackTrace();
                        JOptionPane.showMessageDialog(CalculatorPanel.this, "Invalid input!",
                                "ERROR", JOptionPane.ERROR_MESSAGE);
                    }
                      }

            }
        });

        loadOppRtgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fideId = JOptionPane.showInputDialog("Enter FIDE id of player: ", 1503014);

                if (fideId != null){
                    oppRatingField.setText(getRating4APIByFideId(Integer.parseInt(fideId)).getString("standard_elo"));
                }
            }
        });

        calcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (yourRatingField.getText().equals("####") || oppRatingField.getText().equals("####")){
                    JOptionPane.showMessageDialog(CalculatorPanel.this, "Rating fields cannot be empty!",
                            "Invalid rating", JOptionPane.WARNING_MESSAGE);
                } else if (Integer.parseInt(yourRatingField.getText()) < 1000 || Integer.parseInt(yourRatingField.getText()) > 3000 || Integer.parseInt(oppRatingField.getText()) < 1000 || Integer.parseInt(oppRatingField.getText()) > 3000){
                    JOptionPane.showMessageDialog(CalculatorPanel.this, "Rating must be in range(1000-3000)!",
                            "Invalid rating", JOptionPane.WARNING_MESSAGE);
                } else {

                    double K = Double.parseDouble(koefCombo.getModel().getSelectedItem().toString());
                    double score = Double.parseDouble(bg2.getSelection().getActionCommand());
                    double rtgA = Double.parseDouble(yourRatingField.getText());
                    double rtgB = Double.parseDouble(oppRatingField.getText());
                    double expScore = 1/(1+Math.pow(10,(rtgB-rtgA)/400.0));

                    double result = K*(score-expScore);

                    resultField.setFont(new Font("Arial", Font.BOLD, 12 ));
                    resultField.setEnabled(true);
                    DecimalFormat df = new DecimalFormat("####0.00");
                    if (result > 0){
                        resultField.setBackground(Color.green);
                        resultField.setText("+" + df.format(result));
                    } else {
                        resultField.setBackground(Color.red);
                        resultField.setText(df.format(result));
                    }

                    resetPanel();

                }
            }
        });
    }

    /**
     * Metoda koja rasporeduje komponente po panelu
     */
    private void layoutComp() {

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weighty = 0.25;
        gbc.weightx = 0.5;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel(""), gbc);
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridy ++;
        add(new JLabel("(Your) rating: "), gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx ++;

        add(yourRatingField, gbc);

        gbc.gridy++;
        gbc.gridx--;

        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Opponent rating: "), gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(oppRatingField, gbc);

        gbc.anchor = GridBagConstraints.FIRST_LINE_END;

        gbc.gridy--;
        add(loadYrsRtgBtn, gbc);
        gbc.gridy++;
        add(loadOppRtgBtn, gbc);

        gbc.gridy++;

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;

        gbc.gridx--;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("K:  "),gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(koefCombo, gbc);
        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;

        add(new JLabel("Result: "), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(wonRadio, gbc);
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(drawRadio, gbc);
        gbc.gridx++;
        add(lostRadio, gbc);


        gbc.gridy++;
        gbc.gridx--;

        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(calcButton, gbc);
        gbc.gridy++;
        gbc.gridx--;

        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Rating changed for: "), gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(resultField, gbc);

    }

    /**
     * Metoda koja postavlja granice panela
     */
    private void setBorders() {
        Border inner = BorderFactory.createTitledBorder("Rating Calculator");
        Border outer = BorderFactory.createEmptyBorder(5, 2, 5, 5);

        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }


    /**
     * Metoda koja inicijalizira i definira komponente panela
     */
    private void createComps(){
        try {
            rtgMask = new MaskFormatter("####");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        rtgMask.setPlaceholderCharacter('_');

        yourRatingField = new JFormattedTextField(rtgMask);
        oppRatingField = new JFormattedTextField(rtgMask);
        wonRadio = new JRadioButton("won");
        drawRadio = new JRadioButton("draw");
        lostRadio = new JRadioButton("lost");
        wonRadio.setActionCommand("1");
        drawRadio.setActionCommand("0.5");
        lostRadio.setActionCommand("0");
        bg2 = new ButtonGroup();
        bg2.add(wonRadio);
        bg2.add(drawRadio);
        bg2.add(lostRadio);
        bg2.setSelected(drawRadio.getModel(), true);
        Integer[] koefs = {10, 15, 20, 30, 40};
        koefCombo = new JComboBox<Integer>(koefs);
        koefCombo.setSelectedItem(2);
        calcButton = new JButton("Calculate");
        resultField = new JTextField(15);
        loadYrsRtgBtn = new JButton("Find rtg by FIDE id");
        loadOppRtgBtn = new JButton("Find rtg by FIDE id");

        resultField.setEnabled(false);
    }

}