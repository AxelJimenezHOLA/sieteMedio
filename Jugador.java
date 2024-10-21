public class Jugador {
    private String nombre;
    private Mano mano;

    public Jugador() {
        nombre = "";
        mano = new Mano();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPuntos() {
        return mano.sumarValoresCartas();
    }

    public boolean excedeSieteYMedio() {
        return getPuntos() > 7.5;
    }

    public void recibirCarta(Carta carta) {
        mano.agregarCarta(carta);
    }

    public void limpiarMano() {
        mano.quitarCartas();
    }

    public String[] getDirectoriosCartas() {
        return mano.getDirectoriosCartas();
    }

    @Override
    public String toString() {
        return nombre;
    }
}