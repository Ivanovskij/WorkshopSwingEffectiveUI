package org.ivanovskiy.workshop.chapter3.eventdispatchthread;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class InvokeLater extends JFrame {

    private static final Logger logger  = Logger.getLogger(InvokeLater.class.getName());

    private JButton button;

    public InvokeLater() {
        super("InvokeLater");
        // при закрытии окна - выход
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // добавим кнопку со слушателем
        button = new JButton("Выполнить сложную работу");
        button.addActionListener(e -> {
            // запустим отдельный поток
            /*
            *  (на самом деле, выполнение долгих вычислений в слушателе, который был
                вызван потоком рассылки событий, приведет к блокированию остальных событий,
                ждущих в очереди рассылки тем же потоком,
                и в итоге интерфейс программы станет неотзывчивым).
            * */
            new ComplexJobThread().start();
            button.setText("Подождите...");
        });
        // настроим панель содержимого и выведем окно на экран
        setLayout(new FlowLayout());
        add(new JTextField(20));
        add(button);
        setSize(300, 200);
        setVisible(true);
    }


    private class ComplexJobThread extends Thread {
        @Override
        public void run() {
            try {
                // изобразим задержку
                sleep(3000);
                // работа закончена, нужно изменить интерфейс
                /*
                *   Менять надпись из потока, отличного от потока рассылки событий, не стоит: это может
                    привести к неопределенности данных в компонентах и ошибкам, и даже к тупикам
                    и взаимным блокировкам потоков (мы уже знаем, что компоненты Swing не обладают
                    встроенной синхронизацией).
                * */
                EventQueue.invokeLater(() -> button.setText("Работа завершена"));
            } catch (Exception ex) {
                logger.severe("Exception: " + ex.getMessage());
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InvokeLater());
    }
}