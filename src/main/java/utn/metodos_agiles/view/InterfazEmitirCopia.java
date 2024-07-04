package utn.metodos_agiles.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import utn.metodos_agiles.entidades.ClaseLicencia;
import utn.metodos_agiles.entidades.Licencia;
import utn.metodos_agiles.entidades.TipoLicencia;
import utn.metodos_agiles.entidades.Titular;
import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.model.ClientDto;
import utn.metodos_agiles.model.LicenciaDto;
import utn.metodos_agiles.model.FacturaItem;
import utn.metodos_agiles.util.LicenciaGenerator;
import utn.metodos_agiles.util.FacturaGenerator;
import utn.metodos_agiles.util.VigenciaCalculator;

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
	private Titular titular;
	private Licencia licencia;
	private List<Licencia> licencias;
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
        			if(licencias.get(tablaDatos.getSelectedRow()) != null) {
        				licencia = emitirCopia(tablaDatos.getSelectedRow());
        				DBManager.getInstance().cargarLicencia(licencia);
        				imprimirLicencia();
        				imprimirFactura();
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
		    Titular titular = DBManager.getInstance().buscarPorDni(dni);
		    if (titular != null) {
		    	licencias = DBManager.getInstance().cargarLicenciasTitular(dni);
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla antes de a�adir nuevos datos
		        for(int i = 0; i < licencias.size(); i++) {
		        	if(licencias.get(i) != null) {
		        		model.addRow(new Object[]{licencias.get(i).getClase(), licencias.get(i).getTipo(),
		        				licencias.get(i).getFechaVencimiento()});
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

	private Licencia emitirCopia(int i) {
		Licencia licencia = licencias.get(i);

		licencia.setTipo(TipoLicencia.fromNumber(licencia.getTipo().toNumber() + 1));

		return licencia;
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
        String filePath = "licencia" + titular.getDni() + ".pdf";
        String imagePath = "src/main/resources/fotos/" + titular.getDni() +".png";

        LicenciaDto l = LicenciaDto.builder()
				.number("" + DBManager.getInstance().IDLicencia(licencia.getTitular().getDni(), licencia.getClase()))
				.lastname(licencia.getTitular().getApellido())
				.name(licencia.getTitular().getNombre())
				.address(licencia.getCalleTitular() + " " + licencia.getNroCasaTitular())
				.birth(licencia.getFechaNacTitular().getDay() +
						LicenciaDto.traductorMes(licencia.getFechaNacTitular().getMonth()) +
						licencia.getFechaNacTitular().getYear())
				.emition(licencia.getFechaEmision().getDay() +
						LicenciaDto.traductorMes(licencia.getFechaEmision().getMonth()) +
						licencia.getFechaEmision().getYear())
				.expiration(licencia.getFechaVencimiento().getDay() +
						LicenciaDto.traductorMes(licencia.getFechaVencimiento().getMonth()) +
						licencia.getFechaVencimiento().getYear())
				.isDonor(licencia.isEsDonanteTitular()).bloodType(licencia.getGrupoSangTitular().toString() + licencia.getRhTitular())
				.cuil("" + licencia.getTitular().getDni()).observations(licencia.getObservaciones())
				.type(licencia.getTipo().toString()).build();
		LicenciaGenerator.generar(l, imagePath, filePath);
	}
	
	private void imprimirFactura() {
		String filePath = "factura" + titular.getDni() + ".pdf";
		ClientDto c = ClientDto.builder().name(titular.getNombre() + " " + titular.getApellido())
				.address(titular.getCalle() + " " + titular.getNroCasa()).dni("" + titular.getDni()).build();
		List<FacturaItem> items = new ArrayList<FacturaItem>();
		items.add(FacturaItem.builder().description("Copia de licencia clase " + licencia.getClase()).value(50.0F)
				.build());
		FacturaGenerator.generar(c, items, filePath);
	}
	
	
}
