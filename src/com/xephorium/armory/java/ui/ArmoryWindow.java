package com.xephorium.armory.java.ui;

import javax.swing.*;
import java.awt.*;

public class ArmoryWindow {


    /*--- Variables ---*/

    private final int WINDOW_HEIGHT = 550;
    private final int WINDOW_WIDTH = 800;

    private final String WINDOW_TITLE = "  Armory";

    private JFrame frame = new JFrame(WINDOW_TITLE);


    /*--- Constructor(s) ---*/

    public ArmoryWindow() {
        initializeUserInterface();
    }


    /*--- Public Methods --*/

    public void displayWindow() {
        frame.setVisible(true);
    }


    /*--- Private Methods --*/

    private void initializeUserInterface() {

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(ViewUtility.getWindowStartX(WINDOW_WIDTH), ViewUtility.getWindowStartY(WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(144, 198, 252));
        frame.add(panel, BorderLayout.CENTER);
    }
}
