package com.xephorium.armory.ui;

import com.mortennobel.imagescaling.ResampleOp;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProfilePreviewPanel extends JPanel {


    /*--- Variables ---*/

    private static final int PREVIEW_HEIGHT = ArmoryDimension.PREVIEW_PANEL_HEIGHT;
    private static final int PREVIEW_WIDTH = ArmoryDimension.PREVIEW_PANEL_WIDTH;

    ResampleOp imageResampler;
    private BufferedImage backgroundImage;
    private BufferedImage hudImage;


    /*--- Constructor ---*/

    public ProfilePreviewPanel() {
        super();

        initializeGraphicsUtilities();
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
            graphics.drawImage(rescaleImage(backgroundImage), 0, 0, null);
            graphics.drawImage(rescaleImage(hudImage), 0, 0, null);
        }
    }


    /*--- Private Rendering Methods ---*/

    private BufferedImage rescaleImage(BufferedImage bufferedImage) {
        return imageResampler.filter(bufferedImage, null);
    }

    /*--- Private Setup Methods ---*/

    private void initializeGraphicsUtilities() {
        imageResampler = new ResampleOp (PREVIEW_WIDTH, PREVIEW_HEIGHT);
    }

    private void initializeImages() {
        backgroundImage = ArmoryImage.PREVIEW_BACKGROUND;
        hudImage = ArmoryImage.PREVIEW_HUD;
    }
}
