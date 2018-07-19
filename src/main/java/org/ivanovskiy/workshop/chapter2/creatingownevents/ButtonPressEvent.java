package org.ivanovskiy.workshop.chapter2.creatingownevents;

import java.util.EventObject;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class ButtonPressEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ButtonPressEvent(Object source) {
        super(source);
    }
}
