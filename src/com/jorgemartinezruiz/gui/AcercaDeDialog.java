package com.jorgemartinezruiz.gui;

import javax.swing.*;
import java.awt.*;

public class AcercaDeDialog extends JFrame {
    private JPanel panel1;
    public AcercaDeDialog() {
        startJFrame();
    }


    private void startJFrame() {
        setTitle("Aseguradora");
        this.setContentPane(panel1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(false);
        this.setEnabled(true);
        this.setSize(new Dimension(this.getWidth(), this.getHeight()));
        this.setLocationRelativeTo(null);
        this.setIconImage(new ImageIcon(getClass().getResource("../res/ICONO.png")).getImage());


    }

}
