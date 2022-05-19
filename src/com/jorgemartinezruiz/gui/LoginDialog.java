package com.jorgemartinezruiz.gui;

import com.jorgemartinezruiz.enums.UsuariosLogin;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
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
    JMenuItem itemAyuda;
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
        ponLaAyuda();
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
        itemAyuda = new JMenuItem("Ayuda");
        itemAyuda.setActionCommand("Ayuda");
        itemSalir = new JMenuItem("Salir");
        itemSalir.setActionCommand("Salir");


        menu.add(itemAcercaDe);
        menu.add(itemSalir);
        menu.add(itemAyuda);
        mbBar.add(menu);
        mbBar.add(Box.createHorizontalGlue());
        this.setJMenuBar(mbBar);
    }

    private void ponLaAyuda() {
        try {
            // Carga el fichero de ayuda
            File fichero = new File("src\\javahelpaseguradora\\help\\help_set.hs");
            //File fichero = new File("src"+File.separator+"pruebajavahelp\\help\\help_set.hs");
            URL hsURL = fichero.toURI().toURL();

            // Crea el HelpSet y el HelpBroker
            HelpSet helpset = new HelpSet(getClass().getClassLoader(), hsURL);
            HelpBroker hb = helpset.createHelpBroker();

            // Pone ayuda a item de menu al pulsarlo y a F1 en ventana
            // manual e instalar.
            hb.enableHelpOnButton(itemAyuda, "login", helpset);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
