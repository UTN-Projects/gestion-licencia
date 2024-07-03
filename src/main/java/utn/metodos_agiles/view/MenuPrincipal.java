package utn.metodos_agiles.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;

public class MenuPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private static final String ESCUDO_ARG_PATH = "src/main/resources/imagenes/Escudo_Argentina.png";
	private static final String PUENTE_COLGANTE_PATH = "src/main/resources/imagenes/puente_colgante_desenfocado.png";

	private JPanel contenidoMenu;
	private InterfazFormulario interfazForms = null;
	private InterfazGuardarTitular interfazFormTitular = null;
	private InterfazLicenciasExpiradas interfazLicenciasExpiradas = null;
	private InterfazEmitirCopia interfazEmitirCopia = null;
	private JTextField fieldUsuario;
	private JPasswordField fieldContra;
		
	public MenuPrincipal() {
		setTitle("Sistema de licencias");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ESCUDO_ARG_PATH));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 300, 600, 450);
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
		
		JPanel tabLogin = new JPanel();
		tabLogin.setToolTipText("");
		panelGeneral.addTab("Login", null, tabLogin, null);
		tabLogin.setLayout(null);
		
		JPanel panelDatosLogin = new JPanel();
		panelDatosLogin.setBorder(new LineBorder(new Color(69, 69, 69), 1, true));
		panelDatosLogin.setBackground(new Color(251, 203, 60));
		panelDatosLogin.setBounds(0, 0, 313, 383);
		tabLogin.add(panelDatosLogin);
		panelDatosLogin.setLayout(null);
		
		JLabel txtUsuario = new JLabel("Usuario");
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtUsuario.setForeground(new Color(69, 69, 69));
		txtUsuario.setBounds(20, 168, 239, 26);
		panelDatosLogin.add(txtUsuario);
		
		fieldUsuario = new JTextField();
		fieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldUsuario.setBorder(null);
		fieldUsuario.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		fieldUsuario.setForeground(new Color(69, 69, 69));
		fieldUsuario.setBackground(new Color(251, 203, 60));
		fieldUsuario.setBounds(20, 193, 269, 26);
		panelDatosLogin.add(fieldUsuario);
		fieldUsuario.setColumns(10);
		
		JSeparator separatorUsuario = new JSeparator();
		separatorUsuario.setForeground(new Color(45, 45, 45));
		separatorUsuario.setBackground(new Color(69, 69, 69));
		separatorUsuario.setBounds(20, 219, 269, 20);
		panelDatosLogin.add(separatorUsuario);
		
		JLabel txtContrasenia = new JLabel("Contrase�a");
		txtContrasenia.setForeground(new Color(69, 69, 69));
		txtContrasenia.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtContrasenia.setBounds(20, 250, 239, 26);
		panelDatosLogin.add(txtContrasenia);
		
		JSeparator separatorContra = new JSeparator();
		separatorContra.setForeground(new Color(69, 69, 69));
		separatorContra.setBackground(new Color(69, 69, 69));
		separatorContra.setBounds(20, 302, 269, 20);
		panelDatosLogin.add(separatorContra);
		
		fieldContra = new JPasswordField();
		fieldContra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		fieldContra.setForeground(new Color(45, 45, 45));
		fieldContra.setBorder(null);
		fieldContra.setBackground(new Color(251, 203, 60));
		fieldContra.setBounds(20, 277, 269, 26);
		panelDatosLogin.add(fieldContra);
		
		JLabel utnImagen = new JLabel("");
		utnImagen.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/png utn escalado.png")));
		utnImagen.setBounds(10, 11, 90, 94);
		panelDatosLogin.add(utnImagen);
		
		JLabel metodosTxt = new JLabel("METODOS");
		metodosTxt.setForeground(new Color(69, 69, 69));
		metodosTxt.setFont(new Font("Arial Black", Font.PLAIN, 30));
		metodosTxt.setBounds(110, 11, 192, 39);
		panelDatosLogin.add(metodosTxt);
		
		JLabel agilesTxt = new JLabel("AGILES");
		agilesTxt.setForeground(new Color(69, 69, 69));
		agilesTxt.setVerticalAlignment(SwingConstants.BOTTOM);
		agilesTxt.setFont(new Font("Arial Black", Font.PLAIN, 30));
		agilesTxt.setBounds(110, 46, 192, 39);
		panelDatosLogin.add(agilesTxt);
		
		JPanel btnIngresar = new JPanel();
		btnIngresar.setBackground(new Color(69, 69, 69));
		btnIngresar.setBounds(83, 333, 136, 26);
		panelDatosLogin.add(btnIngresar);
		btnIngresar.setLayout(null);
		
		JLabel txtBtnIngresar = new JLabel("Ingresar");
		txtBtnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txtBtnIngresar.setHorizontalAlignment(SwingConstants.CENTER);
		txtBtnIngresar.setForeground(new Color(255, 255, 255));
		txtBtnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		txtBtnIngresar.setBounds(0, 0, 136, 26);
		btnIngresar.add(txtBtnIngresar);
		
		JLabel loginTxt = new JLabel("Ingrese un usuario:");
		loginTxt.setVerticalAlignment(SwingConstants.BOTTOM);
		loginTxt.setForeground(new Color(69, 69, 69));
		loginTxt.setFont(new Font("Arial Black", Font.PLAIN, 17));
		loginTxt.setBounds(20, 118, 192, 39);
		panelDatosLogin.add(loginTxt);
		
		JLabel imagenLogin = new JLabel("");
		imagenLogin.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/imagen para el login.png")));
		imagenLogin.setBounds(311, 0, 268, 383);
		tabLogin.add(imagenLogin);
		
		JPanel tabMenuPrincipal = new JPanel();
		tabMenuPrincipal.setBackground(new Color(251, 203, 60));
		panelGeneral.addTab("Menu Principal", null, tabMenuPrincipal, null);
		tabMenuPrincipal.setLayout(null);
			 
			 JLabel txtSF = new JLabel("SANTA FE");
			 txtSF.setBackground(new Color(255, 255, 255));
			 txtSF.setForeground(new Color(251, 203, 60));
			 txtSF.setFont(new Font("Arial Black", Font.PLAIN, 37));
			 txtSF.setBounds(157, 43, 219, 45);
			 tabMenuPrincipal.add(txtSF);
			 
			 JLabel txtCiudad = new JLabel("CIUDAD");
			 txtCiudad.setForeground(new Color(255, 255, 255));
			 txtCiudad.setFont(new Font("Arial Black", Font.PLAIN, 37));
			 txtCiudad.setBackground(Color.WHITE);
			 txtCiudad.setBounds(379, 43, 161, 45);
			 tabMenuPrincipal.add(txtCiudad);
			 
			 JLabel imagenPuente = new JLabel("");
			 imagenPuente.setBorder(new LineBorder(new Color(69, 69, 69), 2, true));
			 imagenPuente.setIcon(new ImageIcon(PUENTE_COLGANTE_PATH));
			 imagenPuente.setBounds(10, 11, 559, 77);
			 tabMenuPrincipal.add(imagenPuente);
		
			 JPanel funcionalidades = new JPanel();
			 funcionalidades.setBackground(new Color(251, 203, 60));
			 funcionalidades.setBounds(10, 99, 559, 132);
			 tabMenuPrincipal.add(funcionalidades);
			 funcionalidades.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Funcionalidades del Sistema", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
			 funcionalidades.setLayout(null);
			 
			 JPanel btnEmitirLicencia = new JPanel();
			 btnEmitirLicencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			 btnEmitirLicencia.setBackground(new Color(69, 69, 69));
			 btnEmitirLicencia.setBounds(10, 53, 145, 28);
			 funcionalidades.add(btnEmitirLicencia);
			 btnEmitirLicencia.setLayout(null);

			 JPanel btnGuardarTitular = new JPanel();
			 btnGuardarTitular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			 btnGuardarTitular.setBackground(new Color(69, 69, 69));
			 btnGuardarTitular.setBounds(200, 53, 145, 28);
			 funcionalidades.add(btnGuardarTitular);
			 btnGuardarTitular.setLayout(null);

			JPanel btnLicenciasExpiradas = new JPanel();
			btnLicenciasExpiradas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnLicenciasExpiradas.setBackground(new Color(69, 69, 69));
			btnLicenciasExpiradas.setBounds(390, 53, 145, 28);
			funcionalidades.add(btnLicenciasExpiradas);
			btnLicenciasExpiradas.setLayout(null);
			
			JPanel btnEmitirCopia = new JPanel();
			btnEmitirCopia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnEmitirCopia.setBackground(new Color(69, 69, 69));
			btnEmitirCopia.setBounds(10, 93, 145, 28);
			funcionalidades.add(btnEmitirCopia);
			btnEmitirCopia.setLayout(null);
			

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

			 JLabel textoBtnGuardarTitular = new JLabel("Guardar Titular");
			 textoBtnGuardarTitular.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		
			 		if (interfazFormTitular == null) {
	                    interfazFormTitular = new InterfazGuardarTitular();
	                    interfazFormTitular.setVisible(true);
	                    interfazFormTitular.addWindowListener(new WindowAdapter() {
	                        @Override
	                        public void windowClosed(WindowEvent e) {
	                            interfazFormTitular = null; // Establecer la referencia a null cuando se cierre la ventana
	                        }
	                    });
	                } else {
	                    interfazFormTitular.toFront(); // trae la ventana al frente si ya está abierta
	                }
	
			 	}
			 });

			JLabel textoBtnLicenciasExpiradas = new JLabel("Licencias Expiradas");
			textoBtnLicenciasExpiradas.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {

					if (interfazLicenciasExpiradas == null) {
						interfazLicenciasExpiradas = new InterfazLicenciasExpiradas();
						interfazLicenciasExpiradas.setVisible(true);
						interfazLicenciasExpiradas.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosed(WindowEvent e) {
								interfazLicenciasExpiradas = null; // Establecer la referencia a null cuando se cierre la ventana
							}
						});
					} else {
						interfazLicenciasExpiradas.toFront(); // trae la ventana al frente si ya está abierta
					}

				}
			});
			
			JLabel textoBtnEmitirCopia = new JLabel("Emitir Copia");
			textoBtnEmitirCopia.addMouseListener(new MouseAdapter() {
			 	@Override
			 	public void mouseClicked(MouseEvent e) {
			 		
			 		if (interfazEmitirCopia == null) {
			 			interfazEmitirCopia = new InterfazEmitirCopia();
			 			interfazEmitirCopia.setVisible(true);
			 			interfazEmitirCopia.addWindowListener(new WindowAdapter() {
	                        @Override
	                        public void windowClosed(WindowEvent e) {
	                        	interfazEmitirCopia = null; // Establecer la referencia a null cuando se cierre la ventana
	                        }
	                    });
	                } else {
	                	interfazEmitirCopia.toFront(); // trae la ventana al frente si ya está abierta
	                }
	
			 	}
			 });
			
			 textoBtnEmitirLicencia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			 textoBtnEmitirLicencia.setForeground(new Color(255, 255, 255));
			 textoBtnEmitirLicencia.setFont(new Font("Tahoma", Font.PLAIN, 15));
			 textoBtnEmitirLicencia.setHorizontalAlignment(SwingConstants.CENTER);
			 textoBtnEmitirLicencia.setBounds(0, 0, 145, 28);
			 btnEmitirLicencia.add(textoBtnEmitirLicencia);

			 textoBtnGuardarTitular.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			 textoBtnGuardarTitular.setForeground(new Color(255, 255, 255));
			 textoBtnGuardarTitular.setFont(new Font("Tahoma", Font.PLAIN, 15));
			 textoBtnGuardarTitular.setHorizontalAlignment(SwingConstants.CENTER);
			 textoBtnGuardarTitular.setBounds(0, 0, 145, 28);
			 btnGuardarTitular.add(textoBtnGuardarTitular);

			textoBtnLicenciasExpiradas.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			textoBtnLicenciasExpiradas.setForeground(new Color(255, 255, 255));
			textoBtnLicenciasExpiradas.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textoBtnLicenciasExpiradas.setHorizontalAlignment(SwingConstants.CENTER);
			textoBtnLicenciasExpiradas.setBounds(0, 0, 145, 28);
			btnLicenciasExpiradas.add(textoBtnLicenciasExpiradas);
			
			textoBtnEmitirCopia.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			textoBtnEmitirCopia.setForeground(new Color(255, 255, 255));
			textoBtnEmitirCopia.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textoBtnEmitirCopia.setHorizontalAlignment(SwingConstants.CENTER);
			textoBtnEmitirCopia.setBounds(0, 0, 145, 28);
			btnEmitirCopia.add(textoBtnEmitirCopia);
	
	}
}
