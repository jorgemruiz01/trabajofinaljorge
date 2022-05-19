package com.jorgemartinezruiz.gui;

import com.jorgemartinezruiz.enums.Buscar;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class VistaAdministrador extends  JFrame {
    public JTabbedPane tabbedPane;
    public JPanel panel1;
    public JPanel panelClientesAdmin;
    public JPanel panelEmpleadosAdmin;
    public JPanel panelVentasAdmin;
    public JPanel panelServiciosAdmin;
    public JButton buttonAnyadirEmpleadoAdmin;
    public JButton buttonModificarEmpleadoAdmin;
    public JButton buttonEliminarEmpleadoAdmin;
    public JTable tableEmpleadosAdmin;

    public JTable tableClientesAdmin;

    public JTable tableVentasAdmin;
    public JButton buttonAnyadirServiciosAdmin;
    public JButton buttonModificarServiciosAdmin;
    public JButton buttonEliminarServiciosAdmin;

    public JTable tableServiciosAdmin;
    public JTextField txtBuscarServicios;
    public JComboBox comboBuscarServicios;
    public JButton buttonBuscarServiciosAdmin;
    public JButton buttonMostrarServicios;
    public JButton buttonMostrarEmpleadosAdmin;
    public JComboBox comboBuscarEmpleado;
    public JTextField txtBuscarEmpleado;
    public JButton buttonBuscarEmpelado;
    public JButton ordenarPorEdadAscendenteButton;
    public JButton ordenarPorEdadDesdencenteButton;
    public JButton ordenarPorPrecioAscendenteButton;
    public JButton ordenarPorPrecioDescendenteButton;
    public JButton mostrarEnPDFButton;
    DefaultTableModel dtmEmpleados;

    JMenuItem itemAcercaDe;
    JMenuItem itemCerrarSesion;
    JMenuItem itemAyuda;

    
    public VistaAdministrador(){
        startJFrame();

    }

    private void startJFrame() {
        setTitle("Aseguradora");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(false);
        this.setEnabled(true);
        this.setSize(new Dimension(this.getWidth(), this.getHeight()+20));
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("../res/ICONO.png")).getImage());


        setTableModels();
        setMenu();
        setComboBox();
        ponLaAyuda();

    }
    private void setTableModels() {

        this.dtmEmpleados = new DefaultTableModel();
        this.tableEmpleadosAdmin.setModel(dtmEmpleados);
    }

    private void setComboBox() {
        for (Buscar constant : Buscar.values()) {
            comboBuscarServicios.addItem(constant.getValor());
            comboBuscarEmpleado.addItem(constant.getValor());
        }
        comboBuscarServicios.setSelectedIndex(-1);
        comboBuscarEmpleado.setSelectedIndex(-1);
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
        itemCerrarSesion.setActionCommand("CerrarSesionAdmin");
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
            hb.enableHelpOnButton(itemAyuda, "admin", helpset);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
