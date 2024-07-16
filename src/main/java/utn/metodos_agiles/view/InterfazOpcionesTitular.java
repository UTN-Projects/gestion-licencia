package utn.metodos_agiles.view;

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



public class InterfazOpcionesTitular extends JFrame {


	private static final long serialVersionUID = 1L;
    private InterfazGuardarTitular interfazDAT = null;
    private ModificarTitular modificarT = null;
	private JPanel contentPane;
	

	public InterfazOpcionesTitular() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazGuardarTitular.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setTitle("Opciones Titular");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 600, 450);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 203, 60));
		contentPane.setToolTipText("");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
        JPanel OpcionesAdmin = new JPanel();
        OpcionesAdmin.setBackground(new Color(251, 203, 60));
        OpcionesAdmin.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Opciones", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        OpcionesAdmin.setBounds(10, 145, 564, 93);
        OpcionesAdmin.setLayout(null);

      
        contentPane.add(OpcionesAdmin);
        
        JPanel btnAgregar = new JPanel();
        btnAgregar.setLayout(null);
        btnAgregar.setBackground(new Color(69, 69, 69));
        btnAgregar.setBounds(48, 33, 137, 37);
        OpcionesAdmin.add(btnAgregar);
        
        JLabel lblAgregarTitular = new JLabel("Guardar Titular");
        lblAgregarTitular.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		if (interfazDAT == null || !interfazDAT.isVisible()) {
                    interfazDAT = new InterfazGuardarTitular();
                    interfazDAT.setVisible(true);

                    // Agrega un WindowListener para manejar el evento de cierre
                    interfazDAT.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            interfazDAT = null;
                        }
                    });

                } else {
                    interfazDAT.requestFocus();
                }
            }
        		
        	
        });
        lblAgregarTitular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblAgregarTitular.setBounds(0, 0, 137, 37);
        btnAgregar.add(lblAgregarTitular);
        lblAgregarTitular.setHorizontalAlignment(SwingConstants.CENTER);
        lblAgregarTitular.setForeground(Color.WHITE);
        lblAgregarTitular.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JPanel btnModificarTit = new JPanel();
        btnModificarTit.setLayout(null);
        btnModificarTit.setBackground(new Color(69, 69, 69));
        btnModificarTit.setBounds(379, 33, 137, 37);
        OpcionesAdmin.add(btnModificarTit);
        
        JLabel lblModificarTitular = new JLabel("Modificar Titular");
        lblModificarTitular.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		
        		if (modificarT == null || !modificarT.isVisible()) {
        			modificarT = new ModificarTitular();
        			modificarT.setVisible(true);

                    // Agrega un WindowListener para manejar el evento de cierre
        			modificarT.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                        	modificarT = null;
                        }
                    });

                } else {
                	modificarT.requestFocus();
                }
            }
        		
        		
        		
        		
        	
        });
        
        lblModificarTitular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lblModificarTitular.setHorizontalAlignment(SwingConstants.CENTER);
        lblModificarTitular.setForeground(Color.WHITE);
        lblModificarTitular.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lblModificarTitular.setBounds(0, 0, 137, 37);
        btnModificarTit.add(lblModificarTitular);
        
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
