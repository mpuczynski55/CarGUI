package model;

public class Position {
    private double x;
    private double y;

    public double getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position move(double V, double dt, Position dest) {
        double distance = Math.pow(dest.x - x, 2) + Math.pow(dest.y - y, 2);
        distance = Math.sqrt(distance);
        double dx = V * dt * ((dest.x - x) / distance);
        double dy = V * dt * ((dest.y - y) / distance);
        x = (x + dx);
        y = (y + dy);
        return dest;
    }
}
