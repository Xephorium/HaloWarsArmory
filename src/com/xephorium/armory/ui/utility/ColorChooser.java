package com.xephorium.armory.ui.utility;

import com.xephorium.armory.ui.resource.color.ArmoryColor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.colorchooser.AbstractColorChooserPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ColorChooser extends JDialog {


    /*--- Variables ---*/

    private static final int WINDOW_HEIGHT = 255;
    private static final int WINDOW_WIDTH = 460;

    private ColorChooserListener listener;
    private JPanel previewPanel;
    private JLabel redLabel;
    private JLabel greenLabel;
    private JLabel blueLabel;


    /*--- Constructor ---*/

    public ColorChooser(ColorChooserListener listener) {
        this.listener = listener;

        initializeDialogAttributes();
        this.add(createCustomColorChooserPanel(Color.WHITE));
    }


    /*--- Public Methods ---*/

    public void showDialog() {
        this.add(createCustomColorChooserPanel(Color.WHITE));
        this.show();
    }

    public void showDialog(Color color) {
        this.add(createCustomColorChooserPanel(color));
        this.show();
    }


    /*--- Private Methods ---*/

    private void initializeDialogAttributes() {

        this.setLayout(new BorderLayout());
        this.setTitle(" Choose A Color");
        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLocation(DisplayUtility.getWindowStartX(WINDOW_WIDTH), DisplayUtility.getWindowStartY(WINDOW_HEIGHT));
        this.addWindowListener(createCloseListener());
    }

    private WindowAdapter createCloseListener() {
        return new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                listener.onDialogClose();
            }
        };
    }

    private JPanel createCustomColorChooserPanel(Color color) {

        JColorChooser javaColorChooser = new JColorChooser(color);
        JPanel javaColorChooserPanel = new JPanel();

        javaColorChooser.getSelectionModel().getSelectedColor();
        javaColorChooser.getSelectionModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                redLabel.setText("Red: " + javaColorChooser.getSelectionModel().getSelectedColor().getRed() + ", ");
                greenLabel.setText("Green: " + javaColorChooser.getSelectionModel().getSelectedColor().getGreen() + ", ");
                blueLabel.setText("Blue: " + javaColorChooser.getSelectionModel().getSelectedColor().getBlue());
                previewPanel.setBackground(javaColorChooser.getSelectionModel().getSelectedColor());
            }
        });
        AbstractColorChooserPanel[] panels = javaColorChooser.getChooserPanels();
        for (AbstractColorChooserPanel javaPanel : panels) {
            if (javaPanel.getDisplayName().equals("HSV")) {
                javaColorChooserPanel = javaPanel;
                javaColorChooserPanel.setBorder(new EmptyBorder(15,15,0,15));
            }
        }

        JPanel selectPanel = new JPanel();
        selectPanel.setLayout(new BoxLayout(selectPanel,BoxLayout.X_AXIS));
        previewPanel = new JPanel();
        previewPanel.setBorder(BorderFactory.createLineBorder(ArmoryColor.WINDOW_BORDER_COLOR_DARK));
        previewPanel.setBackground(color);
        JButton selectButton = new JButton("Select");
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.onColorSelected(javaColorChooser.getSelectionModel().getSelectedColor());
                dispose();
            }
        });
        previewPanel.setPreferredSize(new Dimension(143, selectButton.getHeight()));
        selectPanel.add(previewPanel);
        Dimension dim = new Dimension(10,5);
        selectPanel.add(new Box.Filler(dim, dim, dim));
        selectPanel.add(selectButton);

        JPanel redGreenBlueVerticalPanel = new JPanel(new BorderLayout());
        JPanel redGreenBluePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        redLabel = new JLabel("Red: " + javaColorChooser.getSelectionModel().getSelectedColor().getRed() + ", ");
        greenLabel = new JLabel("Green: " + javaColorChooser.getSelectionModel().getSelectedColor().getGreen() + ", ");
        blueLabel = new JLabel("Blue: " + javaColorChooser.getSelectionModel().getSelectedColor().getBlue());
        redGreenBluePanel.add(redLabel);
        redGreenBluePanel.add(greenLabel);
        redGreenBluePanel.add(blueLabel);
        redGreenBlueVerticalPanel.add(redGreenBluePanel, BorderLayout.SOUTH);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.X_AXIS));
        bottomPanel.setBorder(new EmptyBorder(0, 22, 25, 22));
        bottomPanel.add(redGreenBlueVerticalPanel);
        bottomPanel.add(Box.createHorizontalGlue());
        bottomPanel.add(selectPanel);

        JPanel customColorChooserPanel = new JPanel(new BorderLayout());
        customColorChooserPanel.add(javaColorChooserPanel, BorderLayout.CENTER);
        customColorChooserPanel.add(bottomPanel, BorderLayout.PAGE_END);

        return customColorChooserPanel;
    }


    /*--- Listener Interface ---*/

    public interface ColorChooserListener {

        void onColorSelected(Color color);

        void onDialogClose();
    }
}
