import java.util.ArrayList;

public class Mesa {
    private ArrayList<Carta> cartas;

    public Mesa() {
        cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public void limpiarMesa() {
        cartas.clear();
    }
}