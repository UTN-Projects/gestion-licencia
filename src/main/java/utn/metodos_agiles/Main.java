package utn.metodos_agiles;

import java.util.List;

public class Main {

    public static void main(String[] args) {
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
    }
}