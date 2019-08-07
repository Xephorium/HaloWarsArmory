package com.xephorium.armory.ui.resource.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ArmoryImage {


    /*--- Image Resources ---*/

    private boolean USE_DETAILED_ICONS = true;

    private URL ICON_APPLICATION_MAIN_128_PATH = getClass().getResource("iconApplicationMain128.png");
    private URL ICON_APPLICATION_MAIN_92_PATH = getClass().getResource("iconApplicationMain92.png");
    private URL ICON_APPLICATION_MAIN_64_PATH = getClass().getResource("iconApplicationMain64.png");
    private URL ICON_APPLICATION_MAIN_56_PATH = getClass().getResource("iconApplicationMain56.png");
    private URL ICON_APPLICATION_MAIN_48_PATH = getClass().getResource("iconApplicationMain48.png");
    private URL ICON_APPLICATION_MAIN_32_PATH = getClass().getResource("iconApplicationMain32.png");
    private URL ICON_APPLICATION_MAIN_24_PATH = getClass().getResource("iconApplicationMain24.png");
    private URL ICON_APPLICATION_MAIN_16_PATH = getClass().getResource("iconApplicationMain16.png");

    private URL ICON_UNSC_SIMPLE_PATH = getClass().getResource("iconFactionUNSCSimple.png");
    private URL ICON_UNSC_DETAILED_PATH = getClass().getResource("iconFactionUNSCDetailed.png");
    private URL ICON_COVENANT_SIMPLE_PATH = getClass().getResource("iconFactionCovenantSimple.png");
    private URL ICON_COVENANT_DETAILED_PATH = getClass().getResource("iconFactionCovenantDetailed.png");
    private URL ICON_FAILURE_DIALOG_PATH = getClass().getResource("iconFailureDialog.png");
    private URL ICON_FAILURE_INSTALLATION_PATH = getClass().getResource("iconFailureInstallation.png");
    private URL ICON_SUCCESS_DIALOG_PATH = getClass().getResource("iconSuccessDialog.png");
    private URL ICON_SUCCESS_INSTALLATION_PATH = getClass().getResource("iconSuccessInstallation.png");
    private URL ICON_UNKNOWN_DIALOG_PATH = getClass().getResource("iconUnknownDialog.png");
    private URL ICON_UNKNOWN_INSTALLATION_PATH = getClass().getResource("iconUnknownInstallation.png");
    private URL PREVIEW_BACKGROUND_PATH = getClass().getResource("previewBackground.png");
    private URL PREVIEW_MASK_WARTHOG_COLOR_PATH = getClass().getResource("previewMaskWarthogColor.png");
    private URL PREVIEW_MASK_WARTHOG_SHINE_PATH = getClass().getResource("previewMaskWarthogShine.png");
    private URL PREVIEW_MASK_SELECTOR_COLOR_PATH = getClass().getResource("previewMaskSelectorColor.png");
    private URL PREVIEW_MASK_UNSC_MINIMAP_COLOR_PATH = getClass().getResource("previewMaskUNSCMinimapColor.png");
    private URL PREVIEW_MASK_UNSC_PAUSE_COLOR_PATH = getClass().getResource("previewMaskUNSCPauseColor.png");
    private URL PREVIEW_MASK_UNSC_CORPSE_COLOR_PATH = getClass().getResource("previewMaskUNSCCorpseColor.png");


    /*--- Public Image Retrieval Methods ---*/

    public List<Image> ICON_APPLICATION_MAIN_LIST = generateIconApplicationMainList();

    public ImageIcon ICON_UNSC = new ImageIcon(USE_DETAILED_ICONS ? ICON_UNSC_DETAILED_PATH : ICON_UNSC_SIMPLE_PATH, "UNSC Faction");
    public ImageIcon ICON_COVENANT = new ImageIcon(USE_DETAILED_ICONS ? ICON_COVENANT_DETAILED_PATH : ICON_COVENANT_SIMPLE_PATH, "Covenant Faction");
    public ImageIcon ICON_FAILURE_DIALOG = new ImageIcon(ICON_FAILURE_DIALOG_PATH, "Failure");
    public ImageIcon ICON_FAILURE_INSTALLATION = new ImageIcon(ICON_FAILURE_INSTALLATION_PATH, "Installation Invalid");
    public ImageIcon ICON_SUCCESS_DIALOG = new ImageIcon(ICON_SUCCESS_DIALOG_PATH, "Success");
    public ImageIcon ICON_SUCCESS_INSTALLATION = new ImageIcon(ICON_SUCCESS_INSTALLATION_PATH, "Installation Found");
    public ImageIcon ICON_UNKNOWN_DIALOG = new ImageIcon(ICON_UNKNOWN_DIALOG_PATH, "Unknown");
    public ImageIcon ICON_UNKNOWN_INSTALLATION = new ImageIcon(ICON_UNKNOWN_INSTALLATION_PATH, "Installation Unknown");

    public BufferedImage PREVIEW_BACKGROUND = readBufferedImage(PREVIEW_BACKGROUND_PATH);
    public BufferedImage PREVIEW_MASK_WARTHOG_COLOR = readBufferedImage(PREVIEW_MASK_WARTHOG_COLOR_PATH);
    public BufferedImage PREVIEW_MASK_WARTHOG_SHINE = readBufferedImage(PREVIEW_MASK_WARTHOG_SHINE_PATH);
    public BufferedImage PREVIEW_MASK_SELECTOR_COLOR = readBufferedImage(PREVIEW_MASK_SELECTOR_COLOR_PATH);
    public BufferedImage PREVIEW_MASK_UNSC_MINIMAP_COLOR = readBufferedImage(PREVIEW_MASK_UNSC_MINIMAP_COLOR_PATH);
    public BufferedImage PREVIEW_MASK_UNSC_PAUSE_COLOR = readBufferedImage(PREVIEW_MASK_UNSC_PAUSE_COLOR_PATH);
    public BufferedImage PREVIEW_MASK_UNSC_CORPSE_COLOR = readBufferedImage(PREVIEW_MASK_UNSC_CORPSE_COLOR_PATH);


    /*--- Private Utility Methods  ---*/

    private List<Image> generateIconApplicationMainList() {
        List<Image> icons = new ArrayList<>();
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_128_PATH, "Icon Application Main").getImage());
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_92_PATH, "Icon Application Main").getImage());
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_64_PATH, "Icon Application Main").getImage());
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_56_PATH, "Icon Application Main").getImage());
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_48_PATH, "Icon Application Main").getImage());
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_32_PATH, "Icon Application Main").getImage());
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_24_PATH, "Icon Application Main").getImage());
        icons.add(new ImageIcon(ICON_APPLICATION_MAIN_16_PATH, "Icon Application Main").getImage());
        return icons;
    }

    private static BufferedImage readBufferedImage(URL url) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(url);
        } catch (IOException exception) {
            bufferedImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        }
        return bufferedImage;
    }
}
