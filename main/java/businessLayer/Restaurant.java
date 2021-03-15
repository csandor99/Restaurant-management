package businessLayer;

import dataLayer.FileWriter;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class Restaurant extends Observable implements IRestaurantProcessing, Serializable {
    HashMap<Order, ArrayList<MenuItem>> restaurantOrders = new HashMap<Order, ArrayList<MenuItem>>();
    ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
    ArrayList<Order> orders = new ArrayList<Order>();

    public Restaurant(ArrayList<MenuItem> menu){
        this.menu = menu;
    }

    public void createMenuItem(MenuItem item){
        assert item != null;
        int size = menu.size();

        menu.add(item);

        int newSize = menu.size();
        assert newSize == size + 1;

    }

        public void deleteMenuItem(String name){
            assert name != null;
            int size = menu.size();
            assert size > 0;

            MenuItem itemToDelete = null;
            ArrayList<MenuItem> itemListToDelete = new ArrayList<MenuItem>();
            for(MenuItem i: menu){
                if(i.getName().equals(name)) itemToDelete = i;
            }

            menu.remove(itemToDelete);

            for(MenuItem i: menu){
                if(i instanceof CompositeProduct){
                    int delete = 0;
                    ArrayList<MenuItem> compProdList = ((CompositeProduct) i).getItemsList();

                    for (MenuItem j: compProdList){
                        if(j.getName().equals(name)) delete = 1;
                    }

                    if(delete == 1) itemListToDelete.add(i);
                }
            }

            for (MenuItem it: itemListToDelete){
                menu.remove(it);
            }

            itemListToDelete.clear();

            int newSize = menu.size();
            assert newSize < size;

        }

    public void editMenuItem(String name, double price){
        assert name != null;

        for(MenuItem i: menu){
            if(i.getName().equals(name)) i.setPrice(price);
        }

        for(MenuItem i: menu){
            if(i instanceof CompositeProduct){
                ArrayList<MenuItem> compProdList = ((CompositeProduct) i).getItemsList();
                for (MenuItem j: compProdList) {
                    if (j.getName().equals(name)) i.setPrice(i.computePrice());
                }
            }
        }

    }

    public void createNewOrder(Order order, ArrayList<MenuItem> itemList){
        assert order != null;
        assert itemList != null;
        int size = restaurantOrders.size();
        restaurantOrders.put(order,itemList);
        order.setTotalPrice(computePriceOrder(order.getOrderId()));
        int newSize = restaurantOrders.size();
        assert newSize == size + 1;
    }

    public double computePriceOrder(int orderId){
        assert orderId != 0;
        Order order = new Order(0,0);
        float price = 0;

        for (Order key : restaurantOrders.keySet()) {
            if (key.getOrderId() == orderId)
                order = key;
        }

        ArrayList<MenuItem> orderedItems = restaurantOrders.get(order);

        for (MenuItem item : orderedItems) {
            price += item.getPrice();
        }
        order.setTotalPrice(price);
        assert price != 0;

        return price;

    }

    public void generateBill(int orderId) throws FileNotFoundException, UnsupportedEncodingException {
        assert orderId != 0;
        Order order = new Order(0,0);
        for (Order key : restaurantOrders.keySet()) {
            if(key.getOrderId() == orderId){
                order = key;
            }
        }
        ArrayList<MenuItem> orderedItems = restaurantOrders.get(order);
        FileWriter newFile = new FileWriter(order, orderedItems);
        restaurantOrders.remove(order);
    }

    public void notify(Order order){

        StringBuilder sb = new StringBuilder();

        sb.append("-New order-"+"\n\n");
        sb.append("id: ").append(order.getOrderId()).append("\n");
        sb.append("date: ").append(order.getDate()).append("\n");
        sb.append("table nr.: ").append(order.getTable()).append("\n");
        sb.append("products: ").append("\n");
        HashMap<Order, ArrayList<MenuItem>> map = restaurantOrders;
        ArrayList<MenuItem> list = map.get(order);
        for(MenuItem item: list){
            sb.append(item.toString());
        }
        setChanged();
        notifyObservers(sb.toString());
    }

    public ArrayList<MenuItem> getMenu() {
        return menu;
    }

    public HashMap<Order, ArrayList<MenuItem>> getRestaurantOrders() {
        return restaurantOrders;
    }

    public void setMenu(ArrayList<MenuItem> menu) {
        this.menu = menu;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

}
