package com.xephorium.armory.ui.utility;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomMouseListener implements MouseListener {


    /*--- Variables ---*/

    private MouseClickListener listener;


    /*--- Constructor ---*/

    public CustomMouseListener(MouseClickListener listener) {
        this.listener = listener;
    }


    /*--- Override Methods ---*/

    @Override
    public void mouseClicked(MouseEvent e) {
        listener.onMouseClick();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Do Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Do Nothing
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // Do Nothing
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // Do Nothing
    }


    /*--- Listener Interface ---*/

    public interface MouseClickListener {

        void onMouseClick();
    }
}
