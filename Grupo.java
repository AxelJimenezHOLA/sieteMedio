import javax.swing.*;
import java.util.ArrayList;

public class Grupo {
    private ArrayList<Jugador> jugadores;
    private Jugador jugadorActual;
    private Jugador ganador;

    public Grupo() {
        int cantidadJugadores = ingresarCantidadJugadores();
        jugadores = new ArrayList<>(cantidadJugadores);
        crearJugadores(cantidadJugadores);
        nombrarJugadores();
        jugadorActual = jugadores.getFirst();
        ganador = null;
    }

    public void reiniciarGrupo() {
        jugadorActual = jugadores.getFirst();
        ganador = null;
        jugadores.forEach(Jugador::limpiarMano);
    }

    private int ingresarCantidadJugadores() {
        int cantidadJugadoresElegida = 0;
        boolean cantidadValida;
        boolean esUnNumero;
        do {
            try {
                cantidadJugadoresElegida = Integer.parseInt(JOptionPane.showInputDialog(
                        null,
                        "Elige la cantidad de jugadores",
                        "Eligiendo cantidad de jugadores",
                        JOptionPane.QUESTION_MESSAGE
                ));
                esUnNumero = true;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, ingrese un número.",
                        "No ingresaste un número...",
                        JOptionPane.ERROR_MESSAGE
                );
                esUnNumero = false;
            }
            if ((cantidadJugadoresElegida < 2 || cantidadJugadoresElegida > 6) && esUnNumero) {
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, ingrese una cantidad de jugadores entre 2 y 6.",
                        "Ingresa un número en el intervalo",
                        JOptionPane.ERROR_MESSAGE
                );
                cantidadValida = false;
            } else {
                cantidadValida = true;
            }
        } while (!cantidadValida || !esUnNumero);
        return cantidadJugadoresElegida;
    }

    private void crearJugadores(int cantidadJugadores) {
        for (int i = 0; i < cantidadJugadores; i++) {
            jugadores.add(new Jugador());
        }
    }

    private void nombrarJugador(Jugador jugadorANombrar, int indiceJugador) {
        String nombreElegido;
        do {
            nombreElegido = JOptionPane.showInputDialog(
                    null,
                    "Jugador " + (indiceJugador + 1) + ", ¿cuál es tu nombre?",
                    "El nombre del jugador " + (indiceJugador + 1),
                    JOptionPane.QUESTION_MESSAGE
            );

            if (noEsNombreValido(nombreElegido)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Por favor, ingrese un nombre válido.",
                        "Nombre no válido.",
                        JOptionPane.ERROR_MESSAGE
                );
            } else {
                jugadorANombrar.setNombre(nombreElegido);
            }
        } while (noEsNombreValido(nombreElegido));
    }

    private boolean noEsNombreValido(String nombre) {
        if (nombre == null) return true;
        if (nombre.isEmpty()) return true;
        return false;
    }

    private void nombrarJugadores() {
        for (int i = 0; i < jugadores.size(); i++) {
            nombrarJugador(jugadores.get(i), i);
        }
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