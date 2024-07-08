package utn.metodos_agiles.entidades;

public enum RH {
    POSITIVO, NEGATIVO;

    public String toString() {
        return switch (this) {
            case NEGATIVO -> "-";
            case POSITIVO -> "+";
        };
    }
}
