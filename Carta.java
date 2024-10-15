public class Carta {
    private final int indice;
    private final Palo palo;
    private boolean visible;

    public Carta(int indice, Palo palo, boolean visible) {
        this.indice = indice;
        this.palo = palo;
        this.visible = visible;
    }

    public int getIndice() {
        return indice;
    }

    public double getValor() {
        return switch (indice) {
            case 10, 11, 12, 13 -> 0.5;
            default -> indice;
        };
    }

    public Palo getPalo() {
        return palo;
    }

    public boolean isVisible() {
        return visible;
    }

    public void voltear() {
        visible = !visible;
    }
}