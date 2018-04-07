package com.xephorium.armory.ui;

import com.mortennobel.imagescaling.ResampleOp;
import com.xephorium.armory.model.Profile;
import com.xephorium.armory.ui.resource.color.ArmoryColor;
import com.xephorium.armory.ui.resource.dimension.ArmoryDimension;
import com.xephorium.armory.ui.resource.image.ArmoryImage;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ProfilePreviewPanel extends JPanel {


    /*--- Variables ---*/

    private static final boolean RENDER_PREVIEW = true;

    private static final int PREVIEW_HEIGHT = ArmoryDimension.PREVIEW_PANEL_HEIGHT;
    private static final int PREVIEW_WIDTH = ArmoryDimension.PREVIEW_PANEL_WIDTH;

    private ResampleOp imageResampler;

    private BufferedImage scaledBackgroundImage;
    private BufferedImage scaledMaskWarthogColor;
    private BufferedImage scaledMaskWarthogShine;
    private BufferedImage scaledMaskSelectorColor;
    private BufferedImage scaledMaskUNSCMinimapColor;
    private BufferedImage currentPreview = null;

    private Profile selectedProfile;


    /*--- Constructor ---*/

    public ProfilePreviewPanel() {
        super();

        if (RENDER_PREVIEW) {
            initializeGraphicsUtilities();
            initializeImages();

            this.setBackground(Color.WHITE);
            this.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
            this.setPreferredSize(new Dimension(0, ArmoryDimension.PREVIEW_PANEL_HEIGHT));
        } else {
            this.setBackground(Color.WHITE);
            this.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_LIGHT));
            this.setPreferredSize(new Dimension(0, ArmoryDimension.PREVIEW_PANEL_HEIGHT));
            this.add(createBlankPanel(), BorderLayout.CENTER);
        }
    }


    /*--- Public Methods  ---*/

    public void setSelectedProfile(Profile profile) {
        selectedProfile = profile.cloneProfile();
        currentPreview = null;
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        if (RENDER_PREVIEW && selectedProfile != null) {

            if (currentPreview == null) {

                // Recreate currentPreviewImage
                currentPreview = new BufferedImage(PREVIEW_WIDTH, PREVIEW_HEIGHT, BufferedImage.TYPE_INT_ARGB);
                Graphics2D graphics2D = currentPreview.createGraphics();
                graphics2D.drawImage(scaledBackgroundImage, 0, 0, null);
                graphics2D.drawImage(tintImage(scaledMaskWarthogColor, selectedProfile.getColor(Profile.ColorType.UNIT)), 0, 0, null);
                graphics2D.drawImage(tintImage(scaledMaskWarthogShine, new Color(255, 255, 225)), 0, 0, null);
                graphics2D.drawImage(tintImage(scaledMaskSelectorColor, selectedProfile.getColor(Profile.ColorType.SELECTOR)), 0, 0, null);
                graphics2D.drawImage(tintImage(scaledMaskUNSCMinimapColor, selectedProfile.getColor(Profile.ColorType.MINIMAP_ICON)), 0, 0, null);
                graphics2D.dispose();
            }

            // Draw currentPreviewImage
            graphics.drawImage(currentPreview, 0, 0, null);
        }
    }


    /*--- Private Rendering Methods ---*/

    private BufferedImage rescaleImage(BufferedImage bufferedImage) {
        return imageResampler.filter(bufferedImage, null);
    }

    private BufferedImage tintImage(BufferedImage inputImage, Color color) {
        return maskBufferedImageWithAlpha(createColoredBufferedImage(inputImage, color), inputImage);
    }

    private BufferedImage createColoredBufferedImage(BufferedImage inputImage, Color color) {
        BufferedImage newBufferedImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = newBufferedImage.createGraphics();

        graphics.setPaint(color);
        graphics.fillRect(0, 0, newBufferedImage.getWidth(), newBufferedImage.getHeight());
        return newBufferedImage;
    }

    private BufferedImage maskBufferedImageWithAlpha(BufferedImage inputImage, BufferedImage outputImage) {
        final int width = inputImage.getWidth();
        int[] imgData = new int[width];
        int[] maskData = new int[width];

        for (int y = 0; y < inputImage.getHeight(); y++) {

            // fetch a line of data from each image
            inputImage.getRGB(0, y, width, 1, imgData, 0, 1);
            outputImage.getRGB(0, y, width, 1, maskData, 0, 1);

            // apply the mask
            for (int x = 0; x < width; x++) {
                int color = imgData[x] & 0x00FFFFFF; // mask away any alpha present
                int maskColor = (maskData[x] & 0x00FF0000) << 8; // shift red into alpha bits
                color |= maskColor;
                imgData[x] = color;
            }

            // replace the data
            inputImage.setRGB(0, y, width, 1, imgData, 0, 1);
        }
        return inputImage;
    }

    /*--- Private Setup Methods ---*/

    private void initializeGraphicsUtilities() {
        imageResampler = new ResampleOp (PREVIEW_WIDTH, PREVIEW_HEIGHT);
    }

    private void initializeImages() {
        scaledBackgroundImage = rescaleImage(ArmoryImage.PREVIEW_BACKGROUND);
        scaledMaskWarthogColor = rescaleImage(ArmoryImage.PREVIEW_MASK_WARTHOG_COLOR);
        scaledMaskWarthogShine = rescaleImage(ArmoryImage.PREVIEW_MASK_WARTHOG_SHINE);
        scaledMaskSelectorColor = rescaleImage(ArmoryImage.PREVIEW_MASK_SELECTOR_COLOR);
        scaledMaskUNSCMinimapColor = rescaleImage(ArmoryImage.PREVIEW_MASK_UNSC_MINIMAP_COLOR);
    }

    private JPanel createBlankPanel() {
        JPanel emptyPanel = new JPanel();
        emptyPanel.setBackground(Color.WHITE);
        return emptyPanel;
    }
}
