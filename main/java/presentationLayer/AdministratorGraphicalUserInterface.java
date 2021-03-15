package presentationLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdministratorGraphicalUserInterface {
    public JFrame frame;
    JButton btnList = new JButton("VIEW MENU");
    JButton btnAdd = new JButton("ADD");
    JButton btnDelete = new JButton("DELETE");
    JButton btnEdit = new JButton("EDIT");
    JButton btnBack = new JButton("BACK");
    JCheckBox checkbox = new JCheckBox("COMPOSITE PRODUCT");
    JTextField name = new JTextField(20);
    JTextField price = new JTextField(20);

    public AdministratorGraphicalUserInterface(){
        frame = new JFrame("ADMINISTRATOR");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(500, 250);
        frame.setLayout(new GridLayout(5,1));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        panel1.add(new JLabel("Product name: "));
        panel1.add(name);
        panel2.add(new JLabel("Price: "));
        panel2.add(price);
        panel3.add(checkbox);
        panel4.add(btnList);
        panel4.add(btnAdd);
        panel4.add(btnEdit);
        panel4.add(btnDelete);
        panel5.add(btnBack);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);

    }

    public void addActionListenerAdminBack(ActionListener al) {
        btnBack.addActionListener(al);
    }

    public void addActionListenerAdminView(ActionListener al) { btnList.addActionListener(al); }

    public void addActionListenerAdminAdd(ActionListener al) { btnAdd.addActionListener(al); }

    public void addActionListenerAdminEdit(ActionListener al) { btnEdit.addActionListener(al); }

    public void addActionListenerAdminDelete(ActionListener al) { btnDelete.addActionListener(al); }

    public JCheckBox getCheckbox() {return checkbox; }

    public String getName() {return name.getText();}

    public String getPrice() {return price.getText();}

    public JFrame getFrame() {
        return frame;
    }
}
