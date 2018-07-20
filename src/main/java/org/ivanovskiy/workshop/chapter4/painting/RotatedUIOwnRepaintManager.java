package org.ivanovskiy.workshop.chapter4.painting;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Ivanovskij Oleg on 20.07.2018
 */
public class RotatedUIOwnRepaintManager extends JFrame {

    public RotatedUIOwnRepaintManager() {
        super("RotatedUI");
        // выход при закрытии окна
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // добавляем особую панель
        RotatingPanel rp = new RotatingPanel();
        add(rp);
        // добавляем в панель компоненты
        rp.add(new JButton("Привет!"));
        rp.add(new JTextField(20));
        // устанавливаем свой RepaintManager
        RepaintManager.setCurrentManager(
                new RotatingRepaintManager());
        // выводим окно на экран
        setSize(200, 300);
        setVisible(true);
    }

    private class RotatingPanel extends JPanel{
        @Override
        protected void paintChildren(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.translate(50, 200);
            // поворот на 45 градусов
            g2.rotate(-Math.PI/4);
            // небольшое растяжение
            g2.shear(-0.1, -0.1);
            // обычное рисование предков
            super.paintChildren(g);
        }
    }

    /*
    *   Замена стандартного объекта RepaintManager
        на собственный позволяет нам перехватывать желания кнопки и поля. Наш объект унаследован
        от стандартного и переопределяет метод addDirtyRegion(), который вызывается
        при любом запросе любого компонента Swing на перерисовку. Мы смотрим, не находится
        ли компонент в нашей «крутящей» панели, и, если да, просто полностью перерисовываем
        ее, а если нет, позволяем рисовать оригинальному «просителю». Производительность
        перерисовки при таком грубом подходе конечно упадет, но это просто пример.
    * */
    private class RotatingRepaintManager extends RepaintManager {
        // все запросы на перерисовку попадают сюда
        @Override
        public void addDirtyRegion(JComponent c, int x, int y, int w, int h) {
            Container parent = c;
            while(!(parent instanceof RotatingPanel)) {
                parent = parent.getParent();
                if(parent == null) {
                    // мы не нашли нашего предка, сброс
                    parent = c;
                    break;
                }
            }
            // перерисовываем весь компонент полностью
            super.addDirtyRegion((JComponent) parent,
                    0, 0, parent.getWidth(), parent.getHeight());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RotatedUIOwnRepaintManager());
    }
}
