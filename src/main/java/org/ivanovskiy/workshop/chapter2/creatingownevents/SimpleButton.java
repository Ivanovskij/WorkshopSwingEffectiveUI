package org.ivanovskiy.workshop.chapter2.creatingownevents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class SimpleButton extends JComponent {

    // список слушателей
    private List<ButtonPressListener> listeners = new ArrayList<>();
    private ButtonPressEvent buttonPressEvent = new ButtonPressEvent(this);

    public SimpleButton() {
        addMouseListener(new PressListener());
        setPreferredSize(new Dimension(100, 100));
    }

    public void addButtonPressListener(ButtonPressListener l) {
        listeners.add(l);
    }

    public void removeButtonPressListener(ButtonPressListener l) {
        listeners.remove(l);
    }

    private void fireButtonPressed() {
        listeners.forEach(l->l.buttonPressed(buttonPressEvent));
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
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
