package dataLayer;

import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileWriter {

    public FileWriter(Order order, ArrayList<MenuItem> items) {
        PrintWriter writer = null;
        try {
            String fileName = "Bill_" + order.getOrderId() + ".txt";
            writer = new PrintWriter(fileName, "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assert writer != null;
        writer.println("Order: " + order.getOrderId());
        writer.println("Table: " + order.getTable());
        for (MenuItem item : items) {
            writer.println(item.getName()+".........."+item.getPrice());
        }

        writer.println("Total price: " + order.getTotalPrice());
        writer.close();
    }
}
