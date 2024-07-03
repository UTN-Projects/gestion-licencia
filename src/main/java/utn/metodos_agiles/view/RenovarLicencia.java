package utn.metodos_agiles.view;

import entidades.Licencia;

import javax.swing.*;
import java.awt.*;

public class RenovarLicencia extends JFrame {

    private JPanel contentPanel;
    private JTextField textField1;
    private JButton Renovar;

    private Licencia licencia;

    public RenovarLicencia() {
        setIconImage(Toolkit.getDefaultToolkit().getImage("/imagenes/Escudo_Argentina.png"));
        setResizable(false);
        setTitle("Renovar Licencia");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(700, 300, 600, 450);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
