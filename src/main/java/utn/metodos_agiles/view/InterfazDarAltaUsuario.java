package utn.metodos_agiles.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;

import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.entidades.Usuario;

public class InterfazDarAltaUsuario extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCorreo;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField textTel;
	private JTextField textContra;
	
	
	public InterfazDarAltaUsuario() {
		
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazLicenciasVigentes.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setTitle("Agregar usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 600, 450);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 203, 60));
		contentPane.setToolTipText("");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		  
        JPanel datosDelUsuario = new JPanel();
        datosDelUsuario.setBackground(new Color(251, 203, 60));
        datosDelUsuario.setBounds(10, 103, 564, 177);
        contentPane.add(datosDelUsuario);
        datosDelUsuario.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos del Usuario", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        datosDelUsuario.setLayout(null);
       
        
        
        JPanel datosVigencia = new JPanel();
        datosVigencia.setBounds(10, 21, 544, 145);
        datosDelUsuario.add(datosVigencia);
        datosVigencia.setBackground(new Color(251, 203, 60));
        datosVigencia.setLayout(null);
        
        txtCorreo = new JTextField();
        txtCorreo.setForeground(new Color(69, 69, 69));
        txtCorreo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtCorreo.setDisabledTextColor(new Color(255, 255, 255));
        txtCorreo.setBorder(null);
        txtCorreo.setBackground(new Color(251, 203, 60));
        txtCorreo.setBounds(190, 0, 147, 26);
        datosVigencia.add(txtCorreo);
        txtCorreo.setColumns(10);
        
        JLabel correoLbl = new JLabel("CORREO ELECTRONICO:");
        correoLbl.setForeground(new Color(69, 69, 69));
        correoLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        correoLbl.setBounds(20, 11, 165, 20);
        datosVigencia.add(correoLbl);
        
        JSeparator separatorTxtCorreo = new JSeparator();
        separatorTxtCorreo.setBackground(new Color(69, 69, 69));
        separatorTxtCorreo.setForeground(new Color(69, 69, 69));
        separatorTxtCorreo.setBounds(190, 27, 147, 13);
        datosVigencia.add(separatorTxtCorreo);
        
        txtNombre = new JTextField();
        txtNombre.setForeground(new Color(69, 69, 69));
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setDisabledTextColor(Color.WHITE);
        txtNombre.setColumns(10);
        txtNombre.setBorder(null);
        txtNombre.setBackground(new Color(251, 203, 60));
        txtNombre.setBounds(94, 43, 112, 26);
        datosVigencia.add(txtNombre);
        
        JSeparator separatorTxtNombre = new JSeparator();
        separatorTxtNombre.setForeground(new Color(69, 69, 69));
        separatorTxtNombre.setBackground(new Color(69, 69, 69));
        separatorTxtNombre.setBounds(94, 70, 112, 13);
        datosVigencia.add(separatorTxtNombre);
        
        JLabel nombreLabel = new JLabel("NOMBRE:");
        nombreLabel.setForeground(new Color(69, 69, 69));
        nombreLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nombreLabel.setBounds(20, 54, 69, 20);
        datosVigencia.add(nombreLabel);
        
        JSeparator separatorTxtApe = new JSeparator();
        separatorTxtApe.setForeground(new Color(69, 69, 69));
        separatorTxtApe.setBackground(new Color(69, 69, 69));
        separatorTxtApe.setBounds(94, 118, 112, 13);
        datosVigencia.add(separatorTxtApe);
        
        txtApellido = new JTextField();
        txtApellido.setForeground(new Color(69, 69, 69));
        txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtApellido.setDisabledTextColor(Color.WHITE);
        txtApellido.setColumns(10);
        txtApellido.setBorder(null);
        txtApellido.setBackground(new Color(251, 203, 60));
        txtApellido.setBounds(94, 91, 112, 26);
        datosVigencia.add(txtApellido);
        
        JLabel apellidoLabel = new JLabel("APELLIDO:");
        apellidoLabel.setForeground(new Color(69, 69, 69));
        apellidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        apellidoLabel.setBounds(20, 100, 69, 20);
        datosVigencia.add(apellidoLabel);
        
        JLabel lblTel = new JLabel("TELEFONO:");
        lblTel.setHorizontalAlignment(SwingConstants.TRAILING);
        lblTel.setForeground(new Color(69, 69, 69));
        lblTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblTel.setBounds(257, 51, 132, 20);
        datosVigencia.add(lblTel);
        
        JLabel lblContra = new JLabel("CONTRASEÑA:");
        lblContra.setHorizontalAlignment(SwingConstants.TRAILING);
        lblContra.setForeground(new Color(69, 69, 69));
        lblContra.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblContra.setBounds(257, 100, 132, 20);
        datosVigencia.add(lblContra);
		
		textTel = new JTextField();
		textTel.setForeground(new Color(69, 69, 69));
		textTel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textTel.setDisabledTextColor(Color.WHITE);
		textTel.setColumns(10);
		textTel.setBorder(null);
		textTel.setBackground(new Color(251, 203, 60));
		textTel.setBounds(399, 43, 112, 26);
		datosVigencia.add(textTel);
		
		JSeparator separatorTxtTel = new JSeparator();
		separatorTxtTel.setForeground(new Color(69, 69, 69));
		separatorTxtTel.setBackground(new Color(69, 69, 69));
		separatorTxtTel.setBounds(399, 70, 112, 13);
		datosVigencia.add(separatorTxtTel);
		
		textContra = new JTextField();
		textContra.setForeground(new Color(69, 69, 69));
		textContra.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textContra.setDisabledTextColor(Color.WHITE);
		textContra.setColumns(10);
		textContra.setBorder(null);
		textContra.setBackground(new Color(251, 203, 60));
		textContra.setBounds(399, 91, 112, 26);
		datosVigencia.add(textContra);
		
		JSeparator separatorTxtContra = new JSeparator();
		separatorTxtContra.setForeground(new Color(69, 69, 69));
		separatorTxtContra.setBackground(new Color(69, 69, 69));
		separatorTxtContra.setBounds(399, 118, 112, 13);
		datosVigencia.add(separatorTxtContra);
        
       
        
        
        JPanel btnAgregar = new JPanel();
        btnAgregar.setBackground(new Color(69, 69, 69));
        btnAgregar.setBounds(478, 377, 96, 23);
        contentPane.add(btnAgregar);
        btnAgregar.setLayout(null);
        
        JLabel btnAgregarTxt = new JLabel("Agregar");
        btnAgregarTxt.setBounds(0, 0, 96, 23);
        btnAgregar.add(btnAgregarTxt);
        btnAgregarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		cargarDatosUsuario();
        		
        		abrirMensajeExitoso();
        		
        		cerrarInterfaz();
        	}
        });
        btnAgregarTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnAgregarTxt.setForeground(new Color(255, 255, 255));
        btnAgregarTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnAgregarTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
        JPanel btnCancelar_1 = new JPanel();
        btnCancelar_1.setLayout(null);
        btnCancelar_1.setBackground(new Color(69, 69, 69));
        btnCancelar_1.setBounds(372, 377, 96, 23);
        contentPane.add(btnCancelar_1);
        
        JLabel btnCancelarTxt_1 = new JLabel("Cancelar");
        btnCancelarTxt_1.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		cerrarInterfaz();
        		
        	}
        });
        btnCancelarTxt_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelarTxt_1.setHorizontalAlignment(SwingConstants.CENTER);
        btnCancelarTxt_1.setForeground(Color.WHITE);
        btnCancelarTxt_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
        btnCancelarTxt_1.setBounds(0, 0, 96, 23);
        btnCancelar_1.add(btnCancelarTxt_1);

        
       
    }
	
	public void cerrarInterfaz() {
        dispose(); 
    }

	public void cargarDatosUsuario() {
        String correo = txtCorreo.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = textTel.getText();
        String contrasena = textContra.getText();

        Usuario usuario = new Usuario(correo, nombre, apellido, telefono, contrasena);
        DBManager.getInstance().cargarUsuario(usuario);
        
    }
	
	public void abrirMensajeExitoso() {
		MensajeExitosoUsuario mensajeExitosoU = new MensajeExitosoUsuario(this);
		mensajeExitosoU.setVisible(true);
		setVisible(false); 
	}
}









