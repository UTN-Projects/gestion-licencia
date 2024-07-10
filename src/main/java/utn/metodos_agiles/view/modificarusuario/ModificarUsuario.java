package utn.metodos_agiles.view.modificarusuario;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

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

import utn.metodos_agiles.controller.UsuarioController;
import utn.metodos_agiles.model.entidades.Usuario;
import utn.metodos_agiles.view.guardartitular.InterfazGuardarTitular;
import utn.metodos_agiles.view.InterfazModificarDatosUsuario;

public class ModificarUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNusuario;
	private JTable tablaDatos;
	private InterfazModificarDatosUsuario interfazMDU = null;
	
	public ModificarUsuario() {
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
        datosDelTitular.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos del Usuario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        datosDelTitular.setLayout(null);
       
        
        
        JPanel datosUsuario = new JPanel();
        datosUsuario.setBounds(10, 21, 544, 93);
        datosDelTitular.add(datosUsuario);
        datosUsuario.setBackground(new Color(251, 203, 60));
        datosUsuario.setLayout(null);
        
        txtNusuario = new JTextField();
        txtNusuario.setForeground(new Color(69, 69, 69));
        txtNusuario.setFont(new Font("Tahoma", Font.PLAIN, 17));
        txtNusuario.setDisabledTextColor(new Color(255, 255, 255));
        txtNusuario.setBorder(null);
        txtNusuario.setBackground(new Color(251, 203, 60));
        txtNusuario.setBounds(351, 11, 155, 26);
        datosUsuario.add(txtNusuario);
        txtNusuario.setColumns(10);
        
        JLabel nUsuarioLbl = new JLabel("NOMBRE DE USUARIO:");
        nUsuarioLbl.setForeground(new Color(69, 69, 69));
        nUsuarioLbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nUsuarioLbl.setBounds(37, 17, 169, 20);
        datosUsuario.add(nUsuarioLbl);
        
        JSeparator separatorTxtUsuario = new JSeparator();
        separatorTxtUsuario.setBackground(new Color(69, 69, 69));
        separatorTxtUsuario.setForeground(new Color(69, 69, 69));
        separatorTxtUsuario.setBounds(351, 38, 155, 13);
        datosUsuario.add(separatorTxtUsuario);
        
        JPanel btnBuscar = new JPanel();
        btnBuscar.setBorder(null);
        btnBuscar.setBackground(new Color(69, 69, 69));
        btnBuscar.setBounds(211, 56, 130, 26);
        datosUsuario.add(btnBuscar);
        btnBuscar.setLayout(null);
        
        JLabel btnBuscarTxt = new JLabel("Buscar");
        btnBuscarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		String nu = txtNusuario.getText();
        		
        		buscarPorNU(nu);
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
        tablaDatos.setModel(new ModificarUsuarioTableModel(List.of()));
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
        		
        		
        		if (interfazMDU == null || !interfazMDU.isVisible()) {
                    int selectedRow = tablaDatos.getSelectedRow();

                    if(selectedRow != -1) {

                        interfazMDU = new InterfazModificarDatosUsuario(UsuarioController.getInstance()
                                .buscarUsuario(txtNusuario.getText()));
                        interfazMDU.setVisible(true);

                        // Agrega un WindowListener para manejar el evento de cierre
                        interfazMDU.addWindowListener(new WindowAdapter() {
                            @Override
                            public void windowClosing(WindowEvent e) {
                                interfazMDU = null;
                            }
                        });
                    }

                } else {
                    interfazMDU.requestFocus();
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
	
	
	public Usuario buscarPorNU(String nombreUs) {
		
		Usuario usuarioObj = UsuarioController.getInstance().buscarUsuario(nombreUs);
		if (usuarioObj != null) {
            tablaDatos.setModel(new ModificarUsuarioTableModel(List.of(usuarioObj)));
	    } else {
	        // todo: Mostrar mensaje de que no se encontró el titular
	    }
		
		return usuarioObj;
	}
	
	
	
}


