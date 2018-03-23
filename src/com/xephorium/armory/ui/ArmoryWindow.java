package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.utility.DisplayUtility;
import com.xephorium.armory.ui.InstallationBrowsePanel.InstallationBrowsePanelListener;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ArmoryWindow implements InstallationBrowsePanelListener {


    /*--- Variables ---*/

    private final int WINDOW_HEIGHT = 500;
    private final int WINDOW_WIDTH = 700;
    private final String WINDOW_TITLE = "  Armory";

    private JFrame frame;
    private InstallationBrowsePanel installationBrowsePanel;


    /*--- Constructor(s) ---*/

    public ArmoryWindow() {

        initializePanels();
        assembleWindowFrame();
    }


    /*--- Public Methods --*/

    public void displayWindow() {
        frame.setVisible(true);
    }


    /*--- Private Methods --*/

    private void initializePanels() {

        frame = new JFrame(WINDOW_TITLE);
        installationBrowsePanel = new InstallationBrowsePanel(this);
    }

    private void assembleWindowFrame() {

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(DisplayUtility.getWindowStartX(WINDOW_WIDTH), DisplayUtility.getWindowStartY(WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel panel = new JPanel();
        panel.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        frame.add(panel, BorderLayout.CENTER);

        frame.add(installationBrowsePanel, BorderLayout.PAGE_START);
    }


    /*--- Interface Methods ---*/

    @Override
    public void handleBrowseButtonClick() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnValue = fileChooser.showDialog(null, "Select");

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            installationBrowsePanel.setValidInstallationDirectory(selectedFile.getAbsolutePath());
        }
    }
}
