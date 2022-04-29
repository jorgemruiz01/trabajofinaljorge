package com.jorgemartinezruiz.gui;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ajustes {
    public boolean comprobarEmpeladoVacio(NuevoEmpleadoDialog nuevoEmpleadoDialog) {
        return  nuevoEmpleadoDialog.textNombreNEmp.getText().isEmpty() ||
                nuevoEmpleadoDialog.txtApellidoNEmp.getText().isEmpty() ||
                nuevoEmpleadoDialog.txtDniNEmp.getText().isEmpty() ||
                nuevoEmpleadoDialog.txtPasswordNEmp.getText().isEmpty() ||
                nuevoEmpleadoDialog.txtUsernameNEmp.getText().isEmpty() ||
                nuevoEmpleadoDialog.txtTlfNEmp.getText().isEmpty();

    }
    public boolean comprobarClienteVacio(NuevoClienteDialog nuevoClienteDialog) {
        return  nuevoClienteDialog.txtNombreNCliente.getText().isEmpty() ||
                nuevoClienteDialog.txtEdadNCliente.getText().isEmpty() ||
                (nuevoClienteDialog.RBsiAntecedentes.isSelected()==false && nuevoClienteDialog.RBnoAntecedentes.isSelected()==false) ||
                (nuevoClienteDialog.RBsiFamnumerosa.isSelected()==false && nuevoClienteDialog.RBnoFamnumerosa.isSelected()==false) ||
                nuevoClienteDialog.txtPasswordNCliente.getText().isEmpty()||
                nuevoClienteDialog.txtUsernameNCliente.getText().isEmpty();

    }
    public boolean comprobarServicioVacio(NuevoServicioDialog nuevoServicioDialog) {
        return  nuevoServicioDialog.txtNombreNServicio.getText().isEmpty() ||
                nuevoServicioDialog.txtPrecioNServicio.getText().isEmpty();

    }
    public void borrarCamposEmpleado(NuevoEmpleadoDialog nuevoEmpleadoDialog) {
                nuevoEmpleadoDialog.textNombreNEmp.setText("");
                nuevoEmpleadoDialog.txtApellidoNEmp.setText("");
                nuevoEmpleadoDialog.txtDniNEmp.setText("");
                nuevoEmpleadoDialog.txtPasswordNEmp.setText("");
                nuevoEmpleadoDialog.txtUsernameNEmp.setText("");
                nuevoEmpleadoDialog.txtTlfNEmp.setText("");
    }
    public void borrarCamposCliente(NuevoClienteDialog nuevoClienteDialog) {
        nuevoClienteDialog.txtPasswordNCliente.setText("");
        nuevoClienteDialog.txtUsernameNCliente.setText("");
        nuevoClienteDialog.txtEdadNCliente.setText("");
        nuevoClienteDialog.txtNombreNCliente.setText("");

    }
    public void borrarCamposServicio(NuevoServicioDialog nuevoServicioDialog) {
        nuevoServicioDialog.txtNombreNServicio.setText("");
        nuevoServicioDialog.txtPrecioNServicio.setText("");

    }

}
