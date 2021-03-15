package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerialization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CompProdWindow {
    JFrame frame = new JFrame();
    JComboBox<MenuItem> cb = new JComboBox<>();
    JButton btnOk = new JButton("OK");
    JButton btnAddProd = new JButton("Add");
    private ArrayList<MenuItem> compProdList = new ArrayList<>();
    public CompProdWindow(ArrayList<MenuItem> menuItems, String name, Restaurant restaurant, RestaurantSerialization serialization){

        frame.setSize(500, 300);
        frame.setLayout(new GridLayout(2,1));

        cb.removeAllItems();
        for (MenuItem item : menuItems){
            if(item instanceof BaseProduct) cb.addItem(item);
        }

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CompositeProduct item = new CompositeProduct(name,0);
                item.setItemsList(compProdList);
                item.setPrice(item.computePrice());
                restaurant.createMenuItem(item);
                serialization.serialize(restaurant);
                frame.setVisible(false);
            }
        });

        btnAddProd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuItem item = (MenuItem) getComboBox().getSelectedItem();
                if(item instanceof BaseProduct) {
                    compProdList.add((BaseProduct)item);
                    JOptionPane.showMessageDialog(null, item.getName()+" was added!");
                }
            }
        });

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        panel1.add(cb);
        panel2.add(btnAddProd);
        panel2.add(btnOk);
        frame.add(panel1);
        frame.add(panel2);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JComboBox getComboBox() {return cb;}

}
