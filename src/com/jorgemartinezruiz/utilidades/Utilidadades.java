package com.jorgemartinezruiz.utilidades;

import javax.swing.*;

public class Utilidadades {

    public static void showErrorAlert(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }


    public static void showWarningAlert(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Aviso", JOptionPane.WARNING_MESSAGE);
    }
}
