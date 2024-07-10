package utn.metodos_agiles.view.guardartitular;

import utn.metodos_agiles.model.entidades.Contribuyente;
import utn.metodos_agiles.model.entidades.Titular;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class GuardarTitularTableModel extends AbstractTableModel {

    private static final String[] COLUM_NAMES = {
            "DNI", "NOMBRE", "APELLIDO"
    };

    private List<Contribuyente> contribuyentes;

    public GuardarTitularTableModel(List<Contribuyente> contribuyentes) {
        this.contribuyentes = contribuyentes;
    }


    @Override
    public int getRowCount() {
        return contribuyentes.size();
    }

    @Override
    public int getColumnCount() {
        return COLUM_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Contribuyente contribuyente = contribuyentes.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> contribuyente.getDni();
            case 1 -> contribuyente.getNombre();
            case 2 -> contribuyente.getApellido();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUM_NAMES[column];
    }
}
