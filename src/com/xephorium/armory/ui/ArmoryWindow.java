package com.xephorium.armory.ui;

import com.xephorium.armory.model.Profile;
import com.xephorium.armory.model.ProfileList;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.utility.ColorChooser;
import com.xephorium.armory.ui.utility.DialogFactory;
import com.xephorium.armory.ui.utility.DirectoryChooser;
import com.xephorium.armory.ui.utility.DisplayUtility;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;


/*
 * Chris Cruzen                                March 2018
 * Armory Window
 *
 *   ArmoryWindow is the single, top-layer view class of
 * Halo Wars Armory. Its role is to drive the interface
 * and alert the presenter layer when a user action has
 * taken place.
 *
 *   View classes created and managed by ArmoryWindow
 * should remain as near to stateless as possible.
 *
 */


public class ArmoryWindow {


    /*--- Variables ---*/

    private final int WINDOW_HEIGHT = 500;
    private final int WINDOW_WIDTH = 665;
    private final String WINDOW_TITLE = "  Halo Wars Armory";

    private ArmoryWindowListener listener;

    private JFrame frame;
    private InstallDirectoryPanel installDirectoryPanel;
    private FactionConfigurationPanel factionConfigurationPanel;
    private ProfileConfigurationPanel profileConfigurationPanel;
    private ProfilePreviewPanel profilePreviewPanel;
    private DirectoryChooser directoryChooser;


    /*--- Constructor(s) ---*/

    public ArmoryWindow(ArmoryWindowListener listener) {
        this.listener = listener;

        setGlobalLookAndFeel();
        initializeFrameAttributes();
        initializeViewClasses();
        createProfilePanels();

        frame.add(installDirectoryPanel, BorderLayout.PAGE_START);
        frame.add(factionConfigurationPanel, BorderLayout.CENTER);
        frame.add(createProfilePanels(), BorderLayout.EAST);
    }


    /*--- Core ArmoryWindow Functionality ---*/


    // General

    public void displayWindow() {
        frame.setVisible(true);
    }

    public void updateProfileList(ProfileList profileList) {
        factionConfigurationPanel.updateProfileList(profileList);
        profileConfigurationPanel.updateProfileList(profileList);
    }


    // Install Directory

    public void displayDirectoryChooser() {
        directoryChooser.displayChooser();
    }

    public void displayGameFoundDialog() {
        DialogFactory.createGameFoundDialog(frame);
    }

    public void displayGameNotFoundDialog() {
        DialogFactory.createGameNotFoundDialog(frame);
    }

    public void setValidInstallDirectory(String directory) {
        installDirectoryPanel.setValidInstallDirectory(directory);
    }

    public void setInvalidInstallDirectory() {
        installDirectoryPanel.setInvalidInstallDirectory();
    }


    // Faction Configuration

    public void updateUNSCPlayerConfiguration(List<Integer> unscPlayerProfiles) {
        factionConfigurationPanel.updateUNSCPlayerConfiguration(unscPlayerProfiles);
    }

    public void updateCovenantPlayerConfiguration(List<Integer> covenantPlayerProfiles) {
        factionConfigurationPanel.updateCovenantPlayerConfiguration(covenantPlayerProfiles);
    }


    // Profile Configuration

    public void setSelectedProfile(Profile profile) {
        profileConfigurationPanel.setSelectedProfile(profile);
        profileConfigurationPanel.setWorkingProfile(profile);
    }

    public void setWorkingProfile(Profile profile) {
        profileConfigurationPanel.setWorkingProfile(profile);
    }

    public void displayColorChooserDialog(Color initialColor, ColorChooser.ColorChooserListener listener) {
        DialogFactory.createColorChooserDialog(initialColor, listener);
    }

    public void displayProfileMustHaveNameDialog() {
        DialogFactory.createProfileMustHaveNameDialog(frame);
    }

    public void displayProfileSavedDialog() {
        DialogFactory.createProfileSavedDialog(frame);
    }

    public void displayNoChangesToSaveDialog() {
        DialogFactory.createNoChangesToSaveDialog(frame);
    }


    /*--- Private Methods --*/

    private void setGlobalLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // A Whole Lot of Nothin'
        }
    }

    private void initializeFrameAttributes() {
        frame = new JFrame(WINDOW_TITLE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setLocation(DisplayUtility.getWindowStartX(WINDOW_WIDTH), DisplayUtility.getWindowStartY(WINDOW_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
    }

    private void initializeViewClasses() {
        installDirectoryPanel = new InstallDirectoryPanel(listener);
        factionConfigurationPanel = new FactionConfigurationPanel(listener);
        profileConfigurationPanel = new ProfileConfigurationPanel(listener);
        profilePreviewPanel = new ProfilePreviewPanel();
        directoryChooser = new DirectoryChooser(listener);
    }

    private JPanel createProfilePanels() {
        JPanel eastPanel = new JPanel(new GridLayout(2, 1, 0, ArmoryDimension.PANEL_PADDING));
        eastPanel.setBorder(new EmptyBorder(
                ArmoryDimension.PANEL_PADDING/2,
                ArmoryDimension.PANEL_PADDING/2,
                ArmoryDimension.WINDOW_PADDING_VERTICAL,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL));
        eastPanel.setPreferredSize(new Dimension(ArmoryDimension.COLOR_PROFILE_PANEL_WIDTH, 0));
        eastPanel.setBackground(ArmoryColor.WINDOW_BACKGROUND_COLOR);

        eastPanel.add(profileConfigurationPanel);
        eastPanel.add(profilePreviewPanel);
        return eastPanel;
    }
}
