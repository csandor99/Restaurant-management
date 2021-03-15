package presentationLayer;

import businessLayer.*;
import businessLayer.MenuItem;
import dataLayer.RestaurantSerialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class NewOrderWindow {
    JFrame frame;
    JComboBox<MenuItem> cb = new JComboBox<>();
    JButton btnOk = new JButton("OK");
    JButton btnAddOrd = new JButton("Add");
    JTextField tableNr = new JTextField(20);
    private ArrayList<MenuItem> ordList = new ArrayList<>();
    private int idCount = 0;

    public NewOrderWindow(ArrayList<MenuItem> menuItems, Restaurant restaurant, RestaurantSerialization serialization){
        frame = new JFrame("NEW ORDER");
        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(3,1));

        cb.removeAllItems();
        for (MenuItem item : menuItems){
            cb.addItem(item);
        }

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tnr = Integer.parseInt(tableNr.getText());
                    idCount = restaurant.getRestaurantOrders().size() + 1;

                    Order order = new Order(idCount, tnr);
                    restaurant.getOrders().add(order);
                    restaurant.createNewOrder(order, ordList);
                    restaurant.notify(order);
                    serialization.serialize(restaurant);
                    frame.setVisible(false);
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Please insert a valid TABLE NUMBER!");
                }
            }
        });

        btnAddOrd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem) getComboBox().getSelectedItem();
                ordList.add(item);
                JOptionPane.showMessageDialog(null, item.getName()+" was added!");

            }
        });

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        panel1.add(cb);
        panel2.add(new JLabel("Table nr.: "));
        panel2.add(tableNr);
        panel3.add(btnAddOrd);
        panel3.add(btnOk);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JComboBox getComboBox() {return cb;}
}
