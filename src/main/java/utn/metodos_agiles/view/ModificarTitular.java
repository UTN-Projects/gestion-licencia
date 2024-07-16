package utn.metodos_agiles.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.entidades.Titular;

public class ModificarTitular extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDni;
	private JTable tablaDatos;
	private InterfazModificarDatosTitular interfazMDT = null;
    private Titular titular;
	
	public ModificarTitular() {
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
        datosDelTitular.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos del Titular", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        datosDelTitular.setLayout(null);
       
        
        
        JPanel datosTitular = new JPanel();
        datosTitular.setBounds(10, 21, 544, 93);
        datosDelTitular.add(datosTitular);
        datosTitular.setBackground(new Color(251, 203, 60));
        datosTitular.setLayout(null);
        
        txtDni = new JTextField();
        txtDni.setForeground(new Color(69, 69, 69));
        txtDni.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtDni.setDisabledTextColor(new Color(255, 255, 255));
        txtDni.setBorder(null);
        txtDni.setBackground(new Color(251, 203, 60));
        txtDni.setBounds(351, 11, 155, 26);
        datosTitular.add(txtDni);
        txtDni.setColumns(10);
        
        JLabel titularLbl = new JLabel("DNI TITULAR:");
        titularLbl.setForeground(new Color(69, 69, 69));
        titularLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        titularLbl.setBounds(37, 17, 169, 20);
        datosTitular.add(titularLbl);
        
        JSeparator separatorTxtTitular = new JSeparator();
        separatorTxtTitular.setBackground(new Color(69, 69, 69));
        separatorTxtTitular.setForeground(new Color(69, 69, 69));
        separatorTxtTitular.setBounds(351, 38, 155, 13);
        datosTitular.add(separatorTxtTitular);
        
        JPanel btnBuscar = new JPanel();
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(69, 69, 69));
        btnBuscar.setBounds(211, 56, 130, 26);
        datosTitular.add(btnBuscar);
        btnBuscar.setLayout(null);
        
        JLabel btnBuscarTxt = new JLabel("Buscar");
        btnBuscarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		titular = buscarTitular();
        		
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
        		{"", null, null, null, null},
        	},
        	new String[] {
        		"NOMBRE", "APELLIDO", "CALLE", "NRO CALLE", "ES DONANTE"
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
        
        JPanel btnModificar = new JPanel();
        btnModificar.setLayout(null);
        btnModificar.setBackground(new Color(69, 69, 69));
        btnModificar.setBounds(478, 377, 96, 23);
        contentPane.add(btnModificar);
        
        JLabel btnModificarTxt = new JLabel("Modificar");
        btnModificarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		if (interfazMDT == null || !interfazMDT.isVisible()) {

                    if(titular != null) {
                        interfazMDT = new InterfazModificarDatosTitular(titular);
                        interfazMDT.setVisible(true);

                        // Agrega un WindowListener para manejar el evento de cierre
                        interfazMDT.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                interfazMDT = null;
                            }
                        });
                    }
                    else {
                        abrirMensajeTitularNoCargado();
                    }

                } else {
                    interfazMDT.requestFocus();
                }
        		cerrarInterfaz();
            }
        		
        	
        });
        btnModificarTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnModificarTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnModificarTxt.setForeground(Color.WHITE);
        btnModificarTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnModificarTxt.setBounds(0, 0, 96, 23);
        btnModificar.add(btnModificarTxt);
		
	
		
			}
	
	public void cerrarInterfaz() {
        dispose(); 
    }
	
	
	private Titular buscarTitular() {
		 
		int dni = Integer.parseInt(txtDni.getText());
		    Titular titular = DBManager.getInstance().buscarPorDni(dni);
		    if (titular != null) {
                String esDonante;
                if(titular.getEsDonante()) esDonante = "si";
                else esDonante = "no";
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla antes de añadir nuevos datos
		        model.addRow(new Object[]{titular.getNombre(), titular.getApellido(), titular.getCalle(), titular.getNroCasa(), esDonante});
		    } else {
		        // Mostrar mensaje de que no se encontró el titular
		        DefaultTableModel model = (DefaultTableModel) tablaDatos.getModel();
		        model.setRowCount(0); // Limpiar la tabla si no se encontró el titular
		        model.addRow(new Object[]{"No encontrado", "", "", "", ""});
		        return null;
		    }
	    return titular;
	}

    public void abrirMensajeTitularNoCargado() {
        AdvertenciaTitularNoCargado titularNoCargado = new AdvertenciaTitularNoCargado(this);
        titularNoCargado.setVisible(true);
        setVisible(false);
    }
	
	
	
}
