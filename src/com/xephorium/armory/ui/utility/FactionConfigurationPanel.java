package com.xephorium.armory.ui.utility;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class FactionConfigurationPanel extends JPanel {


    /*--- Constructor ---*/

    public FactionConfigurationPanel() {
        initializePanelAttributes();
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new GridLayout(1, 1, 0, 0));
        this.setBorder(new EmptyBorder(
                ArmoryDimension.PANEL_PADDING/2,
                ArmoryDimension.WINDOW_PADDING_HORIZONTAL,
                ArmoryDimension.WINDOW_PADDING_VERTICAL,
                ArmoryDimension.PANEL_PADDING/2));

        JPanel tabPanel = new JPanel();
        tabPanel.setBackground(ArmoryColor.WINDOW_TEST_COLOR);

        this.add(tabPanel);
    }
}