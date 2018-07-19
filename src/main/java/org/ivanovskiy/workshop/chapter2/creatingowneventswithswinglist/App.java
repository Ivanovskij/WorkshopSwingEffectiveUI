package org.ivanovskiy.workshop.chapter2.creatingowneventswithswinglist;

import org.ivanovskiy.workshop.chapter2.creatingownevents.ButtonPressEvent;
import org.ivanovskiy.workshop.chapter2.creatingownevents.ButtonPressListener;

import javax.swing.*;
import java.util.logging.Logger;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class App extends JFrame {

    private static final Logger logger  = Logger.getLogger(App.class.getName());

    public App() {
        super("SimpleButton Test with event listener list");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // создаем кнопку и присоединим слушателей
        SimpleButton button = new SimpleButton();
        button.addButtonPressListener(e -> logger.info("it is worked, anonymous class!!!"));
        button.addButtonPressListener(new InnerButtonListener());
        JPanel contents = new JPanel();
        contents.add(button);
        setContentPane(contents);
        // выведем окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    private class InnerButtonListener implements ButtonPressListener {
        @Override
        public void buttonPressed(ButtonPressEvent e) {
            logger.info("it is worked, inner class!!!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App());
    }
}
