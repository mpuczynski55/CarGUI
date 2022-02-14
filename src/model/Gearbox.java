package model;

public class Gearbox extends Component {
    private int current_gear;
    private final int gear_num;
    private double current_gear_ratio;
    private final double[] gears_ratios;
    private Clutch clutch;

    public Gearbox(String name, double weight, double price, int current_gear, int gear_num, double current_gear_ratio,
                   double[] gears_ratios, Clutch clutch) {
        super(name, weight, price);
        this.current_gear = current_gear;
        this.gear_num = gear_num;
        this.current_gear_ratio = current_gear_ratio;
        this.gears_ratios = gears_ratios;
        this.clutch = clutch;
    }

    public void setCurrent_gear(int current_gear) {
        this.current_gear = current_gear;
        if (!(current_gear == 0)) {
            setCurrent_gear_ratio();
        }
    }

    public Clutch getClutch() {
        return clutch;
    }

    public int getCurrentGear() {
        return current_gear;
    }

    public void setCurrent_gear_ratio() {
        current_gear_ratio = gears_ratios[current_gear - 1];
    }

    public double getCurrentGearRatio() {
        return current_gear_ratio;
    }

    public void gearUp() {
        if (current_gear < gear_num && current_gear >= 0) {
            clutch.press();
            current_gear++;
            setCurrent_gear_ratio();
            clutch.release();
        }
    }

    public void gearDown() {
        if (0 < current_gear && current_gear <= gear_num) {
            clutch.press();
            current_gear--;
            if(current_gear!=0){
                setCurrent_gear_ratio();
            }
            clutch.release();
        }
    }

    public double getFinalDriveRatio() {
        return gears_ratios[gears_ratios.length - 1];
    }

    public double getClutchWeight() {
        return clutch.getWeight();
    }
}