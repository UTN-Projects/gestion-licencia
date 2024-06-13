package utn.metodos_agiles.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;

public class MensajeExitoso extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	 private InterfazFormulario interfazFormulario;
	 
	public MensajeExitoso(InterfazFormulario interfazFormulario) {
		this.interfazFormulario = interfazFormulario;
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
		
		JLabel txtExitoso = new JLabel("Se ha realizado con exito");
		txtExitoso.setForeground(new Color(69, 69, 69));
		txtExitoso.setHorizontalAlignment(SwingConstants.CENTER);
		txtExitoso.setBounds(101, 34, 229, 19);
		txtExitoso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelExito.add(txtExitoso);
		
		JLabel tildeVerde = new JLabel("");
		tildeVerde.setIcon(new ImageIcon(MensajeExitoso.class.getResource("/imagenes/tilde verde.png")));
		tildeVerde.setBounds(10, 11, 81, 64);
		panelExito.add(tildeVerde);
		
		addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                if (interfazFormulario != null) {
                    interfazFormulario.cerrarInterfaz(); 
                }
            }
        });
    }
	
}
