package com.jorgemartinezruiz.gui;

import com.jorgemartinezruiz.enums.Buscar;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class VistaCliente extends JFrame {

    public JTabbedPane tabbedPane1;
    public JPanel panel1;
    public JTable tableServicios;
    public JButton buscarButton;
    public JButton darDeBajaButton;
    public JButton mostrarButton;
    public JComboBox comboBuscarServicios;

    JMenuItem itemAcercaDe;
    JMenuItem itemCerrarSesion;
    JMenuItem itemAyuda;
    DefaultTableModel dtmMisServicios;

    public VistaCliente() {
        startJFrame();;
    }

    private void startJFrame() {
        setTitle("Aseguradora");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(false);
        this.setEnabled(true);
        this.setSize(new Dimension(this.getWidth(), this.getHeight() + 20));
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("../res/ICONO.png")).getImage());

        setTableModels();
        setMenu();
        ponLaAyuda();

    }
    private void setTableModels() {

        this.dtmMisServicios = new DefaultTableModel();
        this.tableServicios.setModel(dtmMisServicios);

    }
    private void setMenu(){
        //Declaramos la barra de menu
        JMenuBar barra = new JMenuBar();
        //Declaramos un JMenu
        JMenu menu = new JMenu("Opciones");
        //Creamos un componente para la barra de menú
        itemAcercaDe = new JMenuItem("Acerca de");
        itemCerrarSesion = new JMenuItem("Cerrar Sesión");
        itemAyuda = new JMenuItem("Ayuda");
        itemAyuda.setActionCommand("Ayuda");
        //Asignamos un comando para poder darle función
        itemAcercaDe.setActionCommand("Acerca de");
        itemCerrarSesion.setActionCommand("CerrarSesionCliente");
        //Añadimos los JMenuItems al menú
        menu.add(itemCerrarSesion);
        menu.add(itemAcercaDe);
        menu.add(itemAyuda);
        //Añadimos el menú a la barra
        barra.add(menu);
        barra.add(Box.createHorizontalGlue());
        this.setJMenuBar(barra);
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
            hb.enableHelpOnButton(itemAyuda, "cliente", helpset);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
