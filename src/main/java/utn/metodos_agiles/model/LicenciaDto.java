package utn.metodos_agiles.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LicenciaDto {
    public String number;
    public String lastname;
    public String name;
    public String address;
    public String birth;
    public String emition;
    public String expiration;
    public String licencia;
    public boolean isDonor;
    public String bloodType;
    public String cuil;
    public String observations;
    public String type;

}
