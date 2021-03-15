package businessLayer;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {
    public int orderId;
    private Date date;
    public int table;
    public double totalPrice;

    public Order(int id, int table){
        this.orderId = id;
        date = new Date();
        this.table = table;
    }

    public Date getDate() {
        return date;
    }

    public void setDate()
    {
        date = new Date();
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getTable() {
        return table;
    }

    public void setTable(int table) {
        this.table = table;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int hashCode() {
        int hashCode = 13;
        hashCode += hashCode * orderId + hashCode * date.hashCode() + hashCode * table;
        return hashCode;
    }
}
