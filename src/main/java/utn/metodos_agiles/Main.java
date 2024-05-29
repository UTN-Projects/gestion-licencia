package utn.metodos_agiles;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            System.out.println("i = " + i);
        }
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