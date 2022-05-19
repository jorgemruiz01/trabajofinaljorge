package com.jorgemartinezruiz.gui;

import com.jorgemartinezruiz.generarReporte.GenerarReporte;
import com.jorgemartinezruiz.utilidades.Utilidadades;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;

public class Controlador implements ActionListener, ListSelectionListener {

    private Ajustes ajustes;
    private LoginDialog loginDialog;
    private VistaAdministrador vistaAdministrador;
    private VistaCliente vistaCliente;
    private VistaEmpleado vistaEmpleado;
    private NuevoEmpleadoDialog nuevoEmpleadoDialog;
    private NuevoServicioDialog nuevoServicioDialog;
    private NuevoClienteDialog nuevoClienteDialog;
    private AcercaDeDialog acercaDeDialog;
    private GenerarReporte generarReporte;

    private Modelo modelo = new Modelo();
    private Utilidadades utilidades;


    public Controlador(Modelo modelo, LoginDialog loginDialog) throws SQLException {


        this.loginDialog = loginDialog;
        this.modelo = modelo;

        vistaAdministrador = new VistaAdministrador();
        vistaCliente = new VistaCliente();
        vistaEmpleado = new VistaEmpleado();

        nuevoEmpleadoDialog = new NuevoEmpleadoDialog();
        nuevoServicioDialog = new NuevoServicioDialog();
        nuevoClienteDialog = new NuevoClienteDialog();
        acercaDeDialog = new AcercaDeDialog();

        generarReporte = new GenerarReporte();

        ajustes = new Ajustes();

        modelo.conectar();
        refrescar();
        addActionListeners(this);
        addItemListeners(this);


    }
    private void addItemListeners(Controlador controlador) {
    }

    private void refrescar() {
        refrescarClientes();
        refrescarVentas();
        refrescarServicios();
        refrescarEmpelados();

    }


