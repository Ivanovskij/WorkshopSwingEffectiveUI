package org.ivanovskiy.workshop.chapter4.painting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class AWTPainting extends Frame {

    public AWTPainting() {
        super("AWTPainting");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setLayout(new FlowLayout());
        // попробуем закрасить часть кнопки
        for (int i = 0; i < 20; i++) {
            add(new Button("Перерисуем кнопку!") {
                @Override
                public void paint(Graphics g) {
                    g.setColor(Color.BLUE);
                    g.fillRect(2, 2, getWidth() - 5, getHeight() - 5);
                }
            });
        }
        setSize(200, 200);
    }

    @Override
    public void paint(Graphics g) {
        // заполняем все красным цветом
        g.setColor(Color.RED);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public static void main(String[] args) {
        new AWTPainting().setVisible(true);
    }

}
