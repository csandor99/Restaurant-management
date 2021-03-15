package presentationLayer;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Restaurant;
import dataLayer.RestaurantSerialization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller {
    private MainWindow mainMenu;
    private AdministratorGraphicalUserInterface adminGUI;
    private WaiterGraphicalUserInterface waiterGUI;
    private MenuWindow menuGUI;
    private OrderListWindow orderListGUI;
    private Restaurant restaurant;
    private CompProdWindow compProdGUI;
    private NewOrderWindow newOrderGUI;
    private String compProdName;
    private RestaurantSerialization serialization;
    private ChefGraphicalUserInterface chefGUI;

    public Controller(String file){
        this.restaurant = RestaurantSerialization.deserialize(file);
        this.mainMenu = new MainWindow(restaurant);
        this.adminGUI = new AdministratorGraphicalUserInterface();
        this.waiterGUI = new WaiterGraphicalUserInterface();
        this.menuGUI = new MenuWindow(restaurant.getMenu());
        this.orderListGUI = new OrderListWindow(restaurant.getRestaurantOrders());
        this.serialization = new RestaurantSerialization();
        this.compProdGUI = new CompProdWindow(restaurant.getMenu(),null,restaurant,serialization);
        this.newOrderGUI = new NewOrderWindow(restaurant.getMenu(),restaurant,serialization);
        this.chefGUI = new ChefGraphicalUserInterface(restaurant);


        mainMenu.getFrame().setVisible(true);
        mainMenu.addActionListenerAdministrator(new ActionListenerAdmin());
        mainMenu.addActionListenerWaiter(new ActionListenerWaiter());
        adminGUI.addActionListenerAdminBack(new ActionListenerAdminBack());
        adminGUI.addActionListenerAdminView(new ActionListenerAdminList());
        waiterGUI.addActionListenerWaiterView(new ActionListenerWaiterView());
        adminGUI.addActionListenerAdminAdd(new ActionListenerAdminAdd());
        adminGUI.addActionListenerAdminEdit(new ActionListenerAdminEdit());
        adminGUI.addActionListenerAdminDelete(new ActionListenerAdminDelete());
        waiterGUI.addActionListenerWaiterBack(new ActionListenerWaiterBack());
        waiterGUI.addActionListenerWaiterAdd(new ActionListenerWaiterAdd());
        waiterGUI.addActionListenerWaiterBill(new ActionListenerWaiterBill());

    }

    public class ActionListenerAdmin implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mainMenu.frame.setVisible(false);
            adminGUI.frame.setVisible(true);
        }
    }

    private class ActionListenerAdminBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            adminGUI.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        }
    }

    private class ActionListenerAdminList implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            MenuWindow menu = new MenuWindow(restaurant.getMenu());
            menuGUI = menu;
            menuGUI.frame.setVisible(true);

        }
    }

    private class ActionListenerWaiterView implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            OrderListWindow orders = new OrderListWindow(restaurant.getRestaurantOrders());
            orderListGUI = orders;
            orderListGUI.frame.setVisible(true);
        }
    }

    private class ActionListenerAdminAdd implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String name = adminGUI.getName();
            if(!name.equals("")){
                if(!adminGUI.getCheckbox().isSelected()) {
                    try {
                        double price = Double.parseDouble(adminGUI.getPrice());
                        BaseProduct item = new BaseProduct(name, price);
                        restaurant.createMenuItem(item);
                        serialization.serialize(restaurant);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(null, "Please insert a PRICE for the product!");
                    }
                }
                else {
                    compProdName = name;
                    compProdGUI = new CompProdWindow(restaurant.getMenu(),name,restaurant,serialization);
                    compProdGUI.getFrame().setVisible(true);

                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Please insert a NAME for the product!");
            }
        }
    }


    private class ActionListenerAdminEdit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            String name = adminGUI.getName();
            if(!name.equals("")) {
                try {
                    double price = Double.parseDouble(adminGUI.getPrice());
                    restaurant.editMenuItem(name, price);
                    serialization.serialize(restaurant);

                }catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please insert a VALID PRICE to edit the product!");
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "Please insert a NAME to edit the product!");
            }
        }
    }


    private class ActionListenerAdminDelete implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = adminGUI.getName();
            if(!name.equals("")) {
                restaurant.deleteMenuItem(name);
                serialization.serialize(restaurant);
            }
            else {
                JOptionPane.showMessageDialog(null, "Please insert a NAME to delete the product!");
            }
        }
    }

    public class ActionListenerWaiter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            mainMenu.frame.setVisible(false);
            waiterGUI.frame.setVisible(true);
        }
    }

    private class ActionListenerWaiterBack implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            waiterGUI.getFrame().setVisible(false);
            mainMenu.getFrame().setVisible(true);
        }
    }


    private class ActionListenerWaiterAdd implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            newOrderGUI = new NewOrderWindow(restaurant.getMenu(),restaurant,serialization);
            newOrderGUI.frame.setVisible(true);
        }
    }

    private class ActionListenerWaiterBill implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int id = Integer.parseInt(waiterGUI.getId());
                restaurant.generateBill(id);
                JOptionPane.showMessageDialog(null, "Bill generated!");
            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Please insert an ORDER ID to generate a bill!");
            }
        }
    }
}
