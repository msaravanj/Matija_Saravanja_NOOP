package view;

import model.ChessTitleEnum;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class DataPanel extends JPanel {

    private JTextField nameField;
    private JTextField surnameField;
    private JRadioButton maleButton;
    private JRadioButton femaleButton;
    private ButtonGroup bg;
    private JTextField birthYearField;
    private JTextField countryField;
    private JTextField eloRatingField;
    private JTextField fideIdField;
    private JComboBox<ChessTitleEnum> titleCombo;
    private JButton submitButton;


    public DataPanel(){
        Dimension dims = getPreferredSize();
        dims.width = 390;
        dims.height = 440;
        setPreferredSize(dims);


        createComp();
//        activateComp();
        layoutComp();
        setBorders();
    }

    private void setBorders() {
        Border inner = BorderFactory.createTitledBorder("Player Data");
        Border outer = BorderFactory.createEmptyBorder(5, 5, 5, 2);

        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }

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
        add(countryField, gbc);

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
    }

    private void createComp() {
        nameField = new JTextField(10);
        surnameField = new JTextField(10);
        maleButton = new JRadioButton("Male");
        femaleButton = new JRadioButton("Female");
        bg = new ButtonGroup();
        bg.add(maleButton);
        bg.add(femaleButton);
        bg.setSelected(maleButton.getModel(), true);
        birthYearField = new JTextField(10);
        countryField = new JTextField(10);
        eloRatingField = new JTextField(10);
        fideIdField = new JTextField(10);
        titleCombo = new JComboBox<ChessTitleEnum>(ChessTitleEnum.values());
        submitButton = new JButton("Submit");

    }


}
