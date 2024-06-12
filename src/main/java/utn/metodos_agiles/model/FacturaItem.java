package utn.metodos_agiles.model;

public class FacturaItem {
    public String description;
    public Float value;

    public FacturaItem(String description, Float value) {
        this.description = description;
        this.value = value;
    }
}
