package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.utility.DialogFactory;
import com.xephorium.armory.ui.utility.DirectoryChooser;
import com.xephorium.armory.ui.utility.DisplayUtility;
import com.xephorium.armory.ui.InstallationBrowsePanel.InstallationBrowsePanelListener;
import com.xephorium.armory.ui.utility.DirectoryChooser.DirectoryChooserListener;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class ArmoryWindow implements InstallationBrowsePanelListener, DirectoryChooserListener {


    /*--- Variables ---*/

    private final int WINDOW_HEIGHT = 500;
    private final int WINDOW_WIDTH = 700;
    private final String WINDOW_TITLE = "  Armory";

    private JFrame frame;
    private InstallationBrowsePanel installationBrowsePanel;
    private DirectoryChooser directoryChooser;


    /*--- Constructor(s) ---*/

    public ArmoryWindow() {

        setGlobalLookAndFeel();
        initializeViewClasses();
        assembleWindowFrame();
    }


    /*--- Public Methods --*/

    public void displayWindow() {
        frame.setVisible(true);
    }


    /*--- Private Methods --*/

    private void setGlobalLookAndFeel() {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            // A Whole Lot of Nothin'
        }
    }

    private void initializeViewClasses() {

        frame = new JFrame(WINDOW_TITLE);
        installationBrowsePanel = new InstallationBrowsePanel(this);
        directoryChooser = new DirectoryChooser(this);
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
        directoryChooser.displayChooser();
    }

    @Override
    public void handleDirectorySelection(String directory) {
        if (true /* Valid Halo Wars Installation */) {
            installationBrowsePanel.setValidInstallationDirectory(directory);
            DialogFactory.createGameFoundDialog(frame);
        } else {
            installationBrowsePanel.setInvalidInstallationDirectory();
            DialogFactory.createGameNotFoundDialog(frame);
        }
    }
}
