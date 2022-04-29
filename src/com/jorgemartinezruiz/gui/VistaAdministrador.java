package com.jorgemartinezruiz.gui;

import com.jorgemartinezruiz.enums.Buscar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

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
    DefaultTableModel dtmEmpleados;

    JMenuItem itemAcercaDe;
    JMenuItem itemCerrarSesion;

    
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

        setMenu();
        setTableModels();
        setComboBox();
    }
    private void setTableModels() {

        this.dtmEmpleados = new DefaultTableModel();
        String cabecero[] = {"Nombre, Apellidos, Xd"};

        this.dtmEmpleados.addRow(cabecero);
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
        //Asignamos un comando para poder darle función
        itemAcercaDe.setActionCommand("Acerca de");
        itemCerrarSesion.setActionCommand("CerrarSesionAdmin");
        //Añadimos los JMenuItems al menú
        menu.add(itemCerrarSesion);
        menu.add(itemAcercaDe);
        //Añadimos el menú a la barra
        barra.add(menu);
        barra.add(Box.createHorizontalGlue());
        this.setJMenuBar(barra);
    }
}
