package com.xephorium.armory.ui;

import com.xephorium.armory.model.ColorProfile;
import com.xephorium.armory.model.ColorProfile.ColorType;
import com.xephorium.armory.repository.MockColorProfileRepository;
import com.xephorium.armory.ui.utility.ColorChooser;
import com.xephorium.armory.ui.utility.DialogFactory;
import com.xephorium.armory.ui.utility.DirectoryChooser;
import com.xephorium.armory.ui.utility.DisplayUtility;
import com.xephorium.armory.ui.InstallDirectoryPanel.InstallDirectoryPanelListener;
import com.xephorium.armory.ui.utility.DirectoryChooser.DirectoryChooserListener;
import com.xephorium.armory.ui.FactionConfigurationPanel.FactionConfigurationPanelListener;
import com.xephorium.armory.ui.ColorProfilePanel.ColorProfilePanelListener;

import javax.swing.*;
import java.awt.*;


/*
 * Chris Cruzen                                March 2018
 * Armory Window
 *
 *   ArmoryWindow is the single, top-layer view class of
 * Halo Wars Armory. Its role is to take care of all GUI
 * configuration/display behavior and alert the presenter
 * layer when an action has taken place by ActionListener.
 *
 *   View classes created and managed by ArmoryWindow
 * should remain as near to stateless as possible.
 *
 */


public class ArmoryWindow implements
        InstallDirectoryPanelListener,
        DirectoryChooserListener,
        FactionConfigurationPanelListener,
        ColorProfilePanelListener {


    /*--- Variables ---*/

    private final int WINDOW_HEIGHT = 500;
    private final int WINDOW_WIDTH = 665;
    private final String WINDOW_TITLE = "  Halo Wars Armory";

    private JFrame frame;
    private InstallDirectoryPanel installDirectoryPanel;
    private FactionConfigurationPanel factionConfigurationPanel;
    private ColorProfilePanel colorProfilePanel;
    private DirectoryChooser directoryChooser;


    /*--- Constructor(s) ---*/

    public ArmoryWindow() {

        setGlobalLookAndFeel();
        initializeViewClasses();
        assembleWindowFrame();

        ColorProfile[] colorProfileList = MockColorProfileRepository.getProfileList();

        factionConfigurationPanel.updateColorProfiles(colorProfileList);
        colorProfilePanel.setColorProfileList(colorProfileList);
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
        installDirectoryPanel = new InstallDirectoryPanel(this);
        factionConfigurationPanel = new FactionConfigurationPanel(this);
        colorProfilePanel = new ColorProfilePanel(this);
        directoryChooser = new DirectoryChooser(this);
    }

    private void assembleWindowFrame() {

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(DisplayUtility.getWindowStartX(WINDOW_WIDTH), DisplayUtility.getWindowStartY(WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        frame.add(colorProfilePanel, BorderLayout.EAST);
        frame.add(factionConfigurationPanel, BorderLayout.CENTER);
        frame.add(installDirectoryPanel, BorderLayout.PAGE_START);
    }


    /*--- Interface Methods ---*/

    @Override
    public void handleBrowseButtonClick() {
        directoryChooser.displayChooser();
    }

    @Override
    public void handleDirectorySelection(String directory) {
        if (true /* Valid Halo Wars Installation */) {
            installDirectoryPanel.setValidInstallDirectory(directory);
            DialogFactory.createGameFoundDialog(frame);
        } else {
            installDirectoryPanel.setInvalidInstallDirectory();
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

    @Override
    public void handleProfileColorClick(ColorType colorType, Color currentColor) {
        DialogFactory.createColorChooserDialog(currentColor,new ColorChooser.ColorChooserListener() {
            @Override
            public void onColorSelected(Color color) {
                colorProfilePanel.setWorkingProfileColor(colorType, color);
            }

            @Override
            public void onDialogClose() {
                // Do Nothing
            }
        });
    }

    @Override
    public void handleProfileSaveClick() {
        // TODO - Save Selected ColorProfile
    }
}
