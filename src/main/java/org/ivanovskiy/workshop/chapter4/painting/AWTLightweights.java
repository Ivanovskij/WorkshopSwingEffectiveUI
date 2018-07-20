package org.ivanovskiy.workshop.chapter4.painting;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class AWTLightweights extends Frame {

    public AWTLightweights() {
        super("AWTLightweights");
        // при закрытии окна приложение завершается
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        // добавляем пару легковесных компонентов
        LightweightRect rect1 =
                new LightweightRect(Color.BLUE, true);
        LightweightRect rect2 =
                new LightweightRect(Color.RED, true);
        LightweightRect transparentRect =
                new LightweightRect(Color.BLACK, false);
        // укажем координаты вручную, чтобы компоненты
        // перекрывались
        setLayout(null);
        rect1.setBounds(40, 40, 100, 100);
        rect2.setBounds(50, 50, 100, 100);
        transparentRect.setBounds(35, 35, 150, 150);
        add(transparentRect);
        add(rect1);
        add(rect2);
        // последним добавляем тяжеловесный компонент
        Button button = new Button("Тяжелая!");
        button.setBounds(50, 170, 80, 30);
        add(button);
        // выводим окно на экран
        setSize(250, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AWTLightweights();
    }

    private class LightweightRect extends Component {

        private Color color;
        private boolean fill;

        // параметы — цвет и нужно ли зарисовывать всю область
        public LightweightRect(Color color, boolean fill) {
            this.color = color;
            this.fill = fill;
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(color);
            if (fill) {
                g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);
            } else {
                g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
            }
        }
    }
}
