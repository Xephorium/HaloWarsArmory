package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class InstallDirectoryPanel extends JPanel {


    /*--- Variables ---*/

    private static int COMPONENT_HORIZONTAL_PADDING = 10;

    private ArmoryWindowListener listener;

    private ImageIcon imageIcon;
    private JTextField directoryTextField;


    /*--- Constructor ---*/

    InstallDirectoryPanel(ArmoryWindowListener listener) {
        this.listener = listener;

        initializePanelAttributes();

        imageIcon = ArmoryImage.ICON_UNKNOWN_INSTALLATION;
        directoryTextField = createDirectoryTextField();

        setDefaultInstallDirectory();

        Dimension padding = new Dimension(COMPONENT_HORIZONTAL_PADDING, 0);

        this.add(createIconLabel(imageIcon));
        this.add(new Box.Filler(padding, padding, padding));
        this.add(directoryTextField);
        this.add(new Box.Filler(padding, padding, padding));
        this.add(createBrowseButton());
    }


    /*--- Public Methods ---*/

    public void setValidInstallDirectory(String directory) {
        if (directory != null) {
            directoryTextField.setText(" " + directory);
            imageIcon.setImage(ArmoryImage.ICON_SUCCESS_INSTALLATION.getImage());
            this.repaint();
        }
    }

    public void setInvalidInstallDirectory() {
        directoryTextField.setText(" Choose Halo Wars installation folder...");
        imageIcon.setImage(ArmoryImage.ICON_FAILURE_INSTALLATION.getImage());
        this.repaint();
    }

    public void setDefaultInstallDirectory() {
        directoryTextField.setText(" Choose Halo Wars installation folder...");
        imageIcon.setImage(ArmoryImage.ICON_UNKNOWN_INSTALLATION.getImage());
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
        this.setBackground(ArmoryColor.WINDOW_BACKGROUND_COLOR);
    }

    private JLabel createIconLabel(ImageIcon imageIcon) {
        return new JLabel("", imageIcon, JLabel.CENTER);
    }

    private JTextField createDirectoryTextField() {
        JTextField textField = new JTextField();
        textField.setEnabled(false);
        textField.setBackground(Color.WHITE);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.handleBrowseButtonClick();
            }
        });

        return textField;
    }

    private JButton createBrowseButton() {
        JButton button = new JButton("Browse");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.handleBrowseButtonClick();
            }
        });

        return button;
    }
}
