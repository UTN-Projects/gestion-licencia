package utn.metodos_agiles.view.emitirlicencia;

import utn.metodos_agiles.model.entidades.Contribuyente;
import utn.metodos_agiles.model.entidades.Titular;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmitirLicenciaTableModel extends AbstractTableModel {

    private static final String[] COLUM_NAMES = {
            "DNI", "NOMBRE", "APELLIDO"
    };

    private List<Titular> titulares;

    public EmitirLicenciaTableModel(List<Titular> titulares) {
        this.titulares = titulares;
    }


    @Override
    public int getRowCount() {
        return titulares.size();
    }

    @Override
    public int getColumnCount() {
        return COLUM_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Titular titular = titulares.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> titular.getDni();
            case 1 -> titular.getNombre();
            case 2 -> titular.getApellido();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUM_NAMES[column];
    }
}
