package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;

import javax.swing.*;
import java.awt.*;

public class ProfilePreviewPanel extends JPanel {

    public ProfilePreviewPanel() {
        super();
        this.setBackground(ArmoryColor.WINDOW_TEST_COLOR);
        this.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        this.setPreferredSize(new Dimension(0, ArmoryDimension.PREVIEW_PANEL_HEIGHT));
    }
}
