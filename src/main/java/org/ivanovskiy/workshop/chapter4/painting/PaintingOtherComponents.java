package org.ivanovskiy.workshop.chapter4.painting;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ivanovskij Oleg on 20.07.2018
 */
public class PaintingOtherComponents extends JFrame {

    public PaintingOtherComponents() {
        super("PaintingOtherComponents");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        add(new CustomPaintComponent());
        setSize(400, 300);
        setVisible(true);
    }


    private class CustomPaintComponent extends JPanel {
        // кнопка для прорисовки
        private JButton button = new JButton("Привет!");
        // метод для рисования в Swing
        @Override
        protected void paintComponent(Graphics g) {
            // необходимо вызвать для обработки свойства opaque
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            button.setSize(80, 30);
            // отключение двойной буферизации — не всегда нужно
//            button.setDoubleBuffered(false);
            // переместим позицию рисования
            g2.translate(100, 100);
            for (int i = 1; i <= 8; i++) {
                g2.rotate(2 * Math.PI / i);
                button.paint(g);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PaintingOtherComponents());
    }
}
