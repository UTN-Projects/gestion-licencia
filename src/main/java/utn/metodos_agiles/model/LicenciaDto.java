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
    
    public static String traductorMes(int mes) {
    	switch(mes) {
    	case 0:
    		return "ENE";
    	case 1:
    		return "FEB";
    	case 2:
    		return "MAR";
    	case 3:
    		return "ABR";
    	case 4:
    		return "MAY";
    	case 5:
    		return "JUN";
    	case 6:
    		return "JUL";
    	case 7:
    		return "AGO";
    	case 8:
    		return "SEP";
    	case 9:
    		return "OCT";
    	case 10:
    		return "NOV";
    	case 11:
    		return "DIC";
    			
    	}
    	return "";
    }
}
