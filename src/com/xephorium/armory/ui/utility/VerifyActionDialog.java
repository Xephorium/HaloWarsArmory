package com.xephorium.armory.ui.utility;

import com.xephorium.armory.ui.resource.content.ArmoryContent;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;

public class VerifyActionDialog {


    /*--- Variables ---*/

    private VerifyActionListener listener;
    private String title;
    private String message;


    /*--- Constructor ---*/

    VerifyActionDialog(VerifyActionListener listener, String title, String message) {
        this.listener = listener;
        this.title = title;
        this.message = message;
    }


    /*--- Public Methods ---*/

    void showDialog() {
        String[] options = new String[]{
                ArmoryContent.DIALOG_VERIFY_ACTION_YES,
                ArmoryContent.DIALOG_VERIFY_ACTION_CANCEL
        };
        int userAction = JOptionPane.showOptionDialog(null,
                message,
                title,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                ArmoryImage.ICON_UNKNOWN_DIALOG,
                options,
                options[0]);
        handleAction(userAction);
    }


    /*--- Private Methods ---*/

    private void handleAction(int actionKey) {
        switch (actionKey) {
            case 0:
                listener.onVerifyActionSelection();
                break;

            case 1:
                listener.onCancelActionSelection();
                break;

            case -1:
                listener.onDialogClose();
        }
    }


    /*--- Listener Interface ---*/

    public interface VerifyActionListener {

        void onVerifyActionSelection();

        void onCancelActionSelection();

        void onDialogClose();
    }

}
