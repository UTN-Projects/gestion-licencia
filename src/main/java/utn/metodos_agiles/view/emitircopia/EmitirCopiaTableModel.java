package utn.metodos_agiles.view.emitircopia;

import utn.metodos_agiles.model.entidades.Licencia;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmitirCopiaTableModel extends AbstractTableModel {

    private static final String[] COLUM_NAMES = {
            "CLASE", "COPIA", "VENCIMIENTO"
    };

    private List<Licencia> licencias;

    public EmitirCopiaTableModel(List<Licencia> licencias) {
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

        return switch(columnIndex) {
            case 0 -> licencia.getClase();
            case 1 -> licencia.getTipo();
            case 2 -> licencia.getFechaVencimiento();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUM_NAMES[column];
    }

    public Licencia getLicenciaAt(int row) {
        if(row > (getRowCount()-1)) return null;
        return licencias.get(row);
    }
}
