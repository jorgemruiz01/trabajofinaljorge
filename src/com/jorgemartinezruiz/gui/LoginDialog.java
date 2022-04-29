package com.jorgemartinezruiz.gui;

import com.jorgemartinezruiz.enums.UsuariosLogin;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class LoginDialog extends JFrame{

    private JPanel panel1;

    public JLabel lblUsuario;
    public JLabel lblContrasena;
    public JTextField txtUsuarioLogin;
    public JTextField txtContrasenaLogin;
    public JButton btnLogin;
    public JComboBox comboTipoUsuarioLogin;


    JMenuItem itemSalir;
    JMenuItem itemAcercaDe;

    public LoginDialog() { startJFrame(); }

    private void startJFrame(){
        this.setTitle("Aseguradora");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setSize(600, 350);
        this.setLocationRelativeTo(null);

        this.setIconImage(new ImageIcon(getClass().getResource("../res/ICONO.png")).getImage());

        setComboBox();
        setMenu();
    }

    private void setComboBox() {
        for (UsuariosLogin constant : UsuariosLogin.values()) {
            comboTipoUsuarioLogin.addItem(constant.getValor());
        }
        comboTipoUsuarioLogin.setSelectedIndex(-1);
    }
    private void setMenu(){

        //Declaramos un JMenu
        JMenu menu = new JMenu("Opciones");
        JMenuBar mbBar = new JMenuBar();
        itemAcercaDe = new JMenuItem("Acerca de");
        itemAcercaDe.setActionCommand("Acerca de");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");
        menu.add(itemAcercaDe);
        menu.add(itemSalir);
        mbBar.add(menu);
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

}
