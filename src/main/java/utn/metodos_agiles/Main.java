package utn.metodos_agiles;

import utn.metodos_agiles.model.ClientDto;
import utn.metodos_agiles.model.FacturaItem;
import utn.metodos_agiles.model.LicenciaDto;
import utn.metodos_agiles.util.LicenciaGenerator;
import utn.metodos_agiles.view.MenuPrincipal;

import java.util.List;

public class Main {

    public static void main(String[] args) {
		MenuPrincipal interfaz= new MenuPrincipal();
		interfaz.setVisible(true);

        /*
        ClientDto client = new ClientDto();
        client.name = "ANA VICTORIA";
        client.address = "Mitre 1234";
        client.dni = "40123456";

        List<FacturaItem> items = List.of(new FacturaItem("Licencia A1", 350.5F));

        LicenciaDto licenciaDto = new LicenciaDto();
        licenciaDto.number = "99999999";
        licenciaDto.name = "ANA VICTORIA";
        licenciaDto.lastname = "GUEVARA";
        licenciaDto.address = "MITRE 62 - 6A - LA ESMERALDA";
        licenciaDto.birth = "21 AGO 1985";
        licenciaDto.emition = "14 AGO 2016";
        licenciaDto.expiration = "14 AGO 2016";
        licenciaDto.licencia = "B1";
        licenciaDto.bloodType = "A+";
        licenciaDto.cuil = "20401234562";
        licenciaDto.isDonor = true;
        licenciaDto.observations = "";


        //FacturaGenerator.generar(client, items, "document.pdf");
        LicenciaGenerator.generar(licenciaDto, "src/main/resources/licencia_sprites/foto.png","licencia.pdf");

         */
    }
}