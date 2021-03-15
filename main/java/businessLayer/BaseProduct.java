package businessLayer;

public class BaseProduct extends MenuItem {

    public BaseProduct(String name,double price) {

        super.setPrice(price);
        super.setName(name);
    }

    public double computePrice() {

        return super.getPrice();
    }

    public String toString(){
        return this.getName() +", "+this.getPrice();
    }
}
