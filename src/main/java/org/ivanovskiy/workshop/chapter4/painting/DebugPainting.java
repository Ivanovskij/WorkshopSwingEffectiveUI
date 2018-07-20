package org.ivanovskiy.workshop.chapter4.painting;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ivanovskij Oleg on 20.07.2018
 */
public class DebugPainting extends JFrame {

    public DebugPainting() {
        super("DebugPainting");
        // выход при закрытии окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // добавляем рисующий компонент
        PaintingComponent pc = new PaintingComponent();
        add(pc);
        // включаем для него отладку графики
        RepaintManager.currentManager(pc).
                setDoubleBufferingEnabled(false);
        pc.setDebugGraphicsOptions(DebugGraphics.LOG_OPTION
                | DebugGraphics.FLASH_OPTION);
        DebugGraphics.setFlashTime(600);
        DebugGraphics.setFlashCount(3);
        // выводим окно на экран
        setSize(200, 200);
        setVisible(true);
    }

    // компонент, который что-то рисует
    class PaintingComponent extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.orange);
            g.fillRect(10, 10, 100, 100);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DebugPainting());
    }

}
