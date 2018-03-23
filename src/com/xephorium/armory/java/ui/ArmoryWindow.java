package com.xephorium.armory.java.ui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ArmoryWindow {


    /*--- Variables ---*/

    private final int WINDOW_HEIGHT = 500;
    private final int WINDOW_WIDTH = 700;

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

        JPanel installDirectoryPanel = new JPanel();
        installDirectoryPanel.setLayout(new BoxLayout(installDirectoryPanel, BoxLayout.X_AXIS));
        installDirectoryPanel.setBorder(new EmptyBorder(20, 20, 10, 20));
        installDirectoryPanel.setBackground(new Color(255, 255, 255));


        ImageIcon imageIcon = new ImageIcon("src\\com\\xephorium\\armory\\res\\icon\\iconCheckmark.png", "Checkmark Icon");
        JLabel installIconLabel = new JLabel("", imageIcon, JLabel.CENTER);

        JTextField installDirectoryField = new JTextField();
        installDirectoryField.setText("Choose an install directory...");
        installDirectoryField.setEnabled(false);

        JButton browseButton = new JButton("Browse");

        installDirectoryPanel.add(installIconLabel);
        installDirectoryPanel.add(new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 0)));
        installDirectoryPanel.add(installDirectoryField);
        installDirectoryPanel.add(new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 0)));
        installDirectoryPanel.add(browseButton);

        frame.add(installDirectoryPanel, BorderLayout.PAGE_START);
    }
}
