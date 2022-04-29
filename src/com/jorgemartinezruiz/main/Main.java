package com.jorgemartinezruiz.main;

import com.jorgemartinezruiz.gui.Controlador;
import com.jorgemartinezruiz.gui.LoginDialog;
import com.jorgemartinezruiz.gui.Modelo;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/** *
 * @author aplicacion desarrollada por Jorge Martinez
 */

public class Main {
    public static void main(String[] args) throws SQLException, IOException, FontFormatException {
        LoginDialog login = new LoginDialog();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(modelo, login);
    }

}
