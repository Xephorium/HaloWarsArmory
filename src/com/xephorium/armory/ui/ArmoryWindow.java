package com.xephorium.armory.ui;

import com.xephorium.armory.ui.utility.DialogFactory;
import com.xephorium.armory.ui.utility.DirectoryChooser;
import com.xephorium.armory.ui.utility.DisplayUtility;
import com.xephorium.armory.ui.InstallationBrowsePanel.InstallationBrowsePanelListener;
import com.xephorium.armory.ui.utility.DirectoryChooser.DirectoryChooserListener;
import com.xephorium.armory.ui.FactionConfigurationPanel.FactionConfigurationPanelListener;

import javax.swing.*;
import java.awt.*;

public class ArmoryWindow implements
        InstallationBrowsePanelListener,
        DirectoryChooserListener,
        FactionConfigurationPanelListener {


    /*--- Variables ---*/

    private final int WINDOW_HEIGHT = 500;
    private final int WINDOW_WIDTH = 665;
    private final String WINDOW_TITLE = "  Halo Wars Armory";

    private JFrame frame;
    private InstallationBrowsePanel installationBrowsePanel;
    private ColorProfilePanel colorProfilePanel;
    private FactionConfigurationPanel factionConfigurationPanel;
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
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // A Whole Lot of Nothin'
        }
    }

    private void initializeViewClasses() {

        frame = new JFrame(WINDOW_TITLE);
        installationBrowsePanel = new InstallationBrowsePanel(this);
        colorProfilePanel = new ColorProfilePanel();
        factionConfigurationPanel = new FactionConfigurationPanel(this);
        directoryChooser = new DirectoryChooser(this);
    }

    private void assembleWindowFrame() {

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(DisplayUtility.getWindowStartX(WINDOW_WIDTH), DisplayUtility.getWindowStartY(WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(colorProfilePanel, BorderLayout.EAST);
        frame.add(factionConfigurationPanel, BorderLayout.CENTER);
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

    @Override
    public void handleUNSCPlayerUpdate(int playerNumber, String profileName) {
        // TODO - Update UNSC Player
    }

    @Override
    public void handleCovenantPlayerUpdate(int playerNumber, String profileName) {
        // TODO - Update Covenant Player
    }

    @Override
    public void handleUNSCConfigurationReset() {
        // TODO - Reset UNSC Configuration
    }

    @Override
    public void handleCovenantConfigurationReset() {
        // TODO - Reset Covenant Configuration
    }

    @Override
    public void handleUNSCConfigurationSave() {
        // TODO - Save UNSC Configuration
    }

    @Override
    public void handleCovenantConfigurationSave() {
        // TODO - Save Covenant Configuration
    }
}
