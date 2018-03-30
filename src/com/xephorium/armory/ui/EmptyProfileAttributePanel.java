package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EmptyProfileAttributePanel extends JPanel {


    /*--- Constructor ---*/

    public EmptyProfileAttributePanel() {
        super();

        initializePanelAttributes();
        this.add(createEmptyPanel(), BorderLayout.CENTER);
    }


    /*--- Private Methods ---*/

    private void initializePanelAttributes() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(135, 0));
        this.setBorder(new EmptyBorder(0,15,0,0));
        this.setBackground(Color.WHITE);
    }

    private JPanel createEmptyPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setLayout(new GridBagLayout());
        emptyPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        emptyPanel.setBackground(ArmoryColor.WINDOW_BACKGROUND_COLOR);

        JPanel labelPanel = new JPanel(new BorderLayout());

        JLabel emptyLabelTop = new JLabel("No Profiles");
        emptyLabelTop.setForeground(ArmoryColor.TEXT_DISABLED);
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        topPanel.add(emptyLabelTop);

        JLabel emptyLabelBottom = new JLabel("Available");
        emptyLabelBottom.setForeground(ArmoryColor.TEXT_DISABLED);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        bottomPanel.add(emptyLabelBottom);

        labelPanel.add(topPanel, BorderLayout.PAGE_START);
        labelPanel.add(bottomPanel, BorderLayout.PAGE_END);

        emptyPanel.add(labelPanel);

        return emptyPanel;
    }
}
