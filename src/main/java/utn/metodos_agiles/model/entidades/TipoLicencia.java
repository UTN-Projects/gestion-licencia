package utn.metodos_agiles.model.entidades;

public enum TipoLicencia {
    ORIGINAL, DUPLICADO, TRIPLICADO, CUADRUPLICADO, QUINTUPLICADO, SEXTUPLICADO, OCTUPLICADO;

    public static TipoLicencia fromNumber(int number) {
        return TipoLicencia.values()[number-1];
    }

    public int toNumber() {
        return this.ordinal() + 1;
    }
}
