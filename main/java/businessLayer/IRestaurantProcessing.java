package businessLayer;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public interface IRestaurantProcessing {

    /**
     * @param item
     * @pre item != null
     * @post menu.size() == @pre menu.size()+1
     */
    public void createMenuItem(MenuItem item);

    /**
     * @param name
     * @pre name != null
     * @post menu.size() < @pre menu.size()
     */
    public void deleteMenuItem(String name);

    /**
     * @param name
     * @param price
     * @pre name != null
     */
    public void editMenuItem(String name, double price);

    /**
     * @param order
     * @param itemList
     * @pre order != null; itemList != null
     * @post price != 0
     */
    public void createNewOrder(Order order, ArrayList<MenuItem> itemList);

    /**
     * @param orderId
     * @return
     * @pre orderId != 0
     * @post price != 0
     */
    public double computePriceOrder(int orderId);

    /**
     * @param orderId
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     * @pre orderId != 0
     */
    public void generateBill(int orderId) throws FileNotFoundException, UnsupportedEncodingException;

}
