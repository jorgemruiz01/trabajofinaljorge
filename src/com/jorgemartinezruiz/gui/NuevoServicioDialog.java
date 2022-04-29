package com.jorgemartinezruiz.gui;

import javax.swing.*;
import java.awt.*;

public class NuevoServicioDialog extends JFrame{
    public JPanel panel1;
    public JTextField txtNombreNServicio;
    public JTextField txtPrecioNServicio;
    public JButton ButtonAnyadirServicioNServicio;

    public NuevoServicioDialog() {
        startJFrame();
    }

    /**
     * Método en el que establecemos la configuración de la ventana
     * como por ejemplo el tamaño, la posición, el icono...
     * Además, llamamos a los métodos para configurar los combobox.
     */
    private void startJFrame() {
        this.setTitle("Nuevo Servicio");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(false);
        this.setSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("../res/ICONO.png")).getImage());
    }
}
