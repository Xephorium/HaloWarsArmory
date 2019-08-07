package com.xephorium.armory.ui.utility;

import com.xephorium.armory.ui.resource.content.ArmoryContent;
import com.xephorium.armory.ui.utility.ColorChooser.ColorChooserListener;
import com.xephorium.armory.ui.utility.VerifyActionDialog.VerifyActionListener;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import java.awt.*;

public class DialogFactory {


    /*--- Variables ---*/

    private static final String TITLE_LEFT_PADDING = " ";

    /*--- Dialogs ---*/

    public static void createGameNotFoundDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_GAME_NOT_FOUND_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_GAME_NOT_FOUND_TITLE,
                JOptionPane.ERROR_MESSAGE,
                ArmoryImage.ICON_FAILURE_DIALOG);
    }

    public static void createUserLocalNotFoundDialog(JFrame frame, String user) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_USER_LOCAL_NOT_FOUND_MESSAGE_START
                        + user + ArmoryContent.DIALOG_USER_LOCAL_NOT_FOUND_MESSAGE_END,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_USER_LOCAL_NOT_FOUND_TITLE,
                JOptionPane.ERROR_MESSAGE,
                ArmoryImage.ICON_FAILURE_DIALOG);
    }

    public static void createGameFoundDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_GAME_FOUND_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_GAME_FOUND_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                ArmoryImage.ICON_SUCCESS_DIALOG);
    }

    public static void createProfileMustHaveNameDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_PROFILE_MUST_HAVE_NAME_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_PROFILE_MUST_HAVE_NAME_TITLE,
                JOptionPane.ERROR_MESSAGE,
                ArmoryImage.ICON_FAILURE_DIALOG);
    }

    public static void createProblemSavingDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_PROBLEM_SAVING_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_PROBLEM_SAVING_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                ArmoryImage.ICON_FAILURE_DIALOG);
    }

    public static void createProfileSavedDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_PROFILE_SAVED_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_PROFILE_SAVED_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                ArmoryImage.ICON_SUCCESS_DIALOG);
    }

    public static void createConfigurationSavedDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_CONFIGURATION_SAVED_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_CONFIGURATION_SAVED_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                ArmoryImage.ICON_SUCCESS_DIALOG);
    }

    public static void createNoChangesToSaveDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_NO_CHANGES_TO_SAVE_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_NO_CHANGES_TO_SAVE_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                ArmoryImage.ICON_SUCCESS_DIALOG);
    }

    public static void createNoInstallationSetDialog(JFrame frame) {
        JOptionPane.showMessageDialog(frame,
                ArmoryContent.DIALOG_NO_INSTALLATION_SET_MESSAGE,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_NO_INSTALLATION_SET_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                ArmoryImage.ICON_UNKNOWN_DIALOG);
    }

    public static void createDeleteProfileDialog(VerifyActionListener listener, String profileName) {
        VerifyActionDialog verifyActionDialog = new VerifyActionDialog(listener,
                TITLE_LEFT_PADDING + ArmoryContent.DIALOG_DELETE_PROFILE_TITLE,
                ArmoryContent.DIALOG_DELETE_PROFILE_MESSAGE_START +
                        profileName + ArmoryContent.DIALOG_DELETE_PROFILE_MESSAGE_END);
        verifyActionDialog.showDialog();
    }

    public static void createColorChooserDialog(Color initialColor, ColorChooserListener listener) {
        ColorChooser colorChooser = new ColorChooser(listener);
        colorChooser.showDialog(initialColor);
    }
}
