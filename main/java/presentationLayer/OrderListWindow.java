package presentationLayer;

import businessLayer.MenuItem;
import businessLayer.Order;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class OrderListWindow {
    JFrame frame = new JFrame();

    public OrderListWindow(HashMap<Order, ArrayList<MenuItem>> list){
        frame = new JFrame("ORDER LIST");
        frame.setLayout(new FlowLayout());
        frame.setSize(500, 500);
        String[] columns = {"Order ID", "Date", "Table Nr."};

        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.setColumnIdentifiers(columns);

        for (Order key : list.keySet()) {
            int id = key.getOrderId();
            Date date = key.getDate();
            int tableNr = key.getTable();

            Object[] data = {id, date, tableNr};

            tableModel.addRow(data);
        }


        JScrollPane panel = new JScrollPane(table);
        frame.add(panel);
    }
}
