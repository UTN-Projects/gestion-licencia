package utn.metodos_agiles.view;


import utn.metodos_agiles.entidades.Licencia;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LicenciasExpiradasTableModel extends AbstractTableModel {

    private static final String[] COLUM_NAMES = {
            "DNI", "NOMBRE", "APELLIDO", "TIPO DE LICENCIA", "CADUCIDAD"
    };

    private List<Licencia> licencias;

    public LicenciasExpiradasTableModel(List<Licencia> licencias) {
        this.licencias = licencias;
    }

    @Override
    public int getRowCount() {
        return licencias.size();
    }

    @Override
    public int getColumnCount() {
        return COLUM_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Licencia licencia = licencias.get(rowIndex);

        return switch (columnIndex) {
            case 0 -> licencia.getTitular().getDni();
            case 1 -> licencia.getTitular().getNombre();
            case 2 -> licencia.getTitular().getApellido();
            case 3 -> licencia.getTipo();
            case 4 -> licencia.getFechaVencimiento();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUM_NAMES[column];
    }
}
