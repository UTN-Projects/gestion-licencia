package utn.metodos_agiles;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
		MenuPrincipal interfaz= new MenuPrincipal();
		interfaz.setVisible(true);

		/*
        Client client = new Client();
        client.name = "ANA VICTORIA";
        client.address = "Mitre 1234";
        client.dni = "40123456";

        List<Item> items = List.of(new Item("Licencia A1", 350.5F));

        LicenciaDto licenciaDto = new LicenciaDto();
        licenciaDto.number = "99999999";
        licenciaDto.name = "ANA VICTORIA";
        licenciaDto.lastname = "GUEVARA";
        licenciaDto.address = "MITRE 62 - 6A - LA ESMERALDA";
        licenciaDto.birth = "21 AGO 1985";
        licenciaDto.emition = "14 AGO 2016";
        licenciaDto.expiration = "14 AGO 2016";


        //FacturaGenerator.generar(client, items, "document.pdf");
        LicenciaGenerator.generar(licenciaDto, "src/main/resources/licencia_sprites/foto.png","licencia.pdf");
		 */
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