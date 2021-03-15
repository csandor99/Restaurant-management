package presentationLayer;

import businessLayer.MenuItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class MenuWindow {
    JFrame frame = new JFrame();

    public MenuWindow(ArrayList<MenuItem> menu){
        frame = new JFrame("MENU");
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);
        String[] columns = {"Product name", "Price"};

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.setColumnIdentifiers(columns);

        for (MenuItem i: menu){
            String name = i.getName();
            double price = i.getPrice();

            Object[] data = {name, price};

            tableModel.addRow(data);
        }

        JScrollPane panel = new JScrollPane(table);
        frame.add(panel);
    }

}
