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
    	case 1:
    		return "ENE";
    	case 2:
    		return "FEB";
    	case 3:
    		return "MAR";
    	case 4:
    		return "ABR";
    	case 5:
    		return "MAY";
    	case 6:
    		return "JUN";
    	case 7:
    		return "JUL";
    	case 8:
    		return "AGO";
    	case 9:
    		return "SEP";
    	case 10:
    		return "OCT";
    	case 11:
    		return "NOV";
    	case 12:
    		return "DIC";
    			
    	}
    	return "";
    }
}
