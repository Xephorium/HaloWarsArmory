package com.xephorium.armory.ui.resource.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArmoryImage {


    /*--- Path Constants ---*/

    private static boolean USE_DETAILED_ICONS = true;

    private static String ICON_APPLICATION_MAIN_128_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain128.png";
    private static String ICON_APPLICATION_MAIN_92_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain92.png";
    private static String ICON_APPLICATION_MAIN_64_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain64.png";
    private static String ICON_APPLICATION_MAIN_56_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain56.png";
    private static String ICON_APPLICATION_MAIN_48_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain48.png";
    private static String ICON_APPLICATION_MAIN_32_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain32.png";
    private static String ICON_APPLICATION_MAIN_24_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain24.png";
    private static String ICON_APPLICATION_MAIN_16_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconApplicationMain16.png";

    private static String ICON_UNSC_SIMPLE_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionUNSCSimple.png";
    private static String ICON_UNSC_DETAILED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionUNSCDetailed.png";
    private static String ICON_COVENANT_SIMPLE_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionCovenantSimple.png";
    private static String ICON_COVENANT_DETAILED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconFactionCovenantDetailed.png";
    private static String ICON_INSTALLATION_FOUND_STYLIZED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationFoundStylized.png";
    private static String ICON_INSTALLATION_FOUND_DIALOG_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationFoundDialog.png";
    private static String ICON_INSTALLATION_INVALID_STYLIZED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationInvalidStylized.png";
    private static String ICON_INSTALLATION_INVALID_DIALOG_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationInvalidDialog.png";
    private static String ICON_INSTALLATION_UNKNOWN_STYLIZED_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\iconInstallationUnknownStylized.png";
    private static String PREVIEW_BACKGROUND_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewBackground.png";
    private static String PREVIEW_MASK_WARTHOG_COLOR_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewMaskWarthogColor.png";
    private static String PREVIEW_MASK_WARTHOG_SHINE_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewMaskWarthogShine.png";
    private static String PREVIEW_MASK_SELECTOR_COLOR_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewMaskSelectorColor.png";
    private static String PREVIEW_MASK_UNSC_MINIMAP_COLOR_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewMaskUNSCMinimapColor.png";
    private static String PREVIEW_MASK_UNSC_PAUSE_COLOR_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewMaskUNSCPauseColor.png";
    private static String PREVIEW_MASK_UNSC_CORPSE_COLOR_PATH = "src\\com\\xephorium\\armory\\ui\\resource\\image\\previewMaskUNSCCorpseColor.png";


    /*--- Public Image Retrieval Methods ---*/

    public static List<Image> ICON_APPLICATION_MAIN_LIST = generateIconApplicationMainList();

    public static ImageIcon ICON_UNSC = new ImageIcon(USE_DETAILED_ICONS ? ICON_UNSC_DETAILED_PATH : ICON_UNSC_SIMPLE_PATH, "UNSC Faction");
    public static ImageIcon ICON_COVENANT = new ImageIcon(USE_DETAILED_ICONS ? ICON_COVENANT_DETAILED_PATH : ICON_COVENANT_SIMPLE_PATH, "Covenant Faction");
    public static ImageIcon ICON_INSTALLATION_FOUND = new ImageIcon(ICON_INSTALLATION_FOUND_STYLIZED_PATH, "Installation Found");
    public static ImageIcon ICON_INSTALLATION_FOUND_DIALOG = new ImageIcon(ICON_INSTALLATION_FOUND_DIALOG_PATH, "Installation Found");
    public static ImageIcon ICON_INSTALLATION_INVALID = new ImageIcon(ICON_INSTALLATION_INVALID_STYLIZED_PATH, "Installation Invalid");
    public static ImageIcon ICON_INSTALLATION_INVALID_DIALOG = new ImageIcon(ICON_INSTALLATION_INVALID_DIALOG_PATH, "Installation Invalid");
    public static ImageIcon ICON_INSTALLATION_UNKNOWN = new ImageIcon(ICON_INSTALLATION_UNKNOWN_STYLIZED_PATH, "Installation Unknown");

    public static BufferedImage PREVIEW_BACKGROUND = readBufferedImage(PREVIEW_BACKGROUND_PATH);
    public static BufferedImage PREVIEW_MASK_WARTHOG_COLOR = readBufferedImage(PREVIEW_MASK_WARTHOG_COLOR_PATH);
    public static BufferedImage PREVIEW_MASK_WARTHOG_SHINE = readBufferedImage(PREVIEW_MASK_WARTHOG_SHINE_PATH);
    public static BufferedImage PREVIEW_MASK_SELECTOR_COLOR = readBufferedImage(PREVIEW_MASK_SELECTOR_COLOR_PATH);
    public static BufferedImage PREVIEW_MASK_UNSC_MINIMAP_COLOR = readBufferedImage(PREVIEW_MASK_UNSC_MINIMAP_COLOR_PATH);
    public static BufferedImage PREVIEW_MASK_UNSC_PAUSE_COLOR = readBufferedImage(PREVIEW_MASK_UNSC_PAUSE_COLOR_PATH);
    public static BufferedImage PREVIEW_MASK_UNSC_CORPSE_COLOR = readBufferedImage(PREVIEW_MASK_UNSC_CORPSE_COLOR_PATH);


    /*--- Private Utility Methods  ---*/

    private static List<Image> generateIconApplicationMainList() {
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

    private static BufferedImage readBufferedImage(String path) {
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(new File(path));
        } catch(IOException exception) {
            bufferedImage = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        }
        return bufferedImage;
    }
}
