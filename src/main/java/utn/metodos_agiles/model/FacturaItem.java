package utn.metodos_agiles.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacturaItem {
    public String description;
    public Float value;

    public FacturaItem(String description, Float value) {
        this.description = description;
        this.value = value;
    }
}
