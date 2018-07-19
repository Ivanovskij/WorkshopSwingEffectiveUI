package org.ivanovskiy.workshop.chapter3.eventqueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class UsingEventQueue extends JFrame {

    public UsingEventQueue() {
        super("UsingEventQueue");
        // выход при закрытии окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // кнопка и ее слушатель
        JButton button = new JButton("Генерировать событие");
        button.addActionListener(e -> getToolkit().getSystemEventQueue().postEvent(
                new WindowEvent(UsingEventQueue.this,
                        WindowEvent.WINDOW_CLOSING)
        ));
        // добавим кнопку в панель содержимого
        setLayout(new FlowLayout());
        add(button);
        // выведем окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsingEventQueue());
    }
}
