package businessLayer;

import java.util.ArrayList;

public class CompositeProduct extends MenuItem{

    private ArrayList<MenuItem> itemsList;

    public CompositeProduct(String name, double price) {
        super.setName(name);
        super.setPrice(price);
        itemsList = new ArrayList<MenuItem>();
    }

    public ArrayList<MenuItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<MenuItem> itemsList) {
        this.itemsList = itemsList;
    }

    public void addItem(MenuItem item) {
        itemsList.add(item);
    }

    public void removeItem(MenuItem item) {
        itemsList.remove(item);
    }

    public double computePrice() {
        double p = 0;
        for (MenuItem menuItem : itemsList)
            p+= menuItem.computePrice();

        super.setPrice(p);
        return p;
    }

    public String toString(){
        return this.getName() +", "+this.getPrice();
    }

}
