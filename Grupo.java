import javax.swing.*;
import java.util.ArrayList;

public class Grupo {
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Jugador ganador;

    public Grupo() {

        jugadores = new ArrayList<>();


        ganador = null;
    }

    public void establecerPrimerJugador() {
        if (!jugadores.isEmpty()) {
            jugadorActual = jugadores.get(0);
        }
    }

    public void reiniciarGrupo() {
        jugadorActual = jugadores.getFirst();
        ganador = null;
        jugadores.forEach(Jugador::limpiarMano);
    }











    public ArrayList<Jugador> getJugadores() {
        return jugadores;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public Jugador getGanador() {
        return ganador;
    }

    public void setGanador(Jugador ganador) {
        this.ganador = ganador;
    }
}