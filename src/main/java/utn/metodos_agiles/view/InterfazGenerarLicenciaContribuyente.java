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

import entidades.*;
import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.entidades.*;
import utn.metodos_agiles.util.VigenciaCalculator;

import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class InterfazGenerarLicenciaContribuyente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private JComboBox<String> comboBoxClase;
	private JTextField textoObs;
	private Titular titular;
	private Licencia licencia;
	private MensajeExitoso mensajeExitoso;

	public InterfazGenerarLicenciaContribuyente(Contribuyente contribuyente) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazFormulario.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setTitle("Emitir licencia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 600, 450);
		//mostrarDatosContribuyente(contribuyente);
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 203, 60));
		contentPane.setToolTipText("");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        JPanel resultadoBusqueda = new JPanel();
        resultadoBusqueda.setBackground(new Color(251, 203, 60));
        resultadoBusqueda.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos de la licencia a emitir", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        resultadoBusqueda.setBounds(10, 50, 564, 200);
        resultadoBusqueda.setLayout(null);

      
        contentPane.add(resultadoBusqueda);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 41, 519, 58);
        resultadoBusqueda.add(scrollPane);
        
        tablaDatos = new JTable();
        tablaDatos.setModel(new DefaultTableModel(
        	new Object[][] {
        		{contribuyente.getDni(), contribuyente.getNombre(), contribuyente.getApellido()},
        	},
        	new String[] {
        		"DNI", "NOMBRE", "APELLIDO"
        	}
        ));
        scrollPane.setViewportView(tablaDatos);
        
        JLabel licenciaTxt = new JLabel("LICENCIA DISPONIBLES:");
        licenciaTxt.setForeground(new Color(45, 45, 45));
        licenciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        licenciaTxt.setBounds(20, 110, 175, 29);
        resultadoBusqueda.add(licenciaTxt);
        
        comboBoxClase = new JComboBox<>();
        comboBoxClase.setBounds(438, 115, 101, 22);
        comboBoxClase.removeAllItems();
		comboBoxClase.addItem("A");
		comboBoxClase.addItem("B");
		comboBoxClase.addItem("F");
		comboBoxClase.addItem("G");
        resultadoBusqueda.add(comboBoxClase);
        
        JLabel claseTxt = new JLabel("CLASE");
        claseTxt.setForeground(new Color(69, 69, 69));
        claseTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        claseTxt.setBounds(376, 110, 59, 29);
        resultadoBusqueda.add(claseTxt);
        
        JLabel txtObservaciones = new JLabel("OBSERVACIONES:");
        txtObservaciones.setForeground(new Color(45, 45, 45));
        txtObservaciones.setFont(new Font("Tahoma", Font.PLAIN, 15));
        txtObservaciones.setBounds(20, 163, 132, 26);
        resultadoBusqueda.add(txtObservaciones);
        
        textoObs = new JTextField();
        textoObs.setForeground(new Color(45, 45, 45));
        textoObs.setBounds(242, 163, 297, 25);
        resultadoBusqueda.add(textoObs);
        textoObs.setColumns(10);
        
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
        		
        		licencia = emitirLicencia(contribuyente);
                DBManager.getInstance().guardarTitular(Titular.of(contribuyente));
        		DBManager.getInstance().cargarLicencia(licencia);
        		abrirMensajeExitoso();
        		
        		
        	}
        });
        btnEmitirTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEmitirTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnEmitirTxt.setForeground(Color.WHITE);
        btnEmitirTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEmitirTxt.setBounds(0, 0, 96, 23);
        btnEmitir.add(btnEmitirTxt);
		
	
		
			}
	
	
	private Licencia emitirLicencia(Contribuyente contribuyente) {
		
		String claseSeleccionada = (String) comboBoxClase.getSelectedItem();
	    
	    
	    String observaciones = textoObs.getText();
	    
	    LocalDate fechaEmisionLocal = LocalDate.now();
	    Date fechaEmision = Date.valueOf(fechaEmisionLocal);

        int cantLic = DBManager.getInstance().cantLicTitular(contribuyente.getDni());

        return Licencia.builder()
                .titular(Titular.builder()
                        .dni(contribuyente.getDni())
                        .build())
                .nombreTitular(contribuyente.getNombre())
                .apellidoTitular(contribuyente.getApellido())
                .fechaNacTitular(contribuyente.getFechaNacimiento())
                .calleTitular(contribuyente.getCalle())
                .nroCasaTitular(contribuyente.getNroCasa())
                .clase(ClaseLicencia.valueOf(claseSeleccionada))
                .tipo(TipoLicencia.ORIGINAL)
                .grupoSangTitular(contribuyente.getGrupoSanguineo())
                .rhTitular(contribuyente.getRh())
                .esDonanteTitular(contribuyente.getEsDonante())
                .observaciones(observaciones)
                .fechaEmision(fechaEmision)
                .administrador("Juan Perez")
                .vigente(true)
                .fechaVencimiento(VigenciaCalculator.calcularVigencia(contribuyente.getEdad(), cantLic))
                .build();
	}
	
	 public void cerrarInterfaz() {
	        dispose(); 
	    }
	
	
	public void abrirMensajeExitoso() {
		MensajeExitoso mensajeExitoso = new MensajeExitoso(new InterfazFormulario());
		mensajeExitoso.setVisible(true);
		setVisible(false); 
	}
	
}

