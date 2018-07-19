package org.ivanovskiy.workshop.chapter3.deliveringevents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class ConsumingEvents extends JFrame {

    public ConsumingEvents() {
        super("ConsumingEvents");
        // при закрытии окна - выход
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // слушатель, поглощающий печатание символов
        KeyListener kl = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                e.consume();
            }
        };
        setLayout(new FlowLayout());
        JTextField swingField = new JTextField(10);
        swingField.addKeyListener(kl);
        add(swingField);
        TextField awtField = new TextField(10);
        add(awtField);
        awtField.addKeyListener(kl);
        // кнопка
        JButton button = new JButton("Жмите!");
        add(button);
        // слушатель пытается поглотить события от мыши
        // НО, несмотря
        // на наши попытки «съесть» событие, она все равно реагирует на мышь, а значит, получает
        // события через слушатели, и мы не можем так просто это ей запретить
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                e.consume();
            }
        });
        // выводим окно на экран
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ConsumingEvents());
    }

}
