package org.ivanovskiy.workshop.chapter3.deliveringevents;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class PreProcessMouse extends JFrame {

    private static final Logger logger  = Logger.getLogger(PreProcessMouse.class.getName());


    public PreProcessMouse() {
        super("PreProcessMouse");
        // при закрытии окна - выход
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // добавим слушателя событий от мыши
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logger.info("Click count: " + e.getClickCount());
            }
        });
        // выводим окно на экран
        setSize(200, 200);
        setVisible(true);
    }

    @Override
    public void processMouseEvent(MouseEvent e) {
        if (e.getClickCount() == 1) {
            // один щелчок не пропускаем к слушателям
            return;
        } else {
            super.processMouseEvent(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PreProcessMouse());
    }
}
