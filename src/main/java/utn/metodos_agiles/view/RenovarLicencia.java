package utn.metodos_agiles.view;


import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.entidades.Licencia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RenovarLicencia extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JButton renovarBtn;
    private Licencia licencia;

    public RenovarLicencia(Licencia licencia) {
        super("Renovar Licencia");

        this.licencia = licencia;

        setIconImage(Toolkit.getDefaultToolkit().getImage("/imagenes/Escudo_Argentina.png"));
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setContentPane(contentPane);
        pack();
        renovarBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                renovarLicencia();
            }
        });
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    private void renovarLicencia() {
        DBManager.getInstance().
    }
}
