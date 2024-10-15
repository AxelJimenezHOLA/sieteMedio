public class Jugador {
    private String nombre;
    private Mano mano;

    public Jugador() {
        nombre = nombrarJugador();
        mano = new Mano();
    }

    public double getPuntos() {
        return mano.sumarValoresCartas();
    }

    public void recibirCarta(Carta carta) {
        mano.agregarCarta(carta);
    }

    private String nombrarJugador() {
        return "TEST NAME";
    }

    @Override
    public String toString() {
        return nombre;
    }
}