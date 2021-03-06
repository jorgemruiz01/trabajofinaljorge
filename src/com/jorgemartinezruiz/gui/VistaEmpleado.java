package com.jorgemartinezruiz.gui;



import com.jorgemartinezruiz.enums.Buscar;
import com.jorgemartinezruiz.enums.UsuariosLogin;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VistaEmpleado extends JFrame {
    Modelo modelo;
    private Connection conexion ;
    public JTabbedPane tabbedPane1;
    public JPanel panel1;
    public JPanel panelEmpleadosEmpleado;
    public JPanel panelClientesEmpleado;
    public JPanel panelVentasEmpleado;
    public JPanel panelServiciosEmpleado;
    public JLabel lbl1;
    public JLabel lblSalarioEmpleadoEmpleados;
    public JLabel lblNombreApellidoEmpleadoEmpleados;
    public JLabel lblDNIEmpleadoEmplado;
    public JLabel lblTlfEmpeladoEmpleados;
    public JButton buttonRealizarVentasEmpleados;
    public JTable tableVentasEmpleado;
    public JButton buttonMostrarVentasEmpleados;
    public JLabel lblPrecioVentasEmpleados;
    public JButton buttonCalcularPrecioVentasEmpleado;
    public JTable tableServiciosEmlpeado;
    public JButton buttonAnyadirClientesEmpleado;
    public JButton buttonMostrarClientesEmpleado;
    public JTable tableClientesEmpleado;
    public JButton buttonBuscarCliente;
    public JButton buttonBuscarServicio;
    public JTextField txtBuscarCliente;
    public JTextField txtBuscarServicio;
    public JTable tableBuscarCliente;
    public JTable tableBuscarServicio;
    public JComboBox comboBuscarClienteEmpleado;
    public JButton buscarButton;
    public JTextField textBuscarClienteEmpleado;
    public JButton buttonBuscarServicioEmpleado;
    public JTextField txtBuscarServicioEmpleado;
    public JComboBox comboBuscarServicioEmpleado;
    public JButton buttonMostrarServiciosEmpleado;
    public JButton verVentasEnPDFButton;

    DefaultTableModel dtmVentas;
    DefaultTableModel dtmServicios;
    DefaultTableModel dtmClientes;
    DefaultTableModel dtmBuscarClientes;
    DefaultTableModel dtmBuscarServicios;

    JMenuItem itemAcercaDe;
    JMenuItem itemCerrarSesion;
    JMenuItem itemAyuda;

    public VistaEmpleado() {

        startJFrame();

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

        setTableModels();
        setComboBox();
        setMenu();
        ponLaAyuda();
    }
    private void setTableModels() {

        this.dtmClientes = new DefaultTableModel();
        this.tableClientesEmpleado.setModel(dtmClientes);

        this.dtmVentas = new DefaultTableModel();
        this.tableVentasEmpleado.setModel(dtmVentas);

        this.dtmServicios = new DefaultTableModel();
        this.tableServiciosEmlpeado.setModel(dtmServicios);

        this.dtmBuscarClientes = new DefaultTableModel();
        this.tableBuscarCliente.setModel(dtmBuscarClientes);

        this.dtmBuscarServicios = new DefaultTableModel();
        this.tableBuscarServicio.setModel(dtmBuscarServicios);


    }
    private void setMenu(){
        //Declaramos la barra de menu
        JMenuBar barra = new JMenuBar();
        //Declaramos un JMenu
        JMenu menu = new JMenu("Opciones");
        //Creamos un componente para la barra de men??
        itemAcercaDe = new JMenuItem("Acerca de");
        itemCerrarSesion = new JMenuItem("Cerrar Sesi??n");
        itemAyuda = new JMenuItem("Ayuda");
        itemAyuda.setActionCommand("Ayuda");
        //Asignamos un comando para poder darle funci??n
        itemAcercaDe.setActionCommand("Acerca de");
        itemCerrarSesion.setActionCommand("CerrarSesionEmpleado");
        //A??adimos los JMenuItems al men??
        menu.add(itemCerrarSesion);
        menu.add(itemAcercaDe);
        menu.add(itemAyuda);
        //A??adimos el men?? a la barra
        barra.add(menu);
        barra.add(Box.createHorizontalGlue());
        this.setJMenuBar(barra);

    }

    private void setComboBox() {
        for (Buscar constant : Buscar.values()) {
            comboBuscarClienteEmpleado.addItem(constant.getValor());
            comboBuscarServicioEmpleado.addItem(constant.getValor());
        }
        comboBuscarClienteEmpleado.setSelectedIndex(-1);
        comboBuscarServicioEmpleado.setSelectedIndex(-1);
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
            hb.enableHelpOnButton(itemAyuda, "empleado", helpset);



        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}