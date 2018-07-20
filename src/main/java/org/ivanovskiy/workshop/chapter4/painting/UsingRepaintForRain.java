package org.ivanovskiy.workshop.chapter4.painting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class UsingRepaintForRain extends JFrame {

    private static final Logger logger  = Logger.getLogger(UsingRepaintForRain.class.getName());

    public static void main(String [] args ) {
        SwingUtilities.invokeLater(() -> new UsingRepaintForRain());
    }

    public UsingRepaintForRain() {
        super("Rain app");
        setSize(450, 300);
        RainPanel rainPanel = new RainPanel();
        add(rainPanel);
        Thread threadRainPanel = new Thread(rainPanel);
        threadRainPanel.start();
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                rainPanel.setStopped(true);
                System.exit(0);
            }
        });
    }


    private class RainPanel extends JPanel implements Runnable {

        private static final int DELAY_FOR_REPAINT_MS = 16;
        private AtomicBoolean stopped = new AtomicBoolean(false);
        private static final double CHANCE_OF_RAIN = 0.5;
        private List<Rain> rainList = new ArrayList<>();

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = getGraphics2D(g);
            renderRain(g2);
            addNewRainInList();
        }

        private Graphics2D getGraphics2D(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(1.0f));
            g2.setColor(Color.BLUE);
            return g2;
        }

        private void renderRain(Graphics2D g2) {
            Iterator<Rain> iterator = rainList.iterator();
            while (iterator.hasNext()) {
                Rain rain = iterator.next();
                rain.render(g2);
                if (rain.y2 >= getHeight()) {
                    iterator.remove();
                }
            }
        }

        private void addNewRainInList() {
            if (Math.random() > CHANCE_OF_RAIN) {
                rainList.add(new Rain());
            }
        }

        @Override
        public void run() {
            while (!isStopped()) {
                repaint();
                try {
                    Thread.sleep(DELAY_FOR_REPAINT_MS);
                } catch (InterruptedException e) {
                    logger.severe("InterruptedException: " + e.getMessage());
                    Thread.currentThread().interrupt();
                    setStopped(true);
                }
            }

        }

        public boolean isStopped() {
            return stopped.get();
        }

        public void setStopped(boolean stopped) {
            this.stopped.set(stopped);
        }

        private class Rain {

            private int x1;
            private int y1;
            private int x2;
            private int y2;

            public Rain() {
                Random r = new Random();
                x2 = r.nextInt(getWidth());
                y2 = 0;
            }

            public void render(Graphics2D g2) {
                update();
                draw(g2);
            }

            private void update() {
                x1 = x2;
                y1 = y2;
                x2 += 1;
                y2 += 8;
            }

            private void draw(Graphics2D g2) {
                g2.draw(new Line2D.Double(x1, y1, x2, y2));
            }
        }
    }
}
