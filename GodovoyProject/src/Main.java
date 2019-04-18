import javax.swing.*;

import java.awt.*;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        MyPanel panel = new MyPanel();
        JLabel label = new JLabel();
        JFrame frame = new JFrame();
        frame.setSize(1800,1030);
        frame.setTitle("Target shooting");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.add(panel);
        frame.add(label, BorderLayout.SOUTH);
        frame.add(label, BorderLayout.SOUTH);
        frame.setVisible(true);
        panel.addMouseListener(panel.world.mouseListener);
        panel.addMouseMotionListener(panel.world.mouseListener);

        while (true) {
         frame.repaint();
         label.setText("                                         Выстрелов: " + panel.world.shoots + "  Попаданий:" + panel.world.hit + "  Точность:" + panel.world.accuracy +"%");
         Thread.sleep(10);
        }
    }
}
