package org.ivanovskiy.workshop.chapter3;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class StartingEventThread {
    private static final Logger logger  = Logger.getLogger(StartingEventThread.class.getName());

    private StartingEventThread() {
    }

    /**
     *  Не следует создавать или заранее подготавливать компоненты Swing в отдельном
         потоке, отличном от потока рассылки событий. Реализация компонентов
         Swing подразумевает любые манипуляции с ними из потока рассылки
         событий, даже если компоненты еще не добавлены на экран.
     */
    public static void main(String[] args) {
        Toolkit.getDefaultToolkit()
                .getSystemEventQueue()
                .push(new CustomQueue());
        JFrame frame = new JFrame("Тест");
        logger.info("(1) JFrame()");
        // добавляем флажок
        JCheckBox checkBox = new JCheckBox("Тест");
        frame.add(checkBox, "South");
        logger.info("(2) Добавлен флажок");
        // создаем список
        DefaultListModel model = new DefaultListModel();
        JList list = new JList(model);
        frame.add(list);
        logger.info("(3) Добавлен список");
        // обновляем модель
        model.addElement("Тест");
        logger.info("(4) Обновление модели");
        // окончательно выводим интерфейс на экран
        frame.setVisible(true);
        logger.info("(5) Интерфейс построен");
    }

    // специальная очередь событий, сообщающая
    // отладочную информацию о событиях и потоках
    private static class CustomQueue extends EventQueue {

        // метод кладет событие в очередь
        @Override
        public void postEvent(AWTEvent event) {
            logger.info("post(), поток: " + Thread.currentThread().toString());
            logger.info("post(), событие: " + event);
           super.postEvent(event);
        }

        // метод распределяет событие по компонентам
        @Override
        protected void dispatchEvent(AWTEvent event) {
            logger.info("dispatch(), поток: " + Thread.currentThread().toString());
            logger.info("dispatch(), событие: " + event);
            super.dispatchEvent(event);
        }
    }
}
