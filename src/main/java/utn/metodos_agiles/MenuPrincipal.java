package utn.metodos_agiles;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Toolkit;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contenidoMenu;
	private InterfazFormulario interfazForms = null;
		
	public MenuPrincipal() {
		setTitle("Sistema de licencias");
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuPrincipal.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contenidoMenu = new JPanel();
		contenidoMenu.setBackground(new Color(69, 69, 69));
		contenidoMenu.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contenidoMenu);
		contenidoMenu.setLayout(null);
		
		JTabbedPane panelGeneral = new JTabbedPane(JTabbedPane.TOP);
		panelGeneral.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		panelGeneral.setBackground(new Color(69, 69, 69));
		panelGeneral.setBounds(0, 0, 584, 411);
		contenidoMenu.add(panelGeneral);
		
		JPanel tabPrincipal = new JPanel();
		tabPrincipal.setBackground(new Color(251, 203, 60));
		panelGeneral.addTab("Menu Principal", null, tabPrincipal, null);
		tabPrincipal.setLayout(null);
			 
			 JLabel txtSF = new JLabel("SANTA FE");
			 txtSF.setBackground(new Color(255, 255, 255));
			 txtSF.setForeground(new Color(251, 203, 60));
			 txtSF.setFont(new Font("Arial Black", Font.PLAIN, 37));
			 txtSF.setBounds(157, 43, 219, 45);
			 tabPrincipal.add(txtSF);
			 
			 JLabel txtCiudad = new JLabel("CIUDAD");
			 txtCiudad.setForeground(new Color(255, 255, 255));
			 txtCiudad.setFont(new Font("Arial Black", Font.PLAIN, 37));
			 txtCiudad.setBackground(Color.WHITE);
			 txtCiudad.setBounds(379, 43, 161, 45);
			 tabPrincipal.add(txtCiudad);
			 
			 JLabel imagenPuente = new JLabel("");
			 imagenPuente.setBorder(new LineBorder(new Color(69, 69, 69), 2, true));
			 imagenPuente.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/puente colgante para el sistema desenfocado.png")));
			 imagenPuente.setBounds(10, 11, 559, 77);
			 tabPrincipal.add(imagenPuente);
		
			 JPanel funcionalidades = new JPanel();
			 funcionalidades.setBackground(new Color(251, 203, 60));
			 funcionalidades.setBounds(10, 99, 559, 132);
			 tabPrincipal.add(funcionalidades);
			 funcionalidades.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Funcionalidades del Sistema", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
			 funcionalidades.setLayout(null);
			 
			 JPanel btnEmitirLicencia = new JPanel();
			 btnEmitirLicencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			 btnEmitirLicencia.setBackground(new Color(69, 69, 69));
			 btnEmitirLicencia.setBounds(10, 53, 145, 28);
			 funcionalidades.add(btnEmitirLicencia);
			 btnEmitirLicencia.setLayout(null);
			 
			 JLabel textoBtnEmitirLicencia = new JLabel("Emitir licencia");
			 textoBtnEmitirLicencia.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		
			 		if (interfazForms == null) {
	                    interfazForms = new InterfazFormulario();
	                    interfazForms.setVisible(true);
	                    interfazForms.addWindowListener(new WindowAdapter() {
	                        @Override
	                        public void windowClosed(WindowEvent e) {
	                            interfazForms = null; // Establecer la referencia a null cuando se cierre la ventana
	                        }
	                    });
	                } else {
	                    interfazForms.toFront(); // trae la ventana al frente si ya está abierta
	                }
	
			 	}
			 });
			 textoBtnEmitirLicencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			 textoBtnEmitirLicencia.setForeground(new Color(255, 255, 255));
			 textoBtnEmitirLicencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
			 textoBtnEmitirLicencia.setHorizontalAlignment(SwingConstants.CENTER);
			 textoBtnEmitirLicencia.setBounds(0, 0, 145, 28);
			 btnEmitirLicencia.add(textoBtnEmitirLicencia);
		
		
	
	}
}
