package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class InstallationBrowsePanel extends JPanel {


    /*--- Variables ---*/

    private InstallationBrowsePanelListener listener;

    private ImageIcon imageIcon;
    private JTextField directoryTextField;
    private JButton browseButton;


    /*--- Constructor ---*/

    InstallationBrowsePanel(InstallationBrowsePanelListener listener) {

        initializePanelAttributes();

        this.listener = listener;
        imageIcon = ArmoryImage.ICON_INSTALLATION_UNKNOWN;
        directoryTextField = createInstallationDirectoryTextField();
        browseButton = createInstallationBrowseButton();

        clearInstallationDirectory();

        this.add(createInstallationIconLabel(imageIcon));
        this.add(new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 0)));
        this.add(directoryTextField);
        this.add(new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 0)));
        this.add(browseButton);
    }


    /*--- Public Methods ---*/

    public void setValidInstallationDirectory(String directory) {
        directoryTextField.setText(" " + directory);
        imageIcon.setImage(ArmoryImage.ICON_INSTALLATION_FOUND.getImage());
        this.repaint();
    }

    public void setInvalidInstallationDirectory() {
        directoryTextField.setText(" Choose an installation directory...");
        imageIcon.setImage(ArmoryImage.ICON_INSTALLATION_INVALID.getImage());
        this.repaint();
    }

    public void clearInstallationDirectory() {
        directoryTextField.setText(" Choose an installation directory...");
        imageIcon.setImage(ArmoryImage.ICON_INSTALLATION_UNKNOWN.getImage());
        this.repaint();
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new EmptyBorder(
                ArmoryDimension.WINDOW_PADDING_VERTICAL,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL,
                ArmoryDimension.PANEL_PADDING/2,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL));
        this.setBackground(ArmoryColor.WHITE);
    }

    private JLabel createInstallationIconLabel(ImageIcon imageIcon) {
        return new JLabel("", imageIcon, JLabel.CENTER);
    }

    private JTextField createInstallationDirectoryTextField() {
        JTextField textField = new JTextField();
        textField.setEditable(false);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.handleBrowseButtonClick();
            }
        });

        return textField;
    }

    private JButton createInstallationBrowseButton() {
        JButton button = new JButton("Browse");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.handleBrowseButtonClick();
            }
        });

        return button;
    }


    /*--- Listener Interface ---*/

    interface InstallationBrowsePanelListener {

        void handleBrowseButtonClick();
    }
}
