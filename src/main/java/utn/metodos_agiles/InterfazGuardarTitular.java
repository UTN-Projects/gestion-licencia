package utn.metodos_agiles;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.TitledBorder;

import entidades.Contribuyente;
import entidades.Licencia;
import entidades.Titular;
import utn.metodos_agiles.dbmanager.DBManager;

import javax.swing.border.BevelBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class InterfazGuardarTitular extends JFrame {

	private static final long serialVersionUID = 1L;
    private InterfazGenerarLicenciaContribuyente interfazLicenciaContribuyente;
	private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private JTextField textoObs;
	private Contribuyente contribuyente;
	

	public InterfazGuardarTitular() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazGuardarTitular.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setTitle("Guardar Titular");
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
        datosDelTitular.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos del Contribuyente", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        datosDelTitular.setLayout(null);
       
        
        
        JPanel datosDni = new JPanel();
        datosDni.setBounds(10, 21, 544, 93);
        datosDelTitular.add(datosDni);
        datosDni.setBackground(new Color(251, 203, 60));
        datosDni.setLayout(null);
        
        txtDni = new JTextField();
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
        		
        		contribuyente = buscarContribuyente();  
        		
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
        tablaDatos.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"", null, null},
        		
        	},
        	new String[] {
        		"DNI", "NOMBRE", "APELLIDO"
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
        
        JLabel btnEmitirTxt = new JLabel("Guardar");
        btnEmitirTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		if (interfazLicenciaContribuyente == null) {
                    if(contribuyente!=null){
                        Titular auxTitular = DBManager.buscarPorDni(contribuyente.getDni());
                        if(auxTitular == null){
                            interfazLicenciaContribuyente = new InterfazGenerarLicenciaContribuyente(contribuyente);
                            interfazLicenciaContribuyente.setVisible(true);
                            setVisible(false);
                            interfazLicenciaContribuyente.addWindowListener(new WindowAdapter() {
                                @Override
                                public void windowClosed(WindowEvent e) {
                                    interfazLicenciaContribuyente = null; // Establecer la referencia a null cuando se cierre la ventana
                                }
                            });
                        } else {
                            abrirMensajeTitularExistente();
                        }
                    } else {
                        abrirMensajeContribuyenteNoCargado();
                    }
                } else {
                    interfazLicenciaContribuyente.toFront(); // trae la ventana al frente si ya está abierta
                }
        		cerrarInterfaz();
        		
        		
        	}
        });
        btnEmitirTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnEmitirTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnEmitirTxt.setForeground(Color.WHITE);
        btnEmitirTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnEmitirTxt.setBounds(0, 0, 96, 23);
        btnEmitir.add(btnEmitirTxt);
		
	
		
			}
	
	
	private Contribuyente buscarContribuyente() {
		 
		int dni = Integer.parseInt(txtDni.getText());
		    Contribuyente contribuyente = DBManager.buscarContribuyentePorDni(dni);
		    if (contribuyente != null) {
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos
		        model.addRow(new Object[]{contribuyente.getDni(), contribuyente.getNombre(), contribuyente.getApellido()});
		    } else {
		        // Mostrar mensaje de que no se encontró el titular
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla si no se encontró el titular
		        model.addRow(new Object[]{"No encontrado", "", ""});
		        return null;
		    }
	    return contribuyente;
	}
	
	 public void cerrarInterfaz() {
	        dispose(); 
	    }

    public void abrirMensajeTitularExistente() {
        AdvertenciaTitularExistente titularExistente = new AdvertenciaTitularExistente(this);
        titularExistente.setVisible(true);
        setVisible(false);
    }

    public void abrirMensajeContribuyenteNoCargado() {
        AdvertenciaContribuyenteNoCargado contribuyenteNoCargado = new AdvertenciaContribuyenteNoCargado(this);
        contribuyenteNoCargado.setVisible(true);
        setVisible(false);
    }
	
}
