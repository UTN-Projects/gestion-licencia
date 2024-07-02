package entidades;

public enum GrupoSaguineo {
    A,B,CERO;

    @Override
    public String toString() {
        if(this == GrupoSaguineo.CERO) return "0";
        return this.name();
    }
}
