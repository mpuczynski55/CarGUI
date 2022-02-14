package gui;

import model.Car;
import model.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

public class CarGUI extends Thread {
    private JPanel main_jpanel;
    private JTextField car_model_tf;
    private JTextField car_numberplate_tf;
    private JTextField car_weight_tf;
    private JTextField car_speed_tf;
    private JButton turn_on_btn;
    private JButton turn_off_btn;
    private JTextField gearbox_name_tf;
    private JTextField gearbox_price_tf;
    private JTextField gearbox_weight_tf;
    private JTextField gearbox_gear_tf;
    private JTextField clutch_name_tf;
    private JTextField clutch_price_tf;
    private JTextField clutch_weight_tf;
    private JTextField clutch_status_tf;
    private JButton gear_up_btn;
    private JButton gear_down_btn;
    private JButton press_clutch_btn;
    private JButton release_clutch_btn;
    private JComboBox car_select_cmbox;
    private JButton add_new_btn;
    private JButton delete_btn;
    private JTextField engine_name_tf;
    private JTextField engine_price_tf;
    private JTextField engine_weight_tf;
    private JTextField engine_rpm_tf;
    private JButton speed_up_btn;
    private JButton slow_down_btn;
    private JPanel map_jpanel;
    private JLabel car_icon;
    private Car car;
    private DecimalFormat df;
    private Thread gui_thread;

    public CarGUI() {
        actions();
        df = new DecimalFormat();
        df.setMinimumFractionDigits(0);
        df.setMaximumFractionDigits(2);
        gui_thread = new Thread(this);
        gui_thread.start();
    }

    @Override
    public void run() {
        while (true) {
            refresh();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public void actions() {
        turn_on_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                    car.turnOn();
                }
            }
        });
        turn_off_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                car.turnOff();
            }}
        });
        gear_up_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                car.getGearbox().gearUp();
            }}
        });
        gear_down_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                car.getGearbox().gearDown();
            }}
        });
        press_clutch_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                car.getGearbox().getClutch().press();
            }}
        });
        release_clutch_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                car.getGearbox().getClutch().release();
            }}
        });
        speed_up_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                car.getEngine().increaseRpm();
            }}
        });
        slow_down_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                car.getEngine().decreaseRpm();
            }}
        });
        map_jpanel.addMouseListener(new MouseListener() {


            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (car != null) {
                car.driveTo(new Position(e.getX(), e.getY()));
            }}

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add_new_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame f = new NewCarGUI(car_select_cmbox);
                f.pack();
                f.setVisible(true);
            }
        });
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (car != null) {
                    car.getThread().interrupt();
                    car_select_cmbox.removeItem(car);
                }
            }
        });
        car_select_cmbox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                car = (Car) car_select_cmbox.getSelectedItem();
            }
        });
    }


    public void refresh() {
        if (car == null) {
            car_icon.setVisible(false);
            car_model_tf.setText("");
            car_numberplate_tf.setText("");
            car_speed_tf.setText("");
            car_weight_tf.setText("");
            gearbox_name_tf.setText("");
            gearbox_price_tf.setText("");
            gearbox_weight_tf.setText("");
            gearbox_gear_tf.setText("");
            engine_name_tf.setText("");
            engine_price_tf.setText("");
            engine_weight_tf.setText("");
            engine_rpm_tf.setText("");
            clutch_name_tf.setText("");
            clutch_price_tf.setText("");
            clutch_weight_tf.setText("");
            clutch_status_tf.setText("");
        } else {
            car_icon.setVisible(true);
            car_model_tf.setText(car.getModel());
            car_numberplate_tf.setText(car.getNumber_plate());
            car_speed_tf.setText(df.format(car.getCurrentSpeed()));
            car_weight_tf.setText(df.format(car.getWeight()));
            gearbox_name_tf.setText(car.getGearbox().getName());
            gearbox_price_tf.setText(df.format(car.getGearbox().getPrice()));
            gearbox_weight_tf.setText(df.format(car.getGearbox().getWeight()));
            gearbox_gear_tf.setText(String.valueOf((car.getGearbox().getCurrentGear())));
            engine_name_tf.setText(car.getEngine().getName());
            engine_price_tf.setText(df.format(car.getEngine().getPrice()));
            engine_weight_tf.setText(df.format(car.getEngine().getWeight()));
            engine_rpm_tf.setText(String.valueOf((car.getEngine().getRpm())));
            clutch_name_tf.setText(car.getGearbox().getClutch().getName());
            clutch_price_tf.setText(df.format(car.getGearbox().getClutch().getPrice()));
            clutch_weight_tf.setText(df.format(car.getGearbox().getClutch().getWeight()));
            clutch_status_tf.setText(String.valueOf(car.getGearbox().getClutch().getStatus()));
            car_icon.setLocation((int) car.getCurrentPosition().getX() - 21, (int) car.getCurrentPosition().getY() - 21);
        }
    }

    public static void main(String[] args) {
        CarGUI gui = new CarGUI();
        JFrame frame = new JFrame("gui.CarGUI");
        frame.setContentPane(gui.main_jpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
