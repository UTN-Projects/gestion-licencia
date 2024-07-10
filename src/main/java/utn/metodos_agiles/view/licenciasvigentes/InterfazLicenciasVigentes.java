package utn.metodos_agiles.view.licenciasvigentes;

import utn.metodos_agiles.controller.LicenciaController;
import utn.metodos_agiles.model.entidades.Licencia;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import utn.metodos_agiles.view.NumericAndLengthFilter;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class InterfazLicenciasVigentes extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private JTextField textoObs;
	private Set<Licencia> licencias;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JComboBox<String> comboBoxGS;
    private JComboBox<String> comboBoxRH;
    private JComboBox<String> comboBoxDonante;

	public InterfazLicenciasVigentes() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazLicenciasVigentes.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setTitle("Licencias Vigentes");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 600, 450);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 203, 60));
		contentPane.setToolTipText("");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		  
        JPanel datosDelTitular = new JPanel();
        datosDelTitular.setBackground(new Color(251, 203, 60));
        datosDelTitular.setBounds(10, 11, 564, 177);
        contentPane.add(datosDelTitular);
        datosDelTitular.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos del Titular", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        datosDelTitular.setLayout(null);
       
        
        
        JPanel datosVigencia = new JPanel();
        datosVigencia.setBounds(10, 21, 544, 145);
        datosDelTitular.add(datosVigencia);
        datosVigencia.setBackground(new Color(251, 203, 60));
        datosVigencia.setLayout(null);
        
        txtDni = new JTextField();
       ((AbstractDocument) txtDni.getDocument()).setDocumentFilter(new NumericAndLengthFilter(8));
        txtDni.setForeground(new Color(69, 69, 69));
        txtDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDni.setDisabledTextColor(new Color(255, 255, 255));
        txtDni.setBorder(null);
        txtDni.setBackground(new Color(251, 203, 60));
        txtDni.setBounds(94, 0, 112, 26);
        datosVigencia.add(txtDni);
        txtDni.setColumns(10);
        
        JLabel dniLabel = new JLabel("DNI:");
        dniLabel.setForeground(new Color(69, 69, 69));
        dniLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        dniLabel.setBounds(20, 11, 44, 20);
        datosVigencia.add(dniLabel);
        
        JSeparator separatorTxtDni = new JSeparator();
        separatorTxtDni.setBackground(new Color(69, 69, 69));
        separatorTxtDni.setForeground(new Color(69, 69, 69));
        separatorTxtDni.setBounds(94, 27, 112, 13);
        datosVigencia.add(separatorTxtDni);
        
        txtNombre = new JTextField();
        txtNombre.setForeground(new Color(69, 69, 69));
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setDisabledTextColor(Color.WHITE);
        txtNombre.setColumns(10);
        txtNombre.setBorder(null);
        txtNombre.setBackground(new Color(251, 203, 60));
        txtNombre.setBounds(94, 43, 112, 26);
        datosVigencia.add(txtNombre);
        
        JSeparator separatorTxtDni_1 = new JSeparator();
        separatorTxtDni_1.setForeground(new Color(69, 69, 69));
        separatorTxtDni_1.setBackground(new Color(69, 69, 69));
        separatorTxtDni_1.setBounds(94, 70, 112, 13);
        datosVigencia.add(separatorTxtDni_1);
        
        JLabel nombreLabel = new JLabel("NOMBRE:");
        nombreLabel.setForeground(new Color(69, 69, 69));
        nombreLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nombreLabel.setBounds(20, 54, 69, 20);
        datosVigencia.add(nombreLabel);
        
        JSeparator separatorTxtDni_1_1 = new JSeparator();
        separatorTxtDni_1_1.setForeground(new Color(69, 69, 69));
        separatorTxtDni_1_1.setBackground(new Color(69, 69, 69));
        separatorTxtDni_1_1.setBounds(94, 118, 112, 13);
        datosVigencia.add(separatorTxtDni_1_1);
        
        txtApellido = new JTextField();
        txtApellido.setForeground(new Color(69, 69, 69));
        txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtApellido.setDisabledTextColor(Color.WHITE);
        txtApellido.setColumns(10);
        txtApellido.setBorder(null);
        txtApellido.setBackground(new Color(251, 203, 60));
        txtApellido.setBounds(94, 91, 112, 26);
        datosVigencia.add(txtApellido);
        
        JLabel apellidoLabel = new JLabel("APELLIDO:");
        apellidoLabel.setForeground(new Color(69, 69, 69));
        apellidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        apellidoLabel.setBounds(20, 100, 69, 20);
        datosVigencia.add(apellidoLabel);
        
        JLabel lblGrupoSanguineo = new JLabel("GRUPO SANGUINEO:");
        lblGrupoSanguineo.setForeground(new Color(69, 69, 69));
        lblGrupoSanguineo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblGrupoSanguineo.setBounds(266, 11, 132, 20);
        datosVigencia.add(lblGrupoSanguineo);
        
        JLabel lblRH = new JLabel("RH:");
        lblRH.setHorizontalAlignment(SwingConstants.TRAILING);
        lblRH.setForeground(new Color(69, 69, 69));
        lblRH.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblRH.setBounds(262, 54, 132, 20);
        datosVigencia.add(lblRH);
        
        JLabel lblDonante = new JLabel("DONANTE:");
        lblDonante.setHorizontalAlignment(SwingConstants.TRAILING);
        lblDonante.setForeground(new Color(69, 69, 69));
        lblDonante.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDonante.setBounds(262, 98, 132, 20);
        datosVigencia.add(lblDonante);
        
        
        
        comboBoxRH = new JComboBox<>();
		comboBoxRH.setModel(new DefaultComboBoxModel(new String[] {" ", "+", "-"}));
		comboBoxRH.setBounds(440, 55, 80, 22);
		datosVigencia.add(comboBoxRH);

		comboBoxDonante = new JComboBox<>();
		comboBoxDonante.setModel(new DefaultComboBoxModel(new String[] {" ", "SI", "NO"}));
		comboBoxDonante.setBounds(440, 94, 80, 22);
		datosVigencia.add(comboBoxDonante);

		comboBoxGS = new JComboBox<>();
		comboBoxGS.setModel(new DefaultComboBoxModel(new String[] {" ", "A", "B", "0"}));
		comboBoxGS.setBounds(440, 12, 80, 22);
		datosVigencia.add(comboBoxGS);
        
        
        
        
        
        /*JComboBox comboBoxRH = new JComboBox();
        comboBoxRH.setModel(new DefaultComboBoxModel(new String[] {"", "+", "-"}));
        comboBoxRH.setBounds(440, 55, 80, 22);
        datosVigencia.add(comboBoxRH);
        
        JComboBox comboBoxDonante = new JComboBox();
        comboBoxDonante.setModel(new DefaultComboBoxModel(new String[] {"", "SI", "NO"}));
        comboBoxDonante.setBounds(440, 94, 80, 22);
        datosVigencia.add(comboBoxDonante);
        
        JComboBox comboBoxGS = new JComboBox();
        comboBoxGS.setModel(new DefaultComboBoxModel(new String[] {"", "A", "B", "AB", "O"}));
        comboBoxGS.setBounds(440, 12, 80, 22);
        datosVigencia.add(comboBoxGS);*/
		
        JPanel resultadoBusqueda = new JPanel();
        resultadoBusqueda.setBackground(new Color(251, 203, 60));
        resultadoBusqueda.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Resultado de b\u00FAsqueda", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        resultadoBusqueda.setBounds(10, 240, 564, 126);
        resultadoBusqueda.setLayout(null);

      
        contentPane.add(resultadoBusqueda);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 23, 544, 92);
        resultadoBusqueda.add(scrollPane);
        
        tablaDatos = new JTable();
        tablaDatos.setFont(new Font("Tahoma", Font.PLAIN, 11));
        DefaultTableModel tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[] {
                        "DNI", "NOMBRE", "APELLIDO","CLASE", "TIPO", "SANGRE", "DONANTE", "CADUCIDAD"
                }
        );

        tablaDatos.setModel(new DefaultTableModel(
        	new Object[][] {
        		{null, null, null, null, null, null, null, null},
        	},
        	new String[] {
        		"DNI", "NOMBRE", "APELLIDO", "CLASE", "GS", "RH", "DONANTE", "CADUCIDAD"
        	}
        ) {
        	boolean[] columnEditables = new boolean[] {
        		false, false, false, false, false, false, false, false
        	};
        	public boolean isCellEditable(int row, int column) {
        		return columnEditables[column];
        	}
        });
        tablaDatos.getColumnModel().getColumn(0).setResizable(false);
        tablaDatos.getColumnModel().getColumn(1).setResizable(false);
        tablaDatos.getColumnModel().getColumn(2).setResizable(false);
        tablaDatos.getColumnModel().getColumn(3).setResizable(false);
        tablaDatos.getColumnModel().getColumn(4).setResizable(false);
        tablaDatos.getColumnModel().getColumn(5).setResizable(false);
        tablaDatos.getColumnModel().getColumn(6).setResizable(false);
        tablaDatos.getColumnModel().getColumn(7).setResizable(false);
        scrollPane.setViewportView(tablaDatos);
        
        
        JPanel btnCancelar = new JPanel();
        btnCancelar.setBackground(new Color(69, 69, 69));
        btnCancelar.setBounds(478, 377, 96, 23);
        contentPane.add(btnCancelar);
        btnCancelar.setLayout(null);
        
        JLabel btnCancelarTxt = new JLabel("Cancelar");
        btnCancelarTxt.setBounds(0, 0, 96, 23);
        btnCancelar.add(btnCancelarTxt);
        btnCancelarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		limpiarCampos();
        		limpiarCampoDni();
        		DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
                model.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos
        		cerrarInterfaz();
        	}
        });
        btnCancelarTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelarTxt.setForeground(new Color(255, 255, 255));
        btnCancelarTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancelarTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JPanel btnBuscar = new JPanel();
        btnBuscar.setBounds(226, 199, 130, 26);
        contentPane.add(btnBuscar);
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(69, 69, 69));
        btnBuscar.setLayout(null);
        
        JLabel btnBuscarTxt = new JLabel("Buscar");
        btnBuscarTxt.setBounds(0, 0, 130, 26);
        btnBuscar.add(btnBuscarTxt);
        btnBuscarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		buscarLicenciasPorCriterios();
        		
        		
        		
        	}
        });
        btnBuscarTxt.setForeground(new Color(255, 255, 255));
        btnBuscarTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnBuscarTxt.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnBuscarTxt.setHorizontalAlignment(SwingConstants.CENTER);

        addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				limpiarCampos();
				limpiarCampoDni();
				DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos
		        
		        
				
			}
		});
        
        
        
    }
	
	
	private void buscarLicenciasPorCriterios() {
	    String dniText = txtDni.getText().trim();
	    String nombre = txtNombre.getText().trim();
	    String apellido = txtApellido.getText().trim();
	    String grupoSanguineo = comboBoxGS.getSelectedItem() != null ? comboBoxGS.getSelectedItem().toString().trim() : "";
	    String rh = comboBoxRH.getSelectedItem() != null ? comboBoxRH.getSelectedItem().toString().trim() : "";
	    String donante = comboBoxDonante.getSelectedItem() != null ? comboBoxDonante.getSelectedItem().toString().trim() : "";
	    String dona = "";

	    try {
	        List<Licencia> licenciasVigentes = LicenciaController.getInstance().licenciasVigentes();

	        // Mensajes de depuración para verificar los valores seleccionados en los JComboBox
	        System.out.println("Valor seleccionado en Grupo Sanguíneo: " + grupoSanguineo);
	        System.out.println("Valor seleccionado en Rh: " + rh);

	        List<Licencia> filteredLicencias = licenciasVigentes.stream().filter(licencia -> {
	            boolean matches = true;
	            if (!dniText.isEmpty()) {
	                try {
	                    int dni = Integer.parseInt(dniText);
	                    matches = matches && String.valueOf(licencia.getTitular().getDni()).contains(dniText);
	                } catch (NumberFormatException e) {
	                    return false;
	                }
	            }
	            if (!nombre.isEmpty()) {
	                matches = matches && licencia.getNombreTitular().toLowerCase().contains(nombre.toLowerCase());
	            }
	            if (!apellido.isEmpty()) {
	                matches = matches && licencia.getApellidoTitular().toLowerCase().contains(apellido.toLowerCase());
	            }
	            if (!grupoSanguineo.isEmpty() && !grupoSanguineo.equals(" ")) {
	                matches = matches && licencia.getGrupoSangTitular().toString().trim().equalsIgnoreCase(grupoSanguineo);
	                System.out.println("Comparando grupo sanguíneo: " + licencia.getGrupoSangTitular().toString().trim() + " con " + grupoSanguineo);
	            }
	            if (!rh.isEmpty() && !rh.equals(" ")) {
	                matches = matches && licencia.getRhTitular().toString().trim().equalsIgnoreCase(rh);
	                System.out.println("Comparando Rh: " + licencia.getRhTitular().toString().trim() + " con " + rh);
	            }
	            if (!donante.isEmpty() && !donante.equals(" ")) {
	                boolean esDonante = donante.equalsIgnoreCase("SI");
	                matches = matches && licencia.isEsDonanteTitular() == esDonante;
	               
	            }
	            return matches;
	        }).toList();

	        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
	        model.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos
  
	        filteredLicencias.forEach(licencia -> {
	        	String esDonante = licencia.isEsDonanteTitular() ? "SI" : "NO";
	            model.addRow(new Object[]{
	                licencia.getTitular().getDni(),
	                licencia.getNombreTitular(),
	                licencia.getApellidoTitular(),
	                licencia.getClase(),
	                licencia.getGrupoSangTitular(),
	                licencia.getRhTitular(),
	                esDonante,
	                //licencia.isEsDonanteTitular(),
	                licencia.getFechaVencimiento()
	            });
	        });

	        if (filteredLicencias.isEmpty()) {
	            model.addRow(new Object[]{"No encontrado", "", ""});
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	/*private void buscarLicencias() {
		 
		Integer dni = txtDni.getText().isEmpty()? null : Integer.parseInt(txtDni.getText());
        List<Licencia> licenciasFiltered = licencias.stream().filter(licencia -> dni == null || licencia.getTitular().getDni() == dni).toList();

		    if (!licenciasFiltered.isEmpty()) {
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos

                        licenciasFiltered.forEach(licencia -> model.addRow(new Object[]{
                                licencia.getTitular().getDni(),
                                licencia.getNombreTitular(),
                                licencia.getApellidoTitular(),
                                licencia.getClase(),
                                licencia.getTipo(),
                                licencia.getGrupoSangTitular().toString()+licencia.getRhTitular(),
                                licencia.isEsDonanteTitular(),
                                licencia.getFechaVencimiento()
                        }));

		    } else {
		        // Mostrar mensaje de que no se encontró el titular
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla si no se encontró el titular
		        model.addRow(new Object[]{"No encontrado", "", ""});
		    }
	}*/
	
	 public void cerrarInterfaz() {
	        dispose(); 
	    }

	private void limpiarCampos() {
	    txtNombre.setText("");
	    txtApellido.setText("");
	    comboBoxGS.setSelectedIndex(0);
	    comboBoxRH.setSelectedIndex(0);
	    comboBoxDonante.setSelectedIndex(0);
	}
	
	
	
	private void limpiarCampoDni() {
		txtDni.setText("");

	      
	}	
}