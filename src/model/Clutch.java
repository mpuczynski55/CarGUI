package model;

public class Clutch extends Component {
    private boolean stanSprzegla; // true-wcisniete

    public Clutch(String nazwa, double waga, double cena, boolean stanSprzegla) {
        super(nazwa, waga, cena);
        this.stanSprzegla = stanSprzegla;
    }

    public boolean getStatus() {
        return stanSprzegla;
    }

    public void press() {
        this.stanSprzegla = true;
    }

    public void release() {
        this.stanSprzegla = false;
    }
}
