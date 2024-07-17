
package utn.metodos_agiles.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;

import utn.metodos_agiles.controller.LicenciaController;
import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.model.entidades.Titular;
import utn.metodos_agiles.view.dialogs.MensajeModificadoExito;
import utn.metodos_agiles.view.licenciasvigentes.InterfazLicenciasVigentes;

public class InterfazModificarDatosTitular extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtCalle;
	private JTextField textNro;
	private JCheckBox textEsDonante;
	private Titular titularActual;
	
	
	public InterfazModificarDatosTitular(Titular titularMod) {
		this.titularActual = titularMod;
		setIconImage(Toolkit.getDefaultToolkit().getImage(InterfazLicenciasVigentes.class.getResource("/imagenes/Escudo_Argentina.png")));
		setResizable(false);
		setTitle("Modificar usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(700, 300, 600, 450);
		
		
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(251, 203, 60));
		contentPane.setToolTipText("");

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		  
        JPanel datosDelTitular = new JPanel();
        datosDelTitular.setBackground(new Color(251, 203, 60));
        datosDelTitular.setBounds(10, 103, 564, 177);
        contentPane.add(datosDelTitular);
        datosDelTitular.setBorder(new TitledBorder(new LineBorder(new Color(69, 69, 69), 2, true), "Datos del Titular", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(69, 69, 69)));
        datosDelTitular.setLayout(null);
       
        
        
        JPanel datosVigencia = new JPanel();
        datosVigencia.setBounds(10, 21, 544, 145);
        datosDelTitular.add(datosVigencia);
        datosVigencia.setBackground(new Color(251, 203, 60));
        datosVigencia.setLayout(null);
        
        txtNombre = new JTextField();
        txtNombre.setForeground(new Color(69, 69, 69));
        txtNombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtNombre.setDisabledTextColor(new Color(255, 255, 255));
        txtNombre.setBorder(null);
        txtNombre.setBackground(new Color(251, 203, 60));
        txtNombre.setBounds(94, 0, 112, 26);
        datosVigencia.add(txtNombre);
        txtNombre.setColumns(10);
        txtNombre.setText(titularActual.getNombre());
        
        JLabel nombreLbl = new JLabel("NOMBRE:");
        nombreLbl.setForeground(new Color(69, 69, 69));
        nombreLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
        nombreLbl.setBounds(20, 11, 165, 20);
        datosVigencia.add(nombreLbl);
        
        JSeparator separatorTxtNombre = new JSeparator();
        separatorTxtNombre.setBackground(new Color(69, 69, 69));
        separatorTxtNombre.setForeground(new Color(69, 69, 69));
        separatorTxtNombre.setBounds(94, 27, 112, 13);
        datosVigencia.add(separatorTxtNombre);
        
        txtApellido = new JTextField();
        txtApellido.setForeground(new Color(69, 69, 69));
        txtApellido.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtApellido.setDisabledTextColor(Color.WHITE);
        txtApellido.setColumns(10);
        txtApellido.setBorder(null);
        txtApellido.setBackground(new Color(251, 203, 60));
        txtApellido.setBounds(94, 43, 112, 26);
        datosVigencia.add(txtApellido);
        txtApellido.setText(titularActual.getApellido());
        
        JSeparator separatorTxtApellido = new JSeparator();
        separatorTxtApellido.setForeground(new Color(69, 69, 69));
        separatorTxtApellido.setBackground(new Color(69, 69, 69));
        separatorTxtApellido.setBounds(94, 70, 112, 13);
        datosVigencia.add(separatorTxtApellido);
        
        JLabel apellidoLabel = new JLabel("APELLIDO:");
        apellidoLabel.setForeground(new Color(69, 69, 69));
        apellidoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        apellidoLabel.setBounds(20, 54, 69, 20);
        datosVigencia.add(apellidoLabel);
        
        JSeparator separatorTxtCalle = new JSeparator();
        separatorTxtCalle.setForeground(new Color(69, 69, 69));
        separatorTxtCalle.setBackground(new Color(69, 69, 69));
        separatorTxtCalle.setBounds(94, 118, 112, 13);
        datosVigencia.add(separatorTxtCalle);
        
        txtCalle = new JTextField();
        txtCalle.setForeground(new Color(69, 69, 69));
        txtCalle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtCalle.setDisabledTextColor(Color.WHITE);
        txtCalle.setColumns(10);
        txtCalle.setBorder(null);
        txtCalle.setBackground(new Color(251, 203, 60));
        txtCalle.setBounds(94, 91, 112, 26);
        datosVigencia.add(txtCalle);
        txtCalle.setText(titularActual.getCalle());
        
        JLabel calleLabel = new JLabel("CALLE:");
        calleLabel.setForeground(new Color(69, 69, 69));
        calleLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        calleLabel.setBounds(20, 100, 69, 20);
        datosVigencia.add(calleLabel);
        
        JLabel lblNro = new JLabel("ALTURA CALLE:");
        lblNro.setHorizontalAlignment(SwingConstants.TRAILING);
        lblNro.setForeground(new Color(69, 69, 69));
        lblNro.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNro.setBounds(257, 51, 132, 20);
        datosVigencia.add(lblNro);
        
        JLabel lblEsDonante = new JLabel("ES DONANTE:");
        lblEsDonante.setHorizontalAlignment(SwingConstants.TRAILING);
        lblEsDonante.setForeground(new Color(69, 69, 69));
        lblEsDonante.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblEsDonante.setBounds(257, 100, 132, 20);
        datosVigencia.add(lblEsDonante);
		
		textNro = new JTextField();
        ((AbstractDocument) textNro.getDocument()).setDocumentFilter(new NumericAndLengthFilter(5));
		textNro.setForeground(new Color(69, 69, 69));
		textNro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textNro.setDisabledTextColor(Color.WHITE);
		textNro.setColumns(10);
		textNro.setBorder(null);
		textNro.setBackground(new Color(251, 203, 60));
		textNro.setBounds(399, 43, 112, 26);
		datosVigencia.add(textNro);
        textNro.setText(String.valueOf(titularActual.getNroCasa()));
		
		JSeparator separatorTxtNro = new JSeparator();
		separatorTxtNro.setForeground(new Color(69, 69, 69));
		separatorTxtNro.setBackground(new Color(69, 69, 69));
		separatorTxtNro.setBounds(399, 70, 112, 13);
		datosVigencia.add(separatorTxtNro);
		
		textEsDonante = new JCheckBox();
		textEsDonante.setForeground(new Color(69, 69, 69));
		textEsDonante.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textEsDonante.setBorder(null);
		textEsDonante.setBackground(new Color(251, 203, 60));
		textEsDonante.setBounds(399, 91, 112, 26);
		datosVigencia.add(textEsDonante);
        textEsDonante.setSelected(titularActual.getEsDonante());

		JSeparator separatorTxtEsDonante = new JSeparator();
		separatorTxtEsDonante.setForeground(new Color(69, 69, 69));
		separatorTxtEsDonante.setBackground(new Color(69, 69, 69));
		separatorTxtEsDonante.setBounds(399, 118, 112, 13);
		datosVigencia.add(separatorTxtEsDonante);
        
       
        
        
        JPanel btnAgregar = new JPanel();
        btnAgregar.setBackground(new Color(69, 69, 69));
        btnAgregar.setBounds(478, 377, 96, 23);
        contentPane.add(btnAgregar);
        btnAgregar.setLayout(null);
        
        JLabel btnModificarTxt = new JLabel("Modificar");
        btnModificarTxt.setBounds(0, 0, 96, 23);
        btnAgregar.add(btnModificarTxt);
        btnModificarTxt.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		
        		modificarDatosUsuario();
        		
        		abrirMensajeModificadoExito();
        		cerrarInterfaz();
        	}
        });
        btnModificarTxt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnModificarTxt.setForeground(new Color(255, 255, 255));
        btnModificarTxt.setHorizontalAlignment(SwingConstants.CENTER);
        btnModificarTxt.setFont(new Font("Tahoma", Font.PLAIN, 15));
        
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

	public void modificarDatosUsuario() {
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String calle = txtCalle.getText();
        Integer numero = Integer.parseInt(textNro.getText());
        Titular titularViejo = titularActual;
        
        // Actualizar los campos del usuario solo si se han ingresado valores válidos
        if (!nombre.isEmpty()) {
            titularActual.setNombre(nombre);
        }
        if (!apellido.isEmpty()) {
            titularActual.setApellido(apellido);
            
        }
        if (!calle.isEmpty()) {
            titularActual.setCalle(calle);
        }

        titularActual.setEsDonante(textEsDonante.isSelected());


        
        if(!numero.toString().isEmpty())

        	titularActual.setNroCasa(numero);
        
        else titularActual.setNroCasa(0);

        // Guardar los cambios en la base de datos
        LicenciaController.getInstance().actualizarTitular(titularActual);
    }
       
	public void abrirMensajeModificadoExito() {
		MensajeModificadoExito mensajeModEx = new MensajeModificadoExito();
		mensajeModEx.setVisible(true);
		setVisible(false); 
	}
	
}