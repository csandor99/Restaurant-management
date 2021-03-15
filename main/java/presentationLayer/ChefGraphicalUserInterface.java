package presentationLayer;

import businessLayer.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ChefGraphicalUserInterface implements Observer {
    JFrame frame;
    Restaurant restaurant;
    public ChefGraphicalUserInterface(Restaurant restaurant){
        this.restaurant = restaurant;
        frame = new JFrame("CHEF");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(250, 250);
        restaurant.addObserver(this);
        JPanel panel = new JPanel();
        panel.add(new JLabel("CHEF NOTIFIER"));
        frame.add(panel);
    }


    @Override
    public void update(Observable o, Object arg) {
        frame.setVisible(true);
        JOptionPane.showConfirmDialog(null, arg, "Notification!", 2);
        frame.setVisible(false);

    }
}
