import java.util.ArrayList;

public class Grupo {
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Jugador ganadorRonda;
    private Jugador ganadorJuego;

    public Grupo(int cantidadJugadores) {
        jugadores = new ArrayList<>(cantidadJugadores);
        for (int i = 0; i < cantidadJugadores; i++) {
            jugadores.add(new Jugador());
        }
        jugadorActual = jugadores.getFirst();
        ganadorRonda = null;
        ganadorJuego = null;
    }

    public Jugador getJugadorActual() {
        return jugadorActual;
    }

    public void setJugadorActual(Jugador jugadorActual) {
        this.jugadorActual = jugadorActual;
    }

    public Jugador getGanadorRonda() {
        return ganadorRonda;
    }

    public void setGanadorRonda(Jugador ganadorRonda) {
        this.ganadorRonda = ganadorRonda;
    }

    public Jugador getGanadorJuego() {
        return ganadorJuego;
    }

    public void setGanadorJuego(Jugador ganadorJuego) {
        this.ganadorJuego = ganadorJuego;
    }


}