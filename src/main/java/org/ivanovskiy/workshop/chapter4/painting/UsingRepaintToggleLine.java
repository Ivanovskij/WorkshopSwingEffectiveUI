package org.ivanovskiy.workshop.chapter4.painting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Created by Ivanovskij Oleg on 20.07.2018
 */
public class UsingRepaintToggleLine extends JFrame {

    private static final Logger logger  = Logger.getLogger(UsingRepaintToggleLine.class.getName());

    public static void main(String [] args ) {
        SwingUtilities.invokeLater(() -> new UsingRepaintToggleLine());
    }

    public UsingRepaintToggleLine() {
        super("Toggle line");
        setSize(400, 500);
        setLocationRelativeTo(null);
        ToggleLine toggleLine = new ToggleLine(getWidth());
        add(toggleLine);
        setVisible(true);
        Thread thread = new Thread(toggleLine);
        thread.start();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                toggleLine.setStopped(true);
                System.exit(0);
            }
        });
    }

    private class ToggleLine extends JPanel implements Runnable {
        private volatile boolean stopped = false;
        private boolean borderBottom = false;
        private static final int DELAY_FOR_REPAINT_MS = 25;

        private int x1Pos;
        private int y1Pos;
        private int x2Pos;
        private int y2Pos;
        private int offsetPosition = 10;

        public ToggleLine(int widthFrame) {
            Random rnd = new Random();
            x1Pos = rnd.nextInt(widthFrame);
            y1Pos = 0;
            x2Pos = x1Pos;
            y2Pos = y1Pos;
        }

        private void updatePosition() {
            x1Pos = x2Pos;
            y1Pos = y2Pos;
            determinateAndSetBorderBottom();
            updatePositionBasedOnBorderBottom();
        }

        private void updatePositionBasedOnBorderBottom() {
            if (borderBottom) {
                updatePositionUp();
            } else {
                updatePositionDown();
            }
        }

        private void determinateAndSetBorderBottom() {
            if (y2Pos >= getHeight()) {
                borderBottom = true;
            } else if (y2Pos <= 0) {
                borderBottom = false;
            }
        }

        private void updatePositionDown() {
            y2Pos += offsetPosition;
        }

        private void updatePositionUp() {
            y2Pos -= offsetPosition;
        }

        private void draw(Graphics2D g2) {
            g2.draw(new Line2D.Double(x1Pos, y1Pos, x2Pos, y2Pos));
        }

        public void render(Graphics2D g2) {
            updatePosition();
            draw(g2);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = getGraphics2D(g);
            render(g2);
        }

        private Graphics2D getGraphics2D(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setStroke(new BasicStroke(2.0f));
            g2.setColor(Color.BLUE);
            return g2;
        }

        public boolean isStopped() {
            return stopped;
        }

        public void setStopped(boolean stopped) {
            this.stopped = stopped;
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
    }
}
