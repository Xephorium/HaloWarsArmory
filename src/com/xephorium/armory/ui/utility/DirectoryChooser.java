package com.xephorium.armory.ui.utility;

import javax.swing.*;
import java.io.File;

public class DirectoryChooser extends JFileChooser {


    /*--- Variables ---*/

    private DirectoryChooserListener listener;


    /*--- Constructor ---*/

    public DirectoryChooser(DirectoryChooserListener listener) {
        this.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        this.listener = listener;
    }


    /*--- Public Methods ---*/

    public void displayChooser() {
        int returnValue = this.showDialog(null, "Select");

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = this.getSelectedFile();
            listener.handleDirectorySelection(selectedFile.getAbsolutePath());
        }
    }


    /*--- Listener Interface ---*/

    public interface DirectoryChooserListener {

        void handleDirectorySelection(String directory);
    }
}
