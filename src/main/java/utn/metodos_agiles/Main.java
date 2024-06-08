package utn.metodos_agiles;

import java.util.Date;
import utn.metodos_agiles.dbmanager.DBManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {
      
    	
    	MenuPrincipal interfaz= new MenuPrincipal();
    	interfaz.setVisible(true);
    	
    }
    
    private static Date calcularVigencia(int edad, int nroLicTit) {
    	Date vig = new Date();
    	int mas = 0;
    	if(edad < 21) {
    		if(nroLicTit == 1) {
    			mas = 1;
    		} else mas = 3;
    	} else if(edad <= 46) {
    		mas = 5;
    	} else if(edad <= 60) {
    		mas = 4;
    	} else if(edad <= 70) {
    		mas = 3;
    	} else if(edad > 70) {
    		mas = 1;
    	}
    	vig.setYear(vig.getYear() + mas);
    	return vig;
    }
    
    private static int calcularAnioVigencia(int edad, int nroLicTit) {
    	int mas = 0;
    	if(edad < 21) {
    		if(nroLicTit == 1) {
    			mas = 1;
    		} else mas = 3;
    	} else if(edad <= 46) {
    		mas = 5;
    	} else if(edad <= 60) {
    		mas = 4;
    	} else if(edad <= 70) {
    		mas = 3;
    	} else if(edad > 70) {
    		mas = 1;
    	}
    	return new Date().getYear() + mas;

    }
}