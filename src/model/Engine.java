package model;

public class Engine extends Component {
    private int rpm;
    private final int max_rpm;

    public Engine(String name, double weight, double price, int rpm, int max_rpm) {
        super(name, weight, price);
        this.rpm = rpm;
        this.max_rpm = max_rpm;
    }

    public int getRpm() {
        return rpm;
    }

    public void stop() {
        rpm = 0;
    }

    public void start() {
        rpm = 700;
    }

    public void setRpm(int rpm) {
        if (rpm <= max_rpm && rpm >= 700) {
            this.rpm = rpm;
        } else if (rpm < 700) {
            stop();
        }

    }

    public void increaseRpm() {
        if (rpm + 200 <= max_rpm) {
            rpm = rpm + 200;
        } else if (rpm + 100 <= max_rpm) {
            rpm = rpm + 100;
        }
    }


    public void decreaseRpm() {
        if (rpm < 700) {
            stop();
        } else {
            rpm = rpm - 200;
        }
    }
}
