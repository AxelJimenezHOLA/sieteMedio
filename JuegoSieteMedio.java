import java.util.ArrayList;

public class JuegoSieteMedio {
    private Grupo grupoJugadores;
    private Baraja barajaJuego;
    private boolean terminado = false;
    private boolean hayGanador = false;
    private int cantidadDeJugadores;
    private Jugador jugador;

    public JuegoSieteMedio() {


        grupoJugadores = new Grupo();
        barajaJuego = new Baraja(false);
        darCartasIniciales();
    }

    public void setCantidadDeJugadores(int cantidadDeJugadores) {
        this.cantidadDeJugadores = cantidadDeJugadores;
    }

    public int getCantidadDeJugadores() {
        return cantidadDeJugadores;
    }

    public void darCarta() {
        grupoJugadores.getJugadorActual().recibirCarta(barajaJuego.retirarCarta());
    }

    public void plantarTurno() {
        ArrayList<Jugador> jugadores = grupoJugadores.getJugadores();
        Jugador jugadorActual = grupoJugadores.getJugadorActual();
        if (jugadorActual.equals(jugadores.getLast())) {
            declararGanador();
            terminado = true;
        } else {
            grupoJugadores.setJugadorActual(jugadores.get(jugadores.indexOf(jugadorActual)+1));
        }
    }

    public boolean isTerminado() {
        return terminado;
    }

    public String getNombreJugadorActual() {
        return grupoJugadores.getJugadorActual().toString();
    }

    public String getNombreGanador() {
        return grupoJugadores.getGanador().toString();
    }

    public double getPuntosJugadorActual() {
        return grupoJugadores.getJugadorActual().getPuntos();
    }

    public Grupo getGrupoJugadores() {
        return grupoJugadores;
    }

    public String[] getDirectoriosCartas() {
        return grupoJugadores.getJugadorActual().getDirectoriosCartas();

    }
    public void crearJugadores() {
        for (int i = 0; i < cantidadDeJugadores; i++) {
            grupoJugadores.getJugadores().add(new Jugador());

        }
        grupoJugadores.establecerPrimerJugador();
    }

    public boolean excedioPuntosJugadorActual() {
        return grupoJugadores.getJugadorActual().excedeSieteYMedio();
    }

    private void darCartasIniciales() {
        grupoJugadores.getJugadores().forEach(jugador -> jugador.recibirCarta(barajaJuego.retirarCarta()));
    }



    private void declararGanador() {
        double puntajeMaximo = 0;
        for (Jugador jugador : grupoJugadores.getJugadores()) {
            double puntajeJugador = jugador.getPuntos();
            if (!jugador.excedeSieteYMedio() && puntajeJugador >= puntajeMaximo) {
                puntajeMaximo = puntajeJugador;
                grupoJugadores.setGanador(jugador);
                hayGanador = true;
            }
        }
    }

    public void reiniciarJuego() {
        grupoJugadores.reiniciarGrupo();
        barajaJuego.reiniciarBaraja();
        terminado = false;
        hayGanador = false;
        darCartasIniciales();
    }

    public boolean hayGanador() {
        return hayGanador;
    }

    public Jugador getJugador() {

        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}