package com.xephorium.armory.ui;

import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import java.awt.*;

public class ProfilePreviewPanel extends JPanel {


    /*--- Variables ---*/

    private static final int PREVIEW_HEIGHT = ArmoryDimension.PREVIEW_PANEL_HEIGHT;
    private static final int PREVIEW_WIDTH = ArmoryDimension.PREVIEW_PANEL_WIDTH;

    private Image backgroundImage;
    private Image hudImage;


    /*--- Constructor ---*/

    public ProfilePreviewPanel() {
        super();

        initializeImages();

        this.setBackground(Color.WHITE);
        this.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
        this.setPreferredSize(new Dimension(0, ArmoryDimension.PREVIEW_PANEL_HEIGHT));
    }


    /*--- Public Methods  ---*/

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (backgroundImage != null) {
            graphics.drawImage(backgroundImage, 0, 0, this.getWidth(), PREVIEW_HEIGHT, null);
            graphics.drawImage(hudImage, 0, 0, this.getWidth(), PREVIEW_HEIGHT, null);
        }
    }


    /*--- Private Methods ---*/

    private void initializeImages() {
        backgroundImage = ArmoryImage.PREVIEW_BACKGROUND.getImage();
        hudImage = ArmoryImage.PREVIEW_HUD.getImage();
    }
}
