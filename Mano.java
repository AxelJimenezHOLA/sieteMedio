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

    public String[] getDirectoriosCartas() {
        String[] directorios = new String[cartas.size()];
        for (int i = 0; i < cartas.size(); i++) {
            directorios[i] = cartas.get(i).obtenerDirectorioImagen();
        }
        return directorios;
    }

    public void quitarCartas() {
        cartas.clear();
    }

    public double sumarValoresCartas() {
        return cartas.stream().flatMapToDouble(carta -> DoubleStream.of(carta.getValor())).sum();
    }
}