package com.jorgemartinezruiz.generarReporte;

import com.jorgemartinezruiz.gui.Modelo;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenerarReporte {
    Connection conexion;

    public void generarReporte(Modelo modelo) throws SQLException {
        //desde la variable de conexion coge la conexion de la base de datos
        conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddaseguradoratfg", "root", "");
        try {
            //Compilamos el archivo JRXML para convertirlo en jasper
            JasperReport reporte= JasperCompileManager.compileReport("src\\reportes\\Empleados.jrxml");
            //Pintamos el jasper y cogemos tambien la conexion de la base de datos
            JasperPrint print= JasperFillManager.fillReport(reporte,null,conexion);
            //visualizamos el informe
            JasperViewer jasperViewer= new JasperViewer(print,false);
            jasperViewer.show();
            //exportamos el jasper a pdf y lo guardamos en la carpeta de documentos generados
            JasperExportManager.exportReportToPdfFile(print, "pdf\\Empleados.pdf");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
    public void generarReporte2(Modelo modelo) throws SQLException {
        //desde la variable de conexion coge la conexion de la base de datos
        conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/bbddaseguradoratfg", "root", "");
        try {
            //Compilamos el archivo JRXML para convertirlo en jasper
            JasperReport reporte= JasperCompileManager.compileReport("src\\reportes\\Ventasporempleado.jrxml");
            //Pintamos el jasper y cogemos tambien la conexion de la base de datos
            JasperPrint print= JasperFillManager.fillReport(reporte,null,conexion);
            //visualizamos el informe
            JasperViewer jasperViewer= new JasperViewer(print,false);
            jasperViewer.show();
            //exportamos el jasper a pdf y lo guardamos en la carpeta de documentos generados
            JasperExportManager.exportReportToPdfFile(print, "pdf\\VentasPorEmpleado.pdf");
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }

}
