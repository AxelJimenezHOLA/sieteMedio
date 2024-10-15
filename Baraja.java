import java.util.ArrayList;
import java.util.Random;

public class Baraja {
    private final ArrayList<Carta> cartas;
    private final boolean contiene8y9;

    public Baraja(boolean contiene8y9) {
        cartas = new ArrayList<>(40);
        this.contiene8y9 = contiene8y9;
        crearCartas(contiene8y9);
    }

    private void crearCartas(boolean contiene8y9) {
        for (Palo palo : Palo.values()) {
            if (contiene8y9) {
                for (int i = 1; i <= 12; i++) {
                    cartas.add(new Carta(i, palo, true));
                }
            } else {
                for (int i = 1; i <= 7; i++) {
                    cartas.add(new Carta(i, palo, true));
                }
                for (int i = 10; i <= 12; i++) {
                    cartas.add(new Carta(i, palo, true));
                }
            }
        }
    }

    public void reiniciarBaraja() {
        cartas.clear();
        crearCartas(contiene8y9);
    }

    public Carta retirarCarta() {
        return cartas.remove(new Random().nextInt(cartas.size()));
    }
}