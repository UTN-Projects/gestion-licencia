package utn.metodos_agiles.view.modificarusuario;

import utn.metodos_agiles.model.entidades.Usuario;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class ModificarUsuarioTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = {
            "NOMBRE USUARIO", "NOMBRE", "APELLIDO", "CORREO", "TELEFONO"
    };

    private List<Usuario> usuarios;

    public ModificarUsuarioTableModel(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public int getRowCount() {
        return usuarios.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Usuario user = usuarios.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> user.getNombreUsuario();
            case 1 -> user.getNombre();
            case 2 -> user.getApellido();
            case 3 -> user.getCorreoElectronico();
            case 4 -> user.getTelefono();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }
}
