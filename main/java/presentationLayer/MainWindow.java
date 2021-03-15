package presentationLayer;

import businessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainWindow{
    public JFrame frame;
    public JPanel panel;

    public JButton adminBtn;
    public JButton waiterBtn;

    public MainWindow(Restaurant restaurant) {

        frame = new JFrame("MAIN MENU");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 100);

        adminBtn = new JButton("ADMINISTRATOR");
        waiterBtn = new JButton("WAITER");

        panel = new JPanel();

        panel.add(adminBtn);
        panel.add(waiterBtn);
        panel.setAlignmentY(Component.CENTER_ALIGNMENT);
        frame.add(panel);
    }

    public void addActionListenerAdministrator(ActionListener al) {
        adminBtn.addActionListener(al);
    }

    public void addActionListenerWaiter(ActionListener al) {
        waiterBtn.addActionListener(al);
    }

    public JFrame getFrame() {
        return frame;
    }

}
