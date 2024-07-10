package utn.metodos_agiles.controller;

import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.model.entidades.Contribuyente;

public class ContribuyenteController {

    private static ContribuyenteController instance;

    private ContribuyenteController() {}

    public static ContribuyenteController getInstance() {
        if(instance == null) instance = new ContribuyenteController();
        return instance;
    }


    public Contribuyente obtenerContribuyebte(int dni) {
        return DBManager.getInstance().buscarContribuyentePorDni(dni);
    }
}
