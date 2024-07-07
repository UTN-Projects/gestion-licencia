package utn.metodos_agiles.view;

import utn.metodos_agiles.entidades.Licencia;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.List;

public class LicenciasVigentesTableModel extends AbstractTableModel {

    private static final String[] COLUM_NAMES = new String[] {
            "DNI", "NOMBRE", "APELLIDO","CLASE", "TIPO", "SANGRE", "DONANTE", "CADUCIDAD"
    };

    private List<Licencia> licencias;

    public LicenciasVigentesTableModel(List<Licencia> licencias) {
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
            case(0) -> licencia.getTitular().getDni();
            case(1) -> licencia.getNombreTitular();
            case(2) -> licencia.getApellidoTitular();
            case(3) -> licencia.getClase();
            case(4) -> licencia.getTipo();
            case(5) -> licencia.getGrupoSangTitular().toString()+licencia.getRhTitular();
            case(6) -> licencia.isEsDonanteTitular();
            case(7) -> licencia.getFechaVencimiento();
            default -> null;
        };
    }

    @Override
    public String getColumnName(int column) {
        return COLUM_NAMES[column];
    }

    public Licencia getRow(int row) {
        return licencias.get(row);
    }
}

