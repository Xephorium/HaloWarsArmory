package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;

import javax.swing.*;

public class ProfilePreviewPanel extends JPanel {

    public ProfilePreviewPanel() {
        super();
        this.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        this.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
    }
}
