package com.xephorium.armory.ui.utility;

import javax.swing.*;

public class DialogFactory {

    public static void createGameNotFoundDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "No Halo Wars installation found.", "Game Not Found", JOptionPane.ERROR_MESSAGE);
    }

    public static void createGameFoundDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame, "Installation found!", "Game Found", JOptionPane.INFORMATION_MESSAGE);
    }
}
