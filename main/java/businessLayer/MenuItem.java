package businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
    private String name;
    private double price;

    public MenuItem() {

    }

    public MenuItem(String name,double price) {
        this.name = name;
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price)
    {
        this.price = price;

    }

    public double getPrice(){
        return price;
    }

    public abstract double computePrice();

}
