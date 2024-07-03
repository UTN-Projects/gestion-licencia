package utn.metodos_agiles.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import utn.metodos_agiles.entidades.Licencia;
import utn.metodos_agiles.entidades.Titular;
import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.model.ClientDto;
import utn.metodos_agiles.model.LicenciaDto;
import utn.metodos_agiles.model.FacturaItem;
import utn.metodos_agiles.util.LicenciaGenerator;
import utn.metodos_agiles.util.FacturaGenerator;

import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Set;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.text.AbstractDocument;

import java.util.ArrayList;
import java.util.List;

public class InterfazEmitirCopia extends JFrame {

	private static final long serialVersionUID = 1L;

    private static final String ESCUDO_ARG_PATH = "/imagenes/Escudo_Argentina.png";
    private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private JComboBox<String> comboBoxClase;
	private JTextField textoObs;
	private Titular titular;
	private Licencia licencia;
	private Licencia[] licencias = new Licencia[3];
	private MensajeExitoso mensajeExitoso;
	private JLabel nombreTitular;

	public InterfazEmitirCopia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazFormulario.class.getResource(ESCUDO_ARG_PATH)));
		setResizable(false);
		setTitle("Emitir licencia");
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
        		
        		titular = buscarTitular();  
                if (titular != null) {
                    //clasesDisponibles(titular);
                    nombreTitular.setText(titular.getNombre() + " " + titular.getApellido());
                }
        		
        		
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
        
        nombreTitular = new JLabel("Lionel Messi", SwingConstants.CENTER);
        nombreTitular.setForeground(new Color(45, 45, 45));
        nombreTitular.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nombreTitular.setBounds(0, 31, 564, 26);
        resultadoBusqueda.add(nombreTitular);
        nombreTitular.setText("");
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 81, 519, 78);
        resultadoBusqueda.add(scrollPane);
        
        tablaDatos = new JTable();
        tablaDatos.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"", null, null},
        		{null, null, null},
        	},
        	new String[] {
        		"CLASE", "TIPO", "VENCIMIENTO"
        	}
        ));
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
        
        JPanel btnEmitir = new JPanel();
        btnEmitir.setLayout(null);
        btnEmitir.setBackground(new Color(69, 69, 69));
        btnEmitir.setBounds(478, 377, 96, 23);
        contentPane.add(btnEmitir);
        
        JLabel btnEmitirTxt = new JLabel("Emitir");
        btnEmitirTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		if(tablaDatos.getSelectedRow() != -1) {
        			//Generar copia y guardarla
        			if(licencias[tablaDatos.getSelectedRow()] != null) {
        				licencia = emitirCopia(tablaDatos.getSelectedRow());
        				DBManager.cargarLicencia(licencia);
        				//imprimirLicencia();
        				//imprimirFactura();
        				abrirMensajeExitoso();
        			} else {
            			abrirAdvertencia();
        			}
        		} else {
        			abrirAdvertencia();
        		}
        		
        		
        	}
        });
        btnEmitirTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEmitirTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnEmitirTxt.setForeground(Color.WHITE);
        btnEmitirTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEmitirTxt.setBounds(0, 0, 96, 23);
        btnEmitir.add(btnEmitirTxt);
		
	
		
			}
	
	
	private Titular buscarTitular() {
		 
		int dni = Integer.parseInt(txtDni.getText());
		    Titular titular = DBManager.buscarPorDni(dni);
		    if (titular != null) {
		    	licencias = DBManager.cargarLicenciasTitular(dni);
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla antes de a�adir nuevos datos
		        for(int i = 0; i < 3; i++) {
		        	if(licencias[i] != null) {
		        		model.addRow(new Object[]{licencias[i].getClase(), licencias[i].getTipo(), 
		        				licencias[i].getFecha_vencimiento()});
		        	}
		        }
		    } else {
		        // Mostrar mensaje de que no se encontr� el titular
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla si no se encontr� el titular
		        model.addRow(new Object[]{"No encontrado", "", ""});
		        return null;
		    }
	    return titular;
	}
	
	
	private void clasesDisponibles(Titular titular) {
		
		//metodo usado para verificar las condiciones de cada clase de licencia y listar las que cumpla el titular
		comboBoxClase.removeAllItems();
		 
			int edad = titular.getEdad();
		    Set<String> licenciasPoseidas = titular.getLicencias();
		    
		    
		    if (edad >= 17) {
		        comboBoxClase.addItem("A");
		        comboBoxClase.addItem("B");
		        comboBoxClase.addItem("F");
		        comboBoxClase.addItem("G");
		    }
		    
		    if (edad >= 21) {
		        
		    	if (edad >= 65) {
		    		
		    		if (licenciasPoseidas.contains("C") || licenciasPoseidas.contains("D") || licenciasPoseidas.contains("E")) {
			            comboBoxClase.addItem("C");
			            comboBoxClase.addItem("D");
			            comboBoxClase.addItem("E");
		    			}
		    	}else {
		    		if (licenciasPoseidas.contains("B") && titular.tiempoLicencias() >= 1) {
		    			comboBoxClase.addItem("C");
		    			comboBoxClase.addItem("D");
		    			comboBoxClase.addItem("E");
		        		}
		    		}
		    }
	        
	}
	
	private Licencia emitirCopia(int i) {
		Licencia licencia = licencias[i];
		switch(licencia.getTipo()) {
		case "original":
			licencia.setTipo("duplicado");
			break;
		case "duplicado":
			licencia.setTipo("triplicado");
			break;
		case "triplicado":
			licencia.setTipo("cuadriplicado");
			break;
		case "cuadriplicado":
			licencia.setTipo("quintuplicado");
			break;
		case "quintuplicado":
			licencia.setTipo("sextuplicado");
			break;
		case "sextuplicado":
			licencia.setTipo("septuplicado");
			break;
		case "septuplicado":
			licencia.setTipo("octoplicado");
			break;
		default:
			licencia.setTipo("tantas vas a perder?"); //en realidad no s� como se dice la novena copia
			break;
		}
		return licencia;
	}
	
	private Licencia emitirLicencia(Titular titular) {
		
		String claseSeleccionada = (String) comboBoxClase.getSelectedItem();
	    
	    
	    String observaciones = textoObs.getText();
	    
	    LocalDate fechaEmisionLocal = LocalDate.now();
	    Date fechaEmision = Date.valueOf(fechaEmisionLocal);

        return Licencia.builder()
                .dni_titular(titular.getDni())
                .nombre_titular(titular.getNombre())
                .apellido_titular(titular.getApellido())
                .fecha_nac_titular(titular.getFecha_nacimiento())
                .calle_titular(titular.getCalle())
                .nro_casa_titular(titular.getNro_casa())
                .clase(claseSeleccionada)
                .tipo("original")
                .grupo_sang_titular(titular.getGrupo_sanguineo())
                .rh_titular(titular.getRh())
                .es_donante_titular(titular.getEs_donante())
                .observaciones(observaciones)
                .fecha_emision(fechaEmision)
                .administrador("Juan Perez")
                .vigente("si")
                .fecha_vencimiento(Licencia.calcularVigencia(titular))
                .build();
	}
	
	 public void cerrarInterfaz() {
	        dispose(); 
	    }
	
	
	public void abrirMensajeExitoso() {
		MensajeCopiaExitosa mensajeExitoso = new MensajeCopiaExitosa(this);
		mensajeExitoso.setVisible(true);
		setVisible(false); 
	}
	
	private void abrirAdvertencia() {
		AdvertenciaElejirLicencia a = new AdvertenciaElejirLicencia();
		a.setVisible(true);
	}
	
	private void imprimirLicencia() {
		Boolean d;
		if(licencia.getEs_donante_titular() == "si") d = true; 
		else d = false;
		LicenciaDto l = LicenciaDto.builder().number("" + DBManager.IDLicencia(licencia.getDni_titular(), 
						licencia.getClase()))
				.lastname(licencia.getApellido_titular()).name(licencia.getNombre_titular())
				.address(licencia.getCalle_titular() + " " + licencia.getNro_casa_titular())
				.birth(licencia.getFecha_nac_titular().getDay() + 
						LicenciaDto.traductorMes(licencia.getFecha_nac_titular().getMonth()) +
						licencia.getFecha_nac_titular().getYear())
				.emition(licencia.getFecha_emision().getDay() + 
						LicenciaDto.traductorMes(licencia.getFecha_emision().getMonth()) +
						licencia.getFecha_emision().getYear())
				.expiration(licencia.getFecha_vencimiento().getDay() + 
						LicenciaDto.traductorMes(licencia.getFecha_vencimiento().getMonth()) +
						licencia.getFecha_vencimiento().getYear())
				.isDonor(d).bloodType(licencia.getGrupo_sang_titular() + licencia.getRh_titular())
				.cuil("" + licencia.getDni_titular()).observations(licencia.getObservaciones())
				.type(licencia.getTipo()).build();
		LicenciaGenerator.generar(l, "", "");
	}
	
	private void imprimirFactura() {
		ClientDto c = ClientDto.builder().name(titular.getNombre() + " " + titular.getApellido())
				.address(titular.getCalle() + " " + titular.getNro_casa()).dni("" + titular.getDni()).build();
		List<FacturaItem> items = new ArrayList<FacturaItem>();
		items.add(FacturaItem.builder().description("Licencia clase " + licencia.getClase()).value(50.0F)
				.build());
		FacturaGenerator.generar(c, items, "");
	}
	
	
}
