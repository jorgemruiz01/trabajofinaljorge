package com.jorgemartinezruiz.gui;

import javax.swing.*;
import java.awt.*;

public class NuevoEmpleadoDialog extends JFrame{
    public JPanel panel1;
    public JTextField textNombreNEmp;
    public JTextField txtApellidoNEmp;
    public JTextField txtDniNEmp;
    public JTextField txtTlfNEmp;
    public JTextField txtUsernameNEmp;
    public JTextField txtPasswordNEmp;
    public JButton buttonAnyadirEmpNEmp;



    public NuevoEmpleadoDialog() {
        startJFrame();
    }


    private void startJFrame() {
        this.setTitle("Nuevo Empleado");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(false);
        this.setSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("../res/ICONO.png")).getImage());
    }
}
