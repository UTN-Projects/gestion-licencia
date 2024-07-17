package utn.metodos_agiles.view.dialogs;

import utn.metodos_agiles.view.MenuPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;


public class MensajeLoginIncorrecto extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private MenuPrincipal menuPrincipal;
	 
	 
	public MensajeLoginIncorrecto(MenuPrincipal menuPrincipal) {
		this.menuPrincipal = menuPrincipal;
		setIconImage(Toolkit.getDefaultToolkit().getImage(MensajeExitoso.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(800, 350, 356, 125);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelExito = new JPanel();
		panelExito.setBackground(new Color(251, 203, 60));
		panelExito.setBounds(0, 0, 340, 86);
		contentPane.add(panelExito);
		panelExito.setLayout(null);
		
		JLabel txtLoginInc = new JLabel("Usuario o contraseña incorrecto");
		txtLoginInc.setForeground(new Color(69, 69, 69));
		txtLoginInc.setHorizontalAlignment(SwingConstants.CENTER);
		txtLoginInc.setBounds(10, 34, 320, 19);
		txtLoginInc.setFont(new Font("Tahoma", Font.PLAIN, 22));
		panelExito.add(txtLoginInc);
		
		
    }
	
}
