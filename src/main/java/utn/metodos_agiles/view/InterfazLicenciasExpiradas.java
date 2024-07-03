package utn.metodos_agiles.view;

import utn.metodos_agiles.entidades.Licencia;
import utn.metodos_agiles.db.DBManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class InterfazLicenciasExpiradas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private JTextField textoObs;
	private Set<Licencia> licencias;


	public InterfazLicenciasExpiradas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazLicenciasExpiradas.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setTitle("Licencias Expiradas");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 600, 450);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 203, 60));
		contentPane.setToolTipText("");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		  
        JPanel datosDelTitular = new JPanel();
        datosDelTitular.setBackground(new Color(251, 203, 60));
        datosDelTitular.setBounds(10, 11, 564, 125);
        contentPane.add(datosDelTitular);
        datosDelTitular.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos del Titular", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        datosDelTitular.setLayout(null);
       
        
        
        JPanel datosDni = new JPanel();
        datosDni.setBounds(10, 21, 544, 93);
        datosDelTitular.add(datosDni);
        datosDni.setBackground(new Color(251, 203, 60));
        datosDni.setLayout(null);
        
        txtDni = new JTextField();
        ((AbstractDocument) txtDni.getDocument()).setDocumentFilter(new NumericAndLengthFilter(8));
        txtDni.setForeground(new Color(69, 69, 69));
        txtDni.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtDni.setDisabledTextColor(new Color(255, 255, 255));
        txtDni.setBorder(null);
        txtDni.setBackground(new Color(251, 203, 60));
        txtDni.setBounds(351, 11, 155, 26);
        datosDni.add(txtDni);
        txtDni.setColumns(10);
        
        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setForeground(new Color(69, 69, 69));
        dniLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        dniLabel.setBounds(37, 17, 44, 20);
        datosDni.add(dniLabel);
        
        JSeparator separatorTxtDni = new JSeparator();
        separatorTxtDni.setBackground(new Color(69, 69, 69));
        separatorTxtDni.setForeground(new Color(69, 69, 69));
        separatorTxtDni.setBounds(351, 38, 155, 13);
        datosDni.add(separatorTxtDni);
        
        JPanel btnBuscar = new JPanel();
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(69, 69, 69));
        btnBuscar.setBounds(211, 56, 130, 26);
        datosDni.add(btnBuscar);
        btnBuscar.setLayout(null);
        
        JLabel btnBuscarTxt = new JLabel("Buscar");
        btnBuscarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		buscarLicencias();
        		
        	}
        });
        btnBuscarTxt.setForeground(new Color(255, 255, 255));
        btnBuscarTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnBuscarTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnBuscarTxt.setBounds(0, 0, 130, 26);
        btnBuscar.add(btnBuscarTxt);
		
        JPanel resultadoBusqueda = new JPanel();
        resultadoBusqueda.setBackground(new Color(251, 203, 60));
        resultadoBusqueda.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Resultado de b\u00FAsqueda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        resultadoBusqueda.setBounds(10, 147, 564, 200);
        resultadoBusqueda.setLayout(null);

      
        contentPane.add(resultadoBusqueda);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 41, 519, 150);
        resultadoBusqueda.add(scrollPane);
        
        tablaDatos = new JTable();
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[] {
                        "DNI", "NOMBRE", "APELLIDO", "TIPO DE LICENCIA", "CADUCIDAD"
                }
        );

        licencias = DBManager.getInstance().recuperarLicenciasVencidas().stream()
                .sorted(Comparator.comparingInt(licencia -> licencia.getTitular().getDni()))
                .collect(Collectors.toCollection(LinkedHashSet::new));

        licencias.forEach(licencia -> {
            tableModel.addRow(new Object[]{
                    licencia.getTitular().getDni(),
                    licencia.getNombreTitular(),
                    licencia.getApellidoTitular(),
                    licencia.getTipo(),
                    licencia.getFechaVencimiento()
        });
        });

        tablaDatos.setModel(tableModel);
        scrollPane.setViewportView(tablaDatos);
        
        JPanel btnCancelar = new JPanel();
        btnCancelar.setBackground(new Color(69, 69, 69));
        btnCancelar.setBounds(373, 377, 96, 23);
        contentPane.add(btnCancelar);
        btnCancelar.setLayout(null);
        
        JLabel btnCancelarTxt = new JLabel("Cancelar");
        btnCancelarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		cerrarInterfaz();
        	}
        });
        btnCancelarTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelarTxt.setForeground(new Color(255, 255, 255));
        btnCancelarTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancelarTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCancelarTxt.setBounds(0, 0, 96, 23);
        btnCancelar.add(btnCancelarTxt);

    }
	
	
	private void buscarLicencias() {
		 
		Integer dni = txtDni.getText().isEmpty()? null : Integer.parseInt(txtDni.getText());
        List<Licencia> licenciasFiltered = licencias.stream().filter(licencia -> dni == null || licencia.getTitular().getDni() == dni).toList();

		    if (!licenciasFiltered.isEmpty()) {
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos

                        licenciasFiltered.forEach(licencia -> model.addRow(new Object[]{
                        licencia.getTitular().getDni(),
                        licencia.getNombreTitular(),
                        licencia.getApellidoTitular(),
                        licencia.getTipo(),
                        licencia.getFechaVencimiento()}));

		    } else {
		        // Mostrar mensaje de que no se encontró el titular
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla si no se encontró el titular
		        model.addRow(new Object[]{"No encontrado", "", ""});
		    }
	}
	
	 public void cerrarInterfaz() {
	        dispose(); 
	    }


	
}
