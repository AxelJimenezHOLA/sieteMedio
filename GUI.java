import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JFrame {

    private JPanel panelPrincipal;
    private JButton agarrarCartaButton;
    private JButton plantarseButton;
    private JPanel panelBotones;
    private JPanel panelInfo;
    private JLabel turnoLabel;
    private JLabel puntosLabel;
    private JPanel panelCartas;
    private JuegoSieteMedio juego;


    public GUI() {
        setTitle("Siete y Medio");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panelPrincipal);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);

        agarrarCartaButton.addActionListener(_ -> darCarta());
        plantarseButton.addActionListener(_ -> plantarTurno());
        juego = new JuegoSieteMedio();
        juego.setCantidadDeJugadores(ingresarCantidadJugadores());
        juego.crearJugadores();  // Crear los jugadores
        nombrarJugadores(juego.getCantidadDeJugadores());

        actualizarGUI();
        setVisible(true);



    }

    private void darCarta() {
        juego.darCarta();
        actualizarGUI();
        if (juego.excedioPuntosJugadorActual()) {
            JOptionPane.showMessageDialog(
                    null,
                    "El jugador " +juego.getNombreJugadorActual()+ " se excedió de siete y medio...",
                    "Excediste Siete y Medio.",
                    JOptionPane.INFORMATION_MESSAGE
            );
            plantarTurno();
        }
    }

    private void plantarTurno() {
        juego.plantarTurno();
        actualizarGUI();
        if (juego.isTerminado()) {
            JOptionPane.showMessageDialog(
                    null,
                    "¡El último jugador ha terminado su turno!",
                    "Se acabó el juego",
                    JOptionPane.INFORMATION_MESSAGE
            );
            mostrarPuntajes();
            mostrarGanador();
            mostrarMensajeReinicio();
        }
    }

    private void mostrarPuntajes() {
        StringBuilder stringBuilder = new StringBuilder("Tabla de puntajes:\n");
        for (Jugador jugador : juego.getGrupoJugadores().getJugadores()) {
            stringBuilder.append("Puntos de " + jugador + ": " + jugador.getPuntos() + "\n");
        }
        JOptionPane.showMessageDialog(
                null,
                stringBuilder,
                "¡Puntos de todos!",
                JOptionPane.PLAIN_MESSAGE
        );
    }

    private void mostrarGanador() {
        if (juego.hayGanador()) {
            JOptionPane.showMessageDialog(
                    null,
                    "¡El ganador es "+juego.getNombreGanador()+"!",
                    "El ganador de este juego",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Nadie ganó el juego",
                    "Todos perdieron...",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void mostrarMensajeReinicio() {
        int opcionReinicio = JOptionPane.showConfirmDialog(
                null,
                "¿Quieres volver a jugar?",
                "Reiniciar juego",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcionReinicio == JOptionPane.YES_OPTION) {
            juego.reiniciarJuego();
            actualizarGUI();
        } else {
            System.exit(0);
        }
    }

    private void actualizarGUI() {
        ImageIcon imagenCarta;
        panelCartas.removeAll();
        turnoLabel.setText("Turno de " + juego.getNombreJugadorActual());
        puntosLabel.setText("Total de puntos: " + juego.getPuntosJugadorActual());
        for (String directorio : juego.getDirectoriosCartas()) {
            imagenCarta = new ImageIcon(directorio);
            panelCartas.add(new JLabel(imagenCarta));
        }
        panelCartas.revalidate();
        panelCartas.repaint();
        pack();
        setLocationRelativeTo(null);
    }

    private int ingresarCantidadJugadores() {

        int cantidadJugadoresElegida = juego.getCantidadDeJugadores();
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

    public void nombrarJugadores( int cantidadJugadores) {
        ArrayList<Jugador> jugadores = juego.getGrupoJugadores().getJugadores();
        for (int i = 0; i < cantidadJugadores; i++) {
            nombrarJugador(jugadores.get(i),i);
        }
    }

}