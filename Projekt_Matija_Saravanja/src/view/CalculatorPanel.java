package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class CalculatorPanel extends JPanel {

    private JTextField yourRatingField;
    private JTextField oppRatingField;
    private JRadioButton wonRadio;
    private JRadioButton drawRadio;
    private JRadioButton lostRadio;
    private ButtonGroup bg2;
    private JComboBox<Integer> koefCombo;
    private JButton calcButton;
    private JTextField resultField;

    private JButton loadYrsRtgBtn;
    private JButton loadOppRtgBtn;


    public CalculatorPanel(){

        Dimension dims = getPreferredSize();
        dims.width = 390;
        dims.height = 350;
        setPreferredSize(dims);


        createComps();
//        activateComp();
        layoutComp();
        setBorders();
    }

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

        gbc.gridx++;
        gbc.gridy--;
        add(loadYrsRtgBtn, gbc);
        gbc.gridy++;
        add(loadOppRtgBtn, gbc);

        gbc.gridy++;

        gbc.gridx--;
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
        //gbc.gridx++;
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

    private void setBorders() {
        Border inner = BorderFactory.createTitledBorder("Rating Calculator");
        Border outer = BorderFactory.createEmptyBorder(5, 2, 5, 5);

        setBorder(BorderFactory.createCompoundBorder(outer, inner));
    }


    private void createComps(){
        yourRatingField = new JTextField(10);
        oppRatingField = new JTextField(10);
        wonRadio = new JRadioButton("won");
        drawRadio = new JRadioButton("draw");
        lostRadio = new JRadioButton("lost");
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
        loadYrsRtgBtn = new JButton("DB");
        loadOppRtgBtn = new JButton("DB");

        resultField.setEnabled(false);
    }



}
