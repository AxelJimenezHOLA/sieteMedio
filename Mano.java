import java.util.ArrayList;
import java.util.stream.DoubleStream;

public class Mano {
    private ArrayList<Carta> cartas;

    public Mano() {
        cartas = new ArrayList<>();
    }

    public void agregarCarta(Carta carta) {
        cartas.add(carta);
    }

    public double sumarValoresCartas() {
        return cartas.stream().flatMapToDouble(carta -> DoubleStream.of(carta.getValor())).sum();
    }
}