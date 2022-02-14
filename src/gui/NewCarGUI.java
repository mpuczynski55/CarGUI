package gui;

import model.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewCarGUI extends JFrame {
    private JPanel main_jpanel;
    private JTextField no_plate_tf;
    private JTextField model_tf;
    private JTextField brand_tf;
    private JTextField gearbox_tf;
    private JTextField top_speed_tf;
    private JTextField engine_tf;
    private JButton add_btn;
    private JButton cancel_btn;
    private JRadioButton manualRadioButton;
    private JRadioButton automaticRadioButton;
    private JRadioButton petrolRadioButton;
    private JRadioButton dieselRadioButton;
    private ButtonGroup gearbox_chkboxes;
    private ButtonGroup engine_chkboxes;

    public NewCarGUI(JComboBox comboBox) {
        setContentPane(main_jpanel);
        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Car obj1 = new Car(false, no_plate_tf.getText(), model_tf.getText(), Double.parseDouble(top_speed_tf.getText()), 0.363,
                            new Position(30, 30),
                            new Gearbox(gearbox_chkboxes.getSelection().getActionCommand() + " " + gearbox_tf.getText(),
                                    63.2, 3100, 0, 8, 0,
                                    new double[]{4.7, 3.0, 2.0, 1.72, 1.11, 1.0, 0.62, 0.60, 3.01},
                                    new Clutch("Sprzeglo", 18.2, 2490.99, false)),
                            new Engine(engine_chkboxes.getSelection().getActionCommand() + " " + engine_tf.getText(),
                                    159.3, 20000, 0, 5400));
                    comboBox.addItem(obj1);
                    dispose();
                } catch (NumberFormatException n) {
                    JFrame f = new ErrorGUI();
                    f.pack();
                    f.setVisible(true);
                }

            }
        });
        cancel_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
