package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class InstallationBrowsePanel extends JPanel {

    public InstallationBrowsePanel() {
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        this.setBorder(new EmptyBorder(
                ArmoryDimension.WINDOW_PADDING_VERTICAL,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL,
                ArmoryDimension.PANEL_PADDING/2,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL));
        this.setBackground(ArmoryColor.WHITE);

        ImageIcon imageIcon = ArmoryImage.ICON_INSTALLATION_FOUND;
        JLabel installIconLabel = new JLabel("", imageIcon, JLabel.CENTER);

        JTextField installDirectoryField = new JTextField();
        installDirectoryField.setText("Choose an install directory...");
        installDirectoryField.setEnabled(false);

        JButton browseButton = new JButton("Browse");

        this.add(installIconLabel);
        this.add(new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 0)));
        this.add(installDirectoryField);
        this.add(new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 0)));
        this.add(browseButton);
    }
}
