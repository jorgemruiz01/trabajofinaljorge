package com.jorgemartinezruiz.gui;

import javax.swing.*;
import java.awt.*;

public class NuevoClienteDialog extends JFrame {

    public JPanel panel1;
    public JButton buttonAnyadirClienteNCliente;
    public JPasswordField txtPasswordNCliente;
    public JTextField txtUsernameNCliente;
    public JTextField txtEdadNCliente;
    public JTextField txtNombreNCliente;
    public JRadioButton RBsiAntecedentes;
    public JRadioButton RBnoAntecedentes;
    public JRadioButton RBsiFamnumerosa;
    public JRadioButton RBnoFamnumerosa;

    public NuevoClienteDialog() {
        startJFrame();
    }

    /**
     * Método en el que establecemos la configuración de la ventana
     * como por ejemplo el tamaño, la posición, el icono...
     * Además, llamamos a los métodos para configurar los combobox.
     */
    private void startJFrame() {
        this.setTitle("Nuevo Cliente");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(false);
        this.setSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("../res/ICONO.png")).getImage());
    }
}
