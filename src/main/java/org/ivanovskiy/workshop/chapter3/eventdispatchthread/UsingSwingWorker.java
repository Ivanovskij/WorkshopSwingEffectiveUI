package org.ivanovskiy.workshop.chapter3.eventdispatchthread;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by Ivanovskij Oleg on 19.07.2018
 */
public class UsingSwingWorker extends JFrame {

    private JButton button;
    public UsingSwingWorker() {
        super("UsingSwingWorker");
        // при закрытии окна - выход
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // добавим кнопку со слушателем
        button = new JButton("Выполнить сложную работу");
        button.addActionListener(e -> {
            // запустим отдельную долгую работу
            new ComplexJob().execute();
            button.setText("Подождите...");
        });
        // настроим панель содержимого и выведем окно на экран
        setLayout(new FlowLayout());
        add(new JTextField(20));
        add(button);
        setSize(300, 200);
        setVisible(true);
    }

    private class ComplexJob extends SwingWorker<String,String> {
        // здесь выполняется работа, это отдельный поток!
        // тут нельзя работать с интерфейсом программы
        @Override
        public String doInBackground() throws Exception {
            Thread.sleep(2000);
            // публикуем промежуточные результаты
            publish("Половина работы закончена...");
            Thread.sleep(2000);
            return "";
        }

        // обработка промежуточных результатов
        // это поток рассылки событий!
        @Override
        protected void process(List<String> chunks) {
            button.setText(chunks.get(0));
        }

        @Override
        // окончание работы - и вновь это поток рассылки
        public void done() {
            button.setText("Работа завершена");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UsingSwingWorker());
    }
}
