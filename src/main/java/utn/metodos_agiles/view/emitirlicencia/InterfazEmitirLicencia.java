package utn.metodos_agiles.view.emitirlicencia;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import utn.metodos_agiles.controller.LicenciaController;
import utn.metodos_agiles.model.entidades.ClaseLicencia;
import utn.metodos_agiles.model.entidades.Licencia;
import utn.metodos_agiles.model.entidades.Titular;
import utn.metodos_agiles.view.NumericAndLengthFilter;
import utn.metodos_agiles.view.dialogs.MensajeExitoso;

import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.text.AbstractDocument;


public class InterfazEmitirLicencia extends JFrame {

	private static final long serialVersionUID = 1L;

    private static final String ESCUDO_ARG_PATH = "/imagenes/Escudo_Argentina.png";
    private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private JComboBox<String> comboBoxClase;
	private JTextField textoObs;
	private Titular titular;
	private Licencia licencia;
	private MensajeExitoso mensajeExitoso;

	public InterfazEmitirLicencia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazEmitirLicencia.class.getResource(ESCUDO_ARG_PATH)));
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
                    clasesDisponibles(titular);
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
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 41, 519, 58);
        resultadoBusqueda.add(scrollPane);
        
        tablaDatos = new JTable();
        tablaDatos.setModel(new EmitirLicenciaTableModel(List.of()));
        scrollPane.setViewportView(tablaDatos);
        
        JLabel licenciaTxt = new JLabel("LICENCIA DISPONIBLES:");
        licenciaTxt.setForeground(new Color(45, 45, 45));
        licenciaTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        licenciaTxt.setBounds(20, 110, 175, 29);
        resultadoBusqueda.add(licenciaTxt);
        
        comboBoxClase = new JComboBox<>();
        comboBoxClase.setBounds(438, 115, 101, 22);
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
                ClaseLicencia clase = ClaseLicencia.valueOf((String) comboBoxClase.getSelectedItem());
                String observacion = textoObs.getText();

        		LicenciaController.getInstance().emitirLicencia(titular, clase, observacion);
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
	
	
	private Titular buscarTitular() {
		 
		int dni = Integer.parseInt(txtDni.getText());
		    Titular titular = LicenciaController.getInstance().obtenerTitular(dni);
		    if (titular != null) {
		        tablaDatos.setModel(new EmitirLicenciaTableModel(List.of(titular)));
		    } else {
		        //todo: mensaje no encontrado
		        return null;
		    }
	    return titular;
	}
	
	
	private void clasesDisponibles(Titular titular) {
		
		//metodo usado para verificar las condiciones de cada clase de licencia y listar las que cumpla el titular
		comboBoxClase.removeAllItems();
		 
			int edad = titular.getEdad();

		    List<ClaseLicencia> licenciasPoseidas = LicenciaController.getInstance()
                    .licenciasDeTitular(titular.getDni()).stream().map(l->l.getClase())
                    .toList();
		    
		    if (edad >= 17) {
		        comboBoxClase.addItem("A");
		        comboBoxClase.addItem("B");
		        comboBoxClase.addItem("F");
		        comboBoxClase.addItem("G");
		    }
		    
		    if (edad >= 21) {
		        
		    	if (edad >= 65) {
		    		
		    		if (licenciasPoseidas.contains(ClaseLicencia.C)
                            || licenciasPoseidas.contains(ClaseLicencia.D)
                            || licenciasPoseidas.contains(ClaseLicencia.E)) {
			            comboBoxClase.addItem("C");
			            comboBoxClase.addItem("D");
			            comboBoxClase.addItem("E");
		    			}
		    	}else {
		    		if (licenciasPoseidas.contains(ClaseLicencia.B) && titular.tiempoLicencias() >= 1) {
		    			comboBoxClase.addItem("C");
		    			comboBoxClase.addItem("D");
		    			comboBoxClase.addItem("E");
		        		}
		    		}
		    }
	        
	}
	
    public void cerrarInterfaz() {
	        dispose(); 
	    }

	public void abrirMensajeExitoso() {
		MensajeExitoso mensajeExitoso = new MensajeExitoso(this);
		mensajeExitoso.setVisible(true);
		setVisible(false); 
	}
	
}








