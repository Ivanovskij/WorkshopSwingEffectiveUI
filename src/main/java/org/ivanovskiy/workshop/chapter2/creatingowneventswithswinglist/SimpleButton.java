package org.ivanovskiy.workshop.chapter2.creatingowneventswithswinglist;

import org.ivanovskiy.workshop.chapter2.creatingownevents.ButtonPressEvent;
import org.ivanovskiy.workshop.chapter2.creatingownevents.ButtonPressListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class SimpleButton extends JComponent {

    private ButtonPressEvent buttonPressEvent = new ButtonPressEvent(this);

    public SimpleButton() {
        addMouseListener(new PressListener());
        setPreferredSize(new Dimension(100, 100));
    }

    public void addButtonPressListener(ButtonPressListener l) {
        listenerList.add(ButtonPressListener.class, l);
    }

    public void removeButtonPressListener(ButtonPressListener l) {
        listenerList.remove(ButtonPressListener.class, l);
    }

    private void fireButtonPressed() {
        for (ButtonPressListener l : listenerList.getListeners(ButtonPressListener.class)) {
            l.buttonPressed(buttonPressEvent);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, getWidth(), getHeight());
        // рамка
        g.setColor(Color.black);
        g.draw3DRect(0, 0, getWidth(), getHeight(), true);
    }

    private class PressListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            fireButtonPressed();
        }
    }
}
