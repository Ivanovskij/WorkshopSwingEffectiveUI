package org.ivanovskiy.workshop.chapter3;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class MainThreadCompletedTheWorkButEventDispatcherWork {
    private static final Logger logger  = Logger.getLogger(MainThreadCompletedTheWorkButEventDispatcherWork.class.getName());
    public static void main(String[] args) {
        logger.info("main start work");
        new JFrame().pack();
        logger.info("main finish work, but program not be killed, because event dispatcher work");
    }
}