    private void addActionListeners(ActionListener listener) {

        loginDialog.btnLogin.addActionListener(listener);
        loginDialog.itemAcercaDe.addActionListener(listener);
        loginDialog.itemSalir.addActionListener(listener);

        vistaAdministrador.buttonAnyadirEmpleadoAdmin.addActionListener(listener);
        vistaAdministrador.buttonAnyadirServiciosAdmin.addActionListener(listener);
        vistaAdministrador.buttonEliminarEmpleadoAdmin.addActionListener(listener);
        vistaAdministrador.buttonEliminarServiciosAdmin.addActionListener(listener);
        vistaAdministrador.buttonModificarEmpleadoAdmin.addActionListener(listener);
        vistaAdministrador.buttonModificarServiciosAdmin.addActionListener(listener);
        vistaAdministrador.buttonMostrarEmpleadosAdmin.addActionListener(listener);
        vistaAdministrador.buttonMostrarServicios.addActionListener(listener);
        vistaAdministrador.buttonBuscarEmpelado.addActionListener(listener);
        vistaAdministrador.buttonBuscarServiciosAdmin.addActionListener(listener);
        vistaAdministrador.ordenarPorEdadAscendenteButton.addActionListener(listener);
        vistaAdministrador.ordenarPorEdadDesdencenteButton.addActionListener(listener);
        vistaAdministrador.ordenarPorPrecioAscendenteButton.addActionListener(listener);
        vistaAdministrador.ordenarPorPrecioDescendenteButton.addActionListener(listener);
        vistaAdministrador.itemCerrarSesion.addActionListener(listener);
        vistaAdministrador.itemAcercaDe.addActionListener(listener);
        vistaAdministrador.mostrarEnPDFButton.addActionListener(listener);

        vistaEmpleado.buttonBuscarCliente.addActionListener(listener);
        vistaEmpleado.buttonBuscarServicio.addActionListener(listener);
        vistaEmpleado.buttonMostrarClientesEmpleado.addActionListener(listener);
        vistaEmpleado.buscarButton.addActionListener(listener);
        vistaEmpleado.buttonBuscarServicioEmpleado.addActionListener(listener);
        vistaEmpleado.buttonMostrarServiciosEmpleado.addActionListener(listener);
        vistaEmpleado.itemCerrarSesion.addActionListener(listener);
        vistaEmpleado.itemAcercaDe.addActionListener(listener);
        vistaEmpleado.buttonRealizarVentasEmpleados.addActionListener(listener);
        vistaEmpleado.buttonAnyadirClientesEmpleado.addActionListener(listener);
        vistaEmpleado.verVentasEnPDFButton.addActionListener(listener);


        vistaCliente.darDeBajaButton.addActionListener(listener);
        vistaCliente.itemCerrarSesion.addActionListener(listener);
        vistaCliente.itemAcercaDe.addActionListener(listener);

        nuevoEmpleadoDialog.buttonAnyadirEmpNEmp.addActionListener(listener);
        nuevoServicioDialog.ButtonAnyadirServicioNServicio.addActionListener(listener);
        nuevoClienteDialog.buttonAnyadirClienteNCliente.addActionListener(listener);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String command = e.getActionCommand();
        switch (command) {
            case "anyadirEmpleadoAdmin": {
                if (nuevoEmpleadoDialog.isActive()) {
                    vistaAdministrador.setEnabled(false);
                    nuevoEmpleadoDialog.setVisible(true);
                } else {
                    vistaAdministrador.setEnabled(true);
                    nuevoEmpleadoDialog.setVisible(true);
                }
            }
            break;


            case "anyadirClienteEmp": {
                if (nuevoClienteDialog.isActive()) {
                    vistaEmpleado.setEnabled(false);
                    nuevoClienteDialog.setVisible(true);
                } else {
                    vistaEmpleado.setEnabled(true);
                    nuevoClienteDialog.setVisible(true);
                }
            }
            break;
            case "anyadirServicioAdmin": {
                if (nuevoServicioDialog.isActive()) {
                    vistaAdministrador.setEnabled(false);
                    nuevoServicioDialog.setVisible(true);
                } else {
                    vistaAdministrador.setEnabled(true);
                    nuevoServicioDialog.setVisible(true);
                }
            }
            break;


            case "iniciarSesion": {
                String username = loginDialog.txtUsuarioLogin.getText();
                String password = loginDialog.txtContrasenaLogin.getText();
                String tipo = (String) loginDialog.comboTipoUsuarioLogin.getSelectedItem();
                boolean existe = false;
                if (tipo == "Cliente") {
                    existe = modelo.clienteExistente(username, password);
                }
                if (tipo == "Empleado") {
                    existe = modelo.empleadoExistente(username, password);
                }
                if (tipo == "Administrador") {
                    existe = modelo.adminExistente(username, password);
                }

                if (existe) {
                    switch (tipo) {
                        case "Administrador":
                            loginDialog.setVisible(false);
                            vistaAdministrador.setVisible(true);
                            loginDialog.dispose();
                            break;

                        case "Empleado":
                            loginDialog.setVisible(false);
                            vistaEmpleado.setVisible(true);
                            loginDialog.dispose();

                            vistaEmpleado.lblSalarioEmpleadoEmpleados.setText("2000 euros");
                            vistaEmpleado.lblNombreApellidoEmpleadoEmpleados.setText(modelo.empleado.getNombre() + " " + modelo.empleado.getApellido());
                            vistaEmpleado.lblTlfEmpeladoEmpleados.setText(modelo.empleado.getTelefono());

                            vistaEmpleado.lblDNIEmpleadoEmplado.setText(String.valueOf(modelo.empleado.getDni()));
                            break;

                        case "Cliente":
                            loginDialog.setVisible(false);
                            vistaCliente.setVisible(true);
                            loginDialog.dispose();
                            refrescarMisServicios(Integer.parseInt(modelo.cliente.getId()));
                            break;

                    }

                    loginDialog.txtContrasenaLogin.setText(null);
                    loginDialog.comboTipoUsuarioLogin.setSelectedItem(null);

                } else {
                    utilidades.showErrorAlert("No ha sido posible el inicio de sesión");
                }
            }
            break;

            case "anyadirEmpleado": {
                try {
                    String dni=nuevoEmpleadoDialog.txtDniNEmp.getText();
                    int dnil = dni.length();


                    if(dnil == 9){

                        if (ajustes.comprobarEmpeladoVacio(nuevoEmpleadoDialog))  {
                            utilidades.showErrorAlert("Rellena todos los campos");
                        } else {

                            modelo.nuevoEmpleado(
                                    nuevoEmpleadoDialog.textNombreNEmp.getText(),
                                    nuevoEmpleadoDialog.txtApellidoNEmp.getText(),

                                    nuevoEmpleadoDialog.txtDniNEmp.getText(),
                                    Integer.parseInt(nuevoEmpleadoDialog.txtTlfNEmp.getText()),
                                    nuevoEmpleadoDialog.txtUsernameNEmp.getText(),
                                    nuevoEmpleadoDialog.txtPasswordNEmp.getText());


                            ajustes.borrarCamposEmpleado(nuevoEmpleadoDialog);
                            nuevoEmpleadoDialog.setVisible(false);

                            nuevoEmpleadoDialog.dispose();
                            refrescar();
                        }
                    } else {
                            utilidades.showErrorAlert("Introduce un dni válido");
                        }



                } catch (NumberFormatException nfe) {
                    utilidades.showErrorAlert("Introduce números en los campos que lo requieren");

                }

            }
            break;


            case "anyadirCliente": {
                try {
                    if (ajustes.comprobarClienteVacio(nuevoClienteDialog)) {
                        utilidades.showErrorAlert("Rellena todos los campos");
                    } else {
                        boolean familiaNumerosa = false, antecedentes = false;
                        if (nuevoClienteDialog.RBsiFamnumerosa.isSelected()) {
                            familiaNumerosa = true;
                        } else {
                            familiaNumerosa = false;
                        }
                        if (nuevoClienteDialog.RBsiAntecedentes.isSelected()) {
                            antecedentes = true;
                        } else {
                            antecedentes = false;
                        }
                        modelo.nuevoCliente(
                                nuevoClienteDialog.txtNombreNCliente.getText(),
                                Integer.parseInt(nuevoClienteDialog.txtEdadNCliente.getText()),
                                antecedentes,
                                familiaNumerosa,
                                nuevoClienteDialog.txtUsernameNCliente.getText(),
                                nuevoClienteDialog.txtPasswordNCliente.getText());

                        ajustes.borrarCamposCliente(nuevoClienteDialog);
                        nuevoClienteDialog.setVisible(false);

                        nuevoClienteDialog.dispose();
                        refrescar();
                    }
                } catch (NumberFormatException nfe) {
                    utilidades.showErrorAlert("Introduce números en los campos que lo requieren");

                }

            }
            break;

            case "anyadirServicio": {
                try {
                    if (ajustes.comprobarServicioVacio(nuevoServicioDialog)) {
                        utilidades.showErrorAlert("Rellena todos los campos");
                    } else {

                        modelo.nuevoServicio(
                                nuevoServicioDialog.txtNombreNServicio.getText(),
                                Double.parseDouble(nuevoServicioDialog.txtPrecioNServicio.getText()));


                        ajustes.borrarCamposServicio(nuevoServicioDialog);
                        nuevoServicioDialog.setVisible(false);

                        nuevoServicioDialog.dispose();
                        refrescar();
                    }
                } catch (NumberFormatException nfe) {
                    utilidades.showErrorAlert("Introduce números en los campos que lo requieren");

                }

            }
            break;
            case "realizarVenta": {
                try {

                        modelo.realizarVenta(
                                ((String)vistaEmpleado.tableBuscarServicio.getValueAt(vistaEmpleado.tableBuscarServicio.getSelectedRow(), 2)),
                                Integer.parseInt(modelo.empleado.getId()),
                                Integer.parseInt((String)vistaEmpleado.tableBuscarServicio.getValueAt(vistaEmpleado.tableBuscarServicio.getSelectedRow(), 0)),
                                Integer.parseInt((String)vistaEmpleado.tableBuscarCliente.getValueAt(vistaEmpleado.tableBuscarCliente.getSelectedRow(), 0))

                                );



                        nuevoEmpleadoDialog.setVisible(false);


                        refrescar();

                } catch (NumberFormatException nfe) {
                    utilidades.showErrorAlert("Introduce números en los campos que lo requieren");

                }

            }
            break;
            case "eliminarServicio":
                try {
                    int a =Integer.parseInt((String)vistaAdministrador.tableServiciosAdmin.getValueAt(vistaAdministrador.tableServiciosAdmin.getSelectedRow(), 0));
                    modelo.eliminarServicio(a);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                refrescarServicios();
                break;

            case "eliminarEmpleado":
                try {
                    int a = Integer.parseInt((String)vistaAdministrador.tableEmpleadosAdmin.getValueAt(vistaAdministrador.tableEmpleadosAdmin.getSelectedRow(), 0));
                    modelo.eliminarEmpleado(a);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                refrescarEmpelados();
                break;
            case "darDeBaja":
                try {
                    int a= (int) vistaCliente.tableServicios.getValueAt(vistaCliente.tableServicios.getSelectedRow(), 0);
                    modelo.eliminarVenta(a);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                refrescarMisServicios(Integer.parseInt(modelo.cliente.getId()));
                break;

            case "modificarServicio": {


                try {
                    modelo.modificarServicio(
                            Integer.parseInt((String)vistaAdministrador.tableServiciosAdmin.getValueAt(vistaAdministrador.tableServiciosAdmin.getSelectedRow(), 0)),
                            ((String)vistaAdministrador.tableServiciosAdmin.getValueAt(vistaAdministrador.tableServiciosAdmin.getSelectedRow(), 1)),
                            Double.parseDouble((String)vistaAdministrador.tableServiciosAdmin.getValueAt(vistaAdministrador.tableServiciosAdmin.getSelectedRow(), 2))
                            );
                } catch (NumberFormatException | SQLException nfe) {
                    utilidades.showErrorAlert("Introduce números en los campos que lo requieren");

                }


                refrescarServicios();
            }
            break;

            case "modificarEmpleado": {


                try {
                    modelo.modificarEmpleado(
                           Integer.parseInt((String)vistaAdministrador.tableEmpleadosAdmin.getValueAt(vistaAdministrador.tableEmpleadosAdmin.getSelectedRow(), 0)),
                            ((String)vistaAdministrador.tableEmpleadosAdmin.getValueAt(vistaAdministrador.tableEmpleadosAdmin.getSelectedRow(), 1)),
                            ((String)vistaAdministrador.tableEmpleadosAdmin.getValueAt(vistaAdministrador.tableEmpleadosAdmin.getSelectedRow(), 2)),
                            Integer.parseInt((String)vistaAdministrador.tableEmpleadosAdmin.getValueAt(vistaAdministrador.tableEmpleadosAdmin.getSelectedRow(), 3)),
                            ((String)vistaAdministrador.tableEmpleadosAdmin.getValueAt(vistaAdministrador.tableEmpleadosAdmin.getSelectedRow(), 4)),
                            ((String)vistaAdministrador.tableEmpleadosAdmin.getValueAt(vistaAdministrador.tableEmpleadosAdmin.getSelectedRow(), 5))
                    );
                } catch (NumberFormatException | SQLException nfe) {
                    utilidades.showErrorAlert("Introduce números en los campos que lo requieren");

                }


                refrescarEmpelados();
            }
            break;
            case "buscarCliente": {

                    String username = vistaEmpleado.txtBuscarCliente.getText();
                    if (username.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el usuario del cliente correctamente");
                    } else {
                        refrescarBuscarCliente(username);
                    }

            }
            break;
            case "buscarServicio": {

                String nombre = vistaEmpleado.txtBuscarServicio.getText();
                if (nombre.isEmpty()) {
                    utilidades.showErrorAlert("Introduce el usario del cliente correctamente");
                } else {
                    refrescarBuscarServicio(nombre);
                }

            }
            break;
            case "buscarClienteEmpleado": {
                String contenido = (String)vistaEmpleado.comboBuscarClienteEmpleado.getSelectedItem();
                if(contenido == "Id"){
                    String id = vistaEmpleado.textBuscarClienteEmpleado.getText();
                    if (id.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del cliente a buscar");
                    } else {
                        boolean num = true;
                        refrescarBuscar(id,  num, "idcliente");
                    }
                }
                if(contenido == "Nombre"){
                    String nombre = vistaEmpleado.textBuscarClienteEmpleado.getText();
                    if (nombre.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del cliente a buscar");
                    } else {
                        boolean num = false;
                        refrescarBuscar(nombre,  num, "idcliente");
                    }
                }

            }
            break;

            case "buscarServicioEmpleadooo": {
                String contenido = (String)vistaEmpleado.comboBuscarServicioEmpleado.getSelectedItem();
                if(contenido == "Id"){
                    String id = vistaEmpleado.txtBuscarServicioEmpleado.getText();
                    if (id.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del servicio a buscar");
                    } else {
                        boolean num = true;
                        refrescarBuscar(id,  num, "idservicio");
                    }
                }
                if(contenido == "Nombre"){
                    String nombre = vistaEmpleado.txtBuscarServicioEmpleado.getText();
                    if (nombre.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del servicio a buscar");
                    } else {
                        boolean num = false;
                        refrescarBuscar(nombre,  num, "idservicio");
                    }
                }

            }
            break;
            case "buscarServiciosAdmin": {
                String contenido = (String)vistaAdministrador.comboBuscarServicios.getSelectedItem();
                if(contenido == "Id"){
                    String id = vistaAdministrador.txtBuscarServicios.getText();
                    if (id.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del servicio a buscar");
                    } else {
                        boolean num = true;
                        refrescarBuscar(id,  num, "idservicio");
                    }
                }
                if(contenido == "Nombre"){
                    String nombre = vistaAdministrador.txtBuscarServicios.getText();
                    if (nombre.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del servicio a buscar");
                    } else {
                        boolean num = false;
                        refrescarBuscar(nombre,  num, "idservicio");
                    }
                }

            }
            break;

            case "buttonBuscarEmpleadoAdmin": {
                String contenido = (String)vistaAdministrador.comboBuscarEmpleado.getSelectedItem();
                if(contenido == "Id"){
                    String id = vistaAdministrador.txtBuscarEmpleado.getText();
                    if (id.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del empleado a buscar");
                    } else {
                        boolean num = true;
                        refrescarBuscar(id,  num, "idempleado");
                    }
                }
                if(contenido == "Nombre"){
                    String nombre = vistaAdministrador.txtBuscarEmpleado.getText();
                    if (nombre.isEmpty()) {
                        utilidades.showErrorAlert("Introduce el nombre del empleado a buscar");
                    } else {
                        boolean num = false;
                        refrescarBuscar(nombre,  num, "idempleado");
                    }
                }

            }
            break;

            case "mostrarServiciosEmpleado" :{
                refrescarServicios();
            }
            break;
            case "mostrarClientesEmpleado" :{
                refrescarClientes();
            }
            break;
            case "mostrarMisServicios" :{
                refrescarMisServicios(Integer.parseInt(modelo.cliente.getId()));
            }
            break;
            case "mostrarServiciosAdmin" :{
                refrescarServicios();
            }
            break;

            case "mostrarEmpleadosAdmin" :{
                refrescarEmpelados();
            }
            break;
            case "edadAscendente" :{
                try {
                    vistaAdministrador.tableClientesAdmin.setModel(construirTableModelClientes(modelo.edadAscendente()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            break;
            case "edadDescendente" :{
                try {
                    vistaAdministrador.tableClientesAdmin.setModel(construirTableModelClientes(modelo.edadDescendente()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            break;
            case "precioAsc" :{
                try {
                    vistaAdministrador.tableVentasAdmin.setModel(construirTableModelVentas(modelo.precioAscendente()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            break;
            case "precioDesc" :{
                try {
                    vistaAdministrador.tableVentasAdmin.setModel(construirTableModelVentas(modelo.precioDescendente()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            break;
            case "Salir": {
                if (JOptionPane.showConfirmDialog(null, "¿Desea salir del programa?",
                        "Salir", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
            break;

            case "Acerca de": {
                acercaDeDialog.setVisible(true);
            }
            break;

            case "CerrarSesionAdmin": {
                vistaAdministrador.setVisible(false);
                loginDialog.setVisible(true);
                vistaAdministrador.dispose();
            }

            case "CerrarSesionEmpleado": {
                vistaEmpleado.setVisible(false);
                loginDialog.setVisible(true);
                vistaEmpleado.dispose();
            }
            break;

            case "CerrarSesionCliente": {
                vistaCliente.setVisible(false);
                loginDialog.setVisible(true);
                vistaCliente.dispose();
            }
            break;
            case "datosempleados": {
                try {
                    generarReporte.generarReporte(modelo);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            break;
            case "ventasPorEmpleado": {
                try {
                    generarReporte.generarReporte2(modelo);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }


    }

    private void refrescarBuscar(String a,  boolean num, String c) {
        try {
            if(c== "idcliente"){
                if(num==true){
                    int id = Integer.parseInt(a);

                    vistaEmpleado.tableClientesEmpleado.setModel(construirTableModelClientes(modelo.buscarPorId(id, c)));
                }
                if(num==false) {
                    vistaEmpleado.tableClientesEmpleado.setModel(construirTableModelClientes(modelo.buscarPorNombre(a, c)));
                }
            }
            if(c== "idservicio"){
                if(num==true){
                    int id = Integer.parseInt(a);
                    vistaAdministrador.tableServiciosAdmin.setModel(construirTableModelServicios(modelo.buscarPorId(id, c)));
                    vistaEmpleado.tableServiciosEmlpeado.setModel(construirTableModelServicios(modelo.buscarPorId(id, c)));
                }
                if(num==false) {
                    vistaAdministrador.tableServiciosAdmin.setModel(construirTableModelServicios(modelo.buscarPorNombre(a, c)));
                    vistaEmpleado.tableServiciosEmlpeado.setModel(construirTableModelServicios(modelo.buscarPorNombre(a, c)));
                }
            }
            if(c== "idempleado"){
                if(num==true){
                    int id = Integer.parseInt(a);

                    vistaAdministrador.tableEmpleadosAdmin.setModel(construirTableModelEmpleados(modelo.buscarPorId(id, c)));
                }
                if(num==false) {
                    vistaAdministrador.tableEmpleadosAdmin.setModel(construirTableModelEmpleados(modelo.buscarPorNombre(a, c)));
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private void refrescarBuscarCliente(String username) {
        try {
            vistaEmpleado.tableBuscarCliente.setModel(construirTableModelBuscarCliente(modelo.buscarCliente(username)));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelBuscarCliente(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        vistaEmpleado.dtmBuscarClientes.setDataVector(data, columnNames);

        return vistaEmpleado.dtmBuscarClientes;

    }
    private void refrescarBuscarServicio(String nombre) {
        try {
            vistaEmpleado.tableBuscarServicio.setModel(construirTableModelBuscarServicio(modelo.buscarServicio(nombre)));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelBuscarServicio(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        vistaEmpleado.dtmBuscarServicios.setDataVector(data, columnNames);

        return vistaEmpleado.dtmBuscarServicios;

    }
    private void refrescarClientes() {
        try {
            vistaEmpleado.tableClientesEmpleado.setModel(construirTableModelClientes(modelo.consultarCliente()));
            vistaAdministrador.tableClientesAdmin.setModel(construirTableModelClientes(modelo.consultarCliente()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelClientes(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        vistaEmpleado.dtmClientes.setDataVector(data, columnNames);

        return vistaEmpleado.dtmClientes;

    }

    private void refrescarVentas() {
        try {
            vistaEmpleado.tableVentasEmpleado.setModel(construirTableModelVentas(modelo.consultarVenta()));
            vistaAdministrador.tableVentasAdmin.setModel(construirTableModelVentas(modelo.consultarVenta()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelVentas(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        vistaEmpleado.dtmVentas.setDataVector(data, columnNames);

        return vistaEmpleado.dtmVentas;

    }


    private void refrescarServicios() {
        try {
            vistaEmpleado.tableServiciosEmlpeado.setModel(construirTableModelServicios(modelo.consultarServicio()));
            vistaAdministrador.tableServiciosAdmin.setModel(construirTableModelServicios(modelo.consultarServicio()));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void refrescarMisServicios(int id) {
        try {
            vistaCliente.tableServicios.setModel(construirTableModelMisServicios(modelo.consultarMisServicios(id)));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelServicios(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        vistaEmpleado.dtmServicios.setDataVector(data, columnNames);

        return vistaEmpleado.dtmServicios;

    }
    private DefaultTableModel construirTableModelMisServicios(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        vistaCliente.dtmMisServicios.setDataVector(data, columnNames);

        return vistaCliente.dtmMisServicios;

    }
    private void refrescarEmpelados() {
        try {

            vistaAdministrador.tableEmpleadosAdmin.setModel(construirTableModelEmpleados(modelo.consultarEmpleado()));


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel construirTableModelEmpleados(ResultSet rs)
            throws SQLException {

        ResultSetMetaData metaData = rs.getMetaData();

        // names of columns
        Vector<String> columnNames = new Vector<>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // data of the table
        Vector<Vector<Object>> data = new Vector<>();
        setDataVector(rs, columnCount, data);

        vistaAdministrador.dtmEmpleados.setDataVector(data, columnNames);

        return vistaAdministrador.dtmEmpleados;

    }

    private void setDataVector(ResultSet rs, int columnCount, Vector<Vector<Object>> data) throws SQLException {
        while (rs.next()) {
            Vector<Object> vector = new Vector<>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                vector.add(rs.getObject(columnIndex));
            }
            data.add(vector);
        }
    }
    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}


