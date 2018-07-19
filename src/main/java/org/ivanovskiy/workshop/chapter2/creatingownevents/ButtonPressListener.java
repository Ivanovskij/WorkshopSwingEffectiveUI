package org.ivanovskiy.workshop.chapter2.creatingownevents;

import java.util.EventListener;

public interface ButtonPressListener extends EventListener {

    void buttonPressed(ButtonPressEvent e);

}
