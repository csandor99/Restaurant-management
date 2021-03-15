package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WaiterGraphicalUserInterface {
    public JFrame frame;
    JButton btnList = new JButton("VIEW ORDERS");
    JButton btnAdd = new JButton("ADD ORDER");
    JButton btnBill = new JButton("GENERATE BILL");
    JButton btnBack = new JButton("BACK");
    JTextField id = new JTextField(10);

    public WaiterGraphicalUserInterface(){
        frame = new JFrame("WAITER");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setLayout(new GridLayout(3,1));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();


        panel1.add(btnList);
        panel1.add(btnAdd);
        panel1.add(btnBill);
        panel2.add(new JLabel("Order number: "));
        panel2.add(id);
        panel3.add(btnBack);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
    }

    public void addActionListenerWaiterBack(ActionListener al) {
        btnBack.addActionListener(al);
    }

    public void addActionListenerWaiterView(ActionListener al) { btnList.addActionListener(al); }

    public void addActionListenerWaiterAdd(ActionListener al) { btnAdd.addActionListener(al); }

    public void addActionListenerWaiterBill(ActionListener al) { btnBill.addActionListener(al); }

    public String getId() {return id.getText();}

    public JFrame getFrame() {
        return frame;
    }

}
