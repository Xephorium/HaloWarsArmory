package com.xephorium.armory.ui.utility;

import com.xephorium.armory.ui.ArmoryWindowListener;
import com.xephorium.armory.ui.resource.content.ArmoryContent;

import javax.swing.*;
import java.io.File;

public class DirectoryChooser extends JFileChooser {


    /*--- Variables ---*/

    private ArmoryWindowListener listener;


    /*--- Constructor ---*/

    public DirectoryChooser(ArmoryWindowListener listener) {
        this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.setDialogTitle(ArmoryContent.DIALOG_CHOOSE_DIRECTORY_TITLE);
        this.listener = listener;
    }


    /*--- Public Methods ---*/

    public void displayChooser() {
        int returnValue = this.showDialog(null, ArmoryContent.DIALOG_CHOOSE_DIRECTORY_BUTTON);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = this.getSelectedFile();
            listener.handleDirectorySelection(selectedFile.getAbsolutePath());
        }
    }
}
