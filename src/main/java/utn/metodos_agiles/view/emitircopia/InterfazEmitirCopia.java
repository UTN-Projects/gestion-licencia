package utn.metodos_agiles.view.emitircopia;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import utn.metodos_agiles.controller.LicenciaController;
import utn.metodos_agiles.model.entidades.Licencia;
import utn.metodos_agiles.model.entidades.Titular;
import utn.metodos_agiles.view.*;
import utn.metodos_agiles.view.dialogs.AdvertenciaElejirLicencia;
import utn.metodos_agiles.view.dialogs.MensajeCopiaExitosa;
import utn.metodos_agiles.view.dialogs.MensajeExitoso;
import utn.metodos_agiles.view.emitirlicencia.InterfazEmitirLicencia;

import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.text.AbstractDocument;

import java.util.List;

public class InterfazEmitirCopia extends JFrame {

	private static final long serialVersionUID = 1L;

    private static final String ESCUDO_ARG_PATH = "/imagenes/Escudo_Argentina.png";
    private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private Titular titular;
	private Licencia licencia;
	private MensajeExitoso mensajeExitoso;
	private JLabel nombreTitular;

	public InterfazEmitirCopia() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazEmitirLicencia.class.getResource(ESCUDO_ARG_PATH)));
		setResizable(false);
		setTitle("Emitir copia");
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
        tablaDatos.setModel(new EmitirCopiaTableModel(List.of()));
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
                    Licencia licenciaSeleccionada = ((EmitirCopiaTableModel) tablaDatos.getModel())
                            .getLicenciaAt(tablaDatos.getSelectedRow());

        			if(licenciaSeleccionada != null) {
                        LicenciaController.getInstance().copiarLicencia(licenciaSeleccionada);

                        // todo: fix mostrar mensaje
                        buscarTitular();
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
        List<Licencia> licencias = LicenciaController.getInstance().licenciasDeTitular(dni);

        tablaDatos.setModel(new EmitirCopiaTableModel(licencias));

        if(licencias.isEmpty()) {
            // TODO: dialog no encontrado
            // Mostrar mensaje de que no se encontr� el titular
            return null;
        }

	    return licencias.get(0).getTitular();
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
	
}
