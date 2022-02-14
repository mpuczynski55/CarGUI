package model;

import java.util.Scanner;

public class Car extends Thread {
    private boolean ignition;
    private String number_plate;
    private final String model;
    private final double top_speed;
    private final double wheel_radius;
    private Position current_position;
    private Position destination;
    private Gearbox gearbox;
    private Engine engine;
    private Thread car_thread;

    public Car(boolean ignition, String number_plate, String model, double top_speed, double wheel_radius, Position current_position,
               Gearbox gearbox, Engine engine) {
        this.ignition = ignition;
        this.number_plate = number_plate;
        this.model = model;
        this.top_speed = top_speed;
        this.wheel_radius = wheel_radius;
        this.current_position = current_position;
        this.gearbox = gearbox;
        this.engine = engine;
        car_thread = new Thread(this);

    }
    public Thread getThread(){
        return car_thread;
    }

    public boolean isIgnition() {
        return ignition;
    }

    public String getNumber_plate() {
        return number_plate;
    }

    public String getModel() {
        return model;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public Engine getEngine() {
        return engine;
    }

    public void turnOn() {
        if (engine.getRpm() == 0) {
            engine.start();
            ignition = true;
        }
    }

    public void turnOff() {
        if (engine.getRpm() != 0) {
            engine.stop();
            gearbox.setCurrent_gear(0);
            ignition = false;
        }
    }

    @Override
    public void run() {
        while (true) {
            if(engine.getRpm()==0){
                synchronized (this){
                    try{
                        wait();
                    }catch (InterruptedException e){
                        break;
                    }
                }
            }else{
            System.out.println("moving");
            current_position.move(getCurrentSpeed(), 0.1, destination);
            if (Math.abs(destination.getX() - current_position.getX()) <= 50 && Math.abs(destination.getY() - current_position.getY()) <= 50) {
                gearbox.setCurrent_gear(2);
                engine.setRpm(2500);
                if (Math.abs(current_position.getX() - destination.getX()) <= 2 && Math.abs(current_position.getY() - destination.getY()) <= 2) {
                    System.out.println("finished");
                    synchronized (this) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            break;
                        }
                    }
                }
            }}

            System.out.println(current_position.getX());
            System.out.println(current_position.getY());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    @Override
    public String toString() {
        return number_plate + "  " + model;
    }

    public double getWeight() {
        return gearbox.getWeight() + gearbox.getClutchWeight() + engine.getWeight();
    }

    public double getCurrentSpeed() {
        if (engine.getRpm() < 700) {
            engine.setRpm(0);
            return 0.0;
        } else {
            double speed = (3.6 * engine.getRpm() * Math.PI * wheel_radius);
            speed = speed / (30 * gearbox.getCurrentGearRatio() * gearbox.getFinalDriveRatio());
            return speed;
        }
    }

    public Position getCurrentPosition() {
        return current_position;

    }


    public void driveTo(Position destination) {
        if (!ignition || gearbox.getCurrentGear() == 0) {
            return;
        }
        synchronized (this) {
            State state=car_thread.getState();
            if (state.equals(State.NEW)) {
                car_thread.start();
            } else if (state.equals(State.WAITING)) {
                notify();
            }
        }
        this.destination = destination;
    }


    public static void main(String[] args) {
        Car obj1 = new Car(true, "KR1231D", "Giulia", 307.4, 0.363,
                new Position(0.0, 0.0),
                new Gearbox("8HP", 93.2, 9100, 5, 8, 5.0,
                        new double[]{5.0, 3.2, 2.14, 1.72, 1.31, 1.0, 0.82, 0.64, 3.09},
                        new Clutch("Sprzeglo1", 10.2, 1490.99, false)),
                new Engine("V6 AT8", 149.3, 20000, 9000, 7400));
        Car obj2 = new Car(true, "KR12S2A", "Panda", 180.0, 0.28,
                new Position(5, 1),
                new Gearbox("CVT", 43.2, 3100, 3, 5, 0.97,
                        new double[]{4.1, 2.16, 1.34, 0.97, 0.77, 3.15},
                        new Clutch("Sprzeglo2", 6.1, 690.99, true)),
                new Engine("S63", 59.3, 5000, 3000, 6500));
        obj1.getCurrentSpeed();
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int y = input.nextInt();
        Position dest = new Position(x, y);
        obj1.driveTo(dest);
    }
}


