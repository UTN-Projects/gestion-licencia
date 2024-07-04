package utn.metodos_agiles.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientDto {
    public String name;
    public String address;
    public String dni;
}


