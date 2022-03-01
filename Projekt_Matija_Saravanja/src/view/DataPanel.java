package view;

import controller.Controller;
import model.ChessPlayer;
import model.ChessTitleEnum;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
/**
 * Klasa koja predstavlja lijevi panel koji sadrzava polja za upis informacija o
 * sahistu koji ce biti spremljen
 *
 * @author Matija Saravanja
 *
 * @since veljaca, 2022.
 */
public class DataPanel extends JPanel {

    private JTextField nameField;
    private JTextField surnameField;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private ButtonGroup bg;
    private JFormattedTextField birthYearField;
    private String[] countries;
    private JComboBox<String> countryCombo;
    private JFormattedTextField eloRatingField;
    private MaskFormatter eloMask;
    private JTextField fideIdField;
    private JComboBox<ChessTitleEnum> titleCombo;
    private JButton submitButton;
    private DataPanelListener listener;
    private Controller controller;


    public DataPanel(Controller controller) throws ParseException {
        Dimension dims = getPreferredSize();
        dims.width = 390;
        dims.height = 440;
        setPreferredSize(dims);


        this.controller = controller;


        createComp();
        activateComp();
        layoutComp();
        setBorders();
    }

    /**
     * Metoda koja aktivira komponente na panelu
     */
    private void activateComp() {

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameField.getText().equals("") || surnameField.getText().equals("") || eloRatingField.getText().equals("") || fideIdField.getText().equals("") || birthYearField.getText().equals("") || countryCombo.getSelectedIndex() == -1) {
                    JOptionPane.showMessageDialog(DataPanel.this, "All fields must be selected or filled in!",
                            "WARNING", JOptionPane.WARNING_MESSAGE);

                }else if (Integer.parseInt(eloRatingField.getText()) < 1000 || Integer.parseInt(eloRatingField.getText()) > 3000) {
                    JOptionPane.showMessageDialog(DataPanel.this, "Rating must be in range from 1000 to 3000!",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                } else if (Integer.parseInt(birthYearField.getText()) > 2022 || Integer.parseInt(birthYearField.getText()) < 1870){
                    JOptionPane.showMessageDialog(DataPanel.this, "Invalid year!",
                            "WARNING", JOptionPane.WARNING_MESSAGE);
                }else {
                    if (listener != null){
                        int id = controller.getSizeOfChessPlayerList() + 100;
                        String name = nameField.getText();
                        String surname = surnameField.getText();
                        String gender = bg.getSelection().getActionCommand();
                        int birthYear = Integer.parseInt(birthYearField.getText());
                        String country = countryCombo.getModel().getSelectedItem().toString();
                        int eloRating = Integer.parseInt(eloRatingField.getText());
                        int fideId = Integer.parseInt(fideIdField.getText());
                        ChessTitleEnum chessTitle = (ChessTitleEnum) titleCombo.getModel().getSelectedItem();

                        ChessPlayer chessPlayer = new ChessPlayer(id, name, surname, gender, birthYear, country, eloRating, fideId, chessTitle);

                        listener.dataPanelEventOccured(chessPlayer);
                        resetDataPanel();
                    }
                }
            }
        });
    }

    /**
     * Metoda koja resetira tj. brise tekst s polja i vraca ih na pocetno stanje
     */
    private void resetDataPanel() {
        nameField.setText("");
        surnameField.setText("");
        eloRatingField.setValue(null);
        bg.setSelected(maleButton.getModel(), true);
        birthYearField.setValue(null);
        fideIdField.setText("");
        countryCombo.setSelectedIndex(-1);
        titleCombo.setSelectedIndex(0);
        nameField.requestFocus();
    }

    /**
     * Metoda koja postavlja granice panela
     */
    private void setBorders() {
        Border inner = BorderFactory.createTitledBorder("Player Data");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 2);
        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

    /**
     * Metoda koja rasporeduje komponente po panelu
     */
    private void layoutComp() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.weighty = 0.75;
        gbc.weightx = 0.75;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel(""), gbc);
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        gbc.gridy ++;
        add(new JLabel("Name: "), gbc);

        gbc.gridx ++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(nameField, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Surname: "),gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(surnameField, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Gender: "),gbc);
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.gridx++;
        add(maleButton, gbc);
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;

        add(femaleButton, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Birth year: "),gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(birthYearField, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Country: "),gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(countryCombo, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("ELO rating: "),gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(eloRatingField, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("FIDE id: "),gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(fideIdField, gbc);

        gbc.gridx--;
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(new JLabel("Chess title: "),gbc);
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(titleCombo, gbc);

        gbc.gridx--;
        gbc.gridx++;
        gbc.gridx++;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(submitButton, gbc);

        nameField.requestFocus();
    }

    /**
     * Metoda koja inicijalizira i definira komponente
     */
    private void createComp() {
        countries = new String[]{"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegowina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo", "Congo", "Cook Islands", "Costa Rica", "Cote d'Ivoire", "Croatia (Hrvatska)", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "France Metropolitan", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mc Donald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran (Islamic Republic of)", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea, Democratic Republic of", "Korea, Republic of", "Kuwait", "Kyrgyzstan", "Lao, People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova, Republic of", "Monaco", "Mongolia", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Kitts and Nevis", "Saint Lucia", "Saint Vincent and the Grenadines", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia", "Spain", "Sri Lanka", "St. Helena", "St. Pierre and Miquelon", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan, Province of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Vietnam", "Virgin Islands (British)", "Virgin Islands (U.S.)", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Yugoslavia", "Zambia", "Zimbabwe", "Palestine"};
        nameField = new JTextField(10);
        surnameField = new JTextField(10);
        maleButton = new JRadioButton("Male");
        maleButton.setActionCommand("Male");
        femaleButton = new JRadioButton("Female");
        femaleButton.setActionCommand("Female");
        bg = new ButtonGroup();
        bg.add(maleButton);
        bg.add(femaleButton);
        bg.setSelected(maleButton.getModel(), true);
        countryCombo = new JComboBox<String>(countries);
        countryCombo.setSize(10, countryCombo.getPreferredSize().height);
        countryCombo.setPrototypeDisplayValue("XXXXXXXXXXXXXXXXXXXXXXX");

        fideIdField = new JTextField(10);
        titleCombo = new JComboBox<ChessTitleEnum>(ChessTitleEnum.values());
        submitButton = new JButton("Submit");

        try {
            eloMask = new MaskFormatter("####");
            eloMask.setPlaceholderCharacter('_');
        } catch (ParseException e) {
            e.printStackTrace();
        }
        eloRatingField = new JFormattedTextField(eloMask);
        birthYearField = new JFormattedTextField(eloMask);

        countryCombo.setRenderer(new MyComboBoxRenderer("Select country"));
        countryCombo.setSelectedIndex(-1);

    }

    public void setListener(DataPanelListener listener) {
        this.listener = listener;
    }


    /**
     * unutarnja klasa koja postavlja ComboBox selected index na -1 u pocetnom stanju
     */
    class MyComboBoxRenderer extends JLabel implements ListCellRenderer {
        private String _title;

        public MyComboBoxRenderer(String title)
        {
            _title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value,
                                                      int index, boolean isSelected, boolean hasFocus)
        {
            if (index == -1 && value == null) setText(_title);
            else setText(value.toString());
            return this;
        }
    }

}
