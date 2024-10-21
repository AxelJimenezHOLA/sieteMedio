import javax.swing.*;

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
}