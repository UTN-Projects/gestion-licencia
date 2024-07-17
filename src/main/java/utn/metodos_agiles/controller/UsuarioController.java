package utn.metodos_agiles.controller;

import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.model.entidades.Usuario;

public class UsuarioController {

    private static UsuarioController instance;

    private UsuarioController() {}

    public static UsuarioController getInstance() {
        if(instance == null) instance = new UsuarioController();
        return instance;
    }

    public Usuario buscarUsuario(String username) {
        return DBManager.getInstance().buscarUser(username);
    }

    public void saveUser(Usuario usuario) {
        DBManager.getInstance().cargarUsuario(usuario);
    }

    public void updateUser(Usuario usuario) {
        DBManager.getInstance().actualizarUsuario(usuario);
    }

    public Usuario verificarLogin(String usuario, String contrasenia) {
        return DBManager.getInstance().verificarLogin(usuario, contrasenia);
    }
}
