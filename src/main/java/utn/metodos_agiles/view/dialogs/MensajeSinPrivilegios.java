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


public class MensajeSinPrivilegios extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private MenuPrincipal menuPrincipal;
	 
	 
	public MensajeSinPrivilegios(MenuPrincipal menuPrincipal) {
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
		
		JLabel txtSinPr = new JLabel("NO TIENE LOS PRIVILEGIOS");
		txtSinPr.setForeground(new Color(69, 69, 69));
		txtSinPr.setHorizontalAlignment(SwingConstants.CENTER);
		txtSinPr.setBounds(10, 14, 320, 25);
		txtSinPr.setFont(new Font("Tahoma", Font.PLAIN, 20));
		panelExito.add(txtSinPr);
		
		JLabel lblParaAcceder = new JLabel("PARA ACCEDER");
		lblParaAcceder.setHorizontalAlignment(SwingConstants.CENTER);
		lblParaAcceder.setForeground(new Color(69, 69, 69));
		lblParaAcceder.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblParaAcceder.setBounds(10, 50, 320, 25);
		panelExito.add(lblParaAcceder);
		
		
    }
}
