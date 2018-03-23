package com.xephorium.armory.java;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        /*--- Variable Declarations ---*/

        final int IMAGE_HEIGHT = 300;
        final int IMAGE_WIDTH = 400;
        final int IMAGE_POSITION_HEIGHT = (Toolkit.getDefaultToolkit().getScreenSize().height/2) - (IMAGE_HEIGHT/2) - 100;
        final int IMAGE_POSITION_WIDTH = (Toolkit.getDefaultToolkit().getScreenSize().width/2) - (IMAGE_WIDTH/2);

        final String IMAGE_BACKGROUND_PATH = "C:\\Users\\Xephorium\\Documents\\Home\\Projects\\Programming\\Halo Wars Armory\\Test Projects\\src\\com\\xephorium\\layeredimagepreview\\res\\layered_image_background.png";
        final String WINDOW_TITLE = "  Layered Image Preview";

        JFrame frame = new JFrame(WINDOW_TITLE);
        JPanel imagePanel = new JPanel();
        BufferedImage backgroundImage;


        /*--- Window Setup ---*/

        frame.setSize(IMAGE_WIDTH,IMAGE_HEIGHT);
        frame.setLocation(IMAGE_POSITION_WIDTH, IMAGE_POSITION_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        try {
            backgroundImage = ImageIO.read(new File(IMAGE_BACKGROUND_PATH));
            JLabel backgroundLabel = new JLabel(new ImageIcon(backgroundImage));
            imagePanel.add(backgroundLabel);
            frame.add(imagePanel, BorderLayout.CENTER);
        } catch (IOException exception) {
            JLabel textLabel = new JLabel("Couldn't Load Image");
            frame.add(textLabel);
        }

        /*--- Display Window ---*/

        frame.setVisible(true);
    }
}