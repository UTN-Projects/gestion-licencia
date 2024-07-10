package utn.metodos_agiles.view;

import utn.metodos_agiles.view.guardartitular.InterfazGuardarTitular;
import utn.metodos_agiles.view.modificarusuario.ModificarUsuario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.Toolkit;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;



public class InterfazOpcionesAvanzadas extends JFrame {


	private static final long serialVersionUID = 1L;
    private InterfazDarAltaUsuario interfazDAU = null;
    private ModificarUsuario modificarU = null;
	private JPanel contentPane;
	

	public InterfazOpcionesAvanzadas() {
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
		
        JPanel OpcionesAdmin = new JPanel();
        OpcionesAdmin.setBackground(new Color(251, 203, 60));
        OpcionesAdmin.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Opciones del Administrador", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        OpcionesAdmin.setBounds(10, 145, 564, 93);
        OpcionesAdmin.setLayout(null);

      
        contentPane.add(OpcionesAdmin);
        
        JPanel btnAgregar = new JPanel();
        btnAgregar.setLayout(null);
        btnAgregar.setBackground(new Color(69, 69, 69));
        btnAgregar.setBounds(48, 33, 137, 37);
        OpcionesAdmin.add(btnAgregar);
        
        JLabel lblAgregarUsuario = new JLabel("Agregar usuario");
        lblAgregarUsuario.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		if (interfazDAU == null || !interfazDAU.isVisible()) {
                    interfazDAU = new InterfazDarAltaUsuario();
                    interfazDAU.setVisible(true);

                    // Agrega un WindowListener para manejar el evento de cierre
                    interfazDAU.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            interfazDAU = null;
                        }
                    });

                } else {
                    interfazDAU.requestFocus();
                }
            }
        		
        	
        });
        lblAgregarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblAgregarUsuario.setBounds(0, 0, 137, 37);
        btnAgregar.add(lblAgregarUsuario);
        lblAgregarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblAgregarUsuario.setForeground(Color.WHITE);
        lblAgregarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JPanel btnModificarUsu = new JPanel();
        btnModificarUsu.setLayout(null);
        btnModificarUsu.setBackground(new Color(69, 69, 69));
        btnModificarUsu.setBounds(379, 33, 137, 37);
        OpcionesAdmin.add(btnModificarUsu);
        
        JLabel lblModificarUsuario = new JLabel("Modificar usuario");
        lblModificarUsuario.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		if (modificarU == null || !modificarU.isVisible()) {
        			modificarU = new ModificarUsuario();
        			modificarU.setVisible(true);

                    // Agrega un WindowListener para manejar el evento de cierre
        			modificarU.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                        	modificarU = null;
                        }
                    });

                } else {
                	modificarU.requestFocus();
                }
            }
        		
        		
        		
        		
        	
        });
        
        lblModificarUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblModificarUsuario.setHorizontalAlignment(SwingConstants.CENTER);
        lblModificarUsuario.setForeground(Color.WHITE);
        lblModificarUsuario.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblModificarUsuario.setBounds(0, 0, 137, 37);
        btnModificarUsu.add(lblModificarUsuario);
        
        JPanel btnAtras = new JPanel();
        btnAtras.setBackground(new Color(69, 69, 69));
        btnAtras.setBounds(466, 371, 108, 29);
        contentPane.add(btnAtras);
        btnAtras.setLayout(null);
        
        JLabel btnAtrasTxt = new JLabel("Atras");
        btnAtrasTxt.setBounds(0, 0, 106, 29);
        btnAtras.add(btnAtrasTxt);
        btnAtrasTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		cerrarInterfaz();
        	}
        });
        btnAtrasTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAtrasTxt.setForeground(new Color(255, 255, 255));
        btnAtrasTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnAtrasTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
    }
	
	 public void cerrarInterfaz() {
	        dispose(); 
	    }
}
