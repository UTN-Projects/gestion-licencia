package utn.metodos_agiles;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        client.name = "Julian Sanchez";
        client.address = "Francia 2139";
        client.dni = "40121348";

        List<Item> items = List.of(new Item("Licencia A1", 350.5F));

        FacturaGenerator.generar(client, items, "document.pdf");
    }
}