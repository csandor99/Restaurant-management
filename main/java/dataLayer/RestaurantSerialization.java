package dataLayer;

import businessLayer.*;

import java.io.*;
import java.util.ArrayList;

public class RestaurantSerialization {

    public static void serialize (Restaurant restaurant){
        try{
            FileOutputStream fileOutputStream = new FileOutputStream("restaurant.ser");
            ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);
            outputStream.writeObject(restaurant);
            outputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
    }

    public static Restaurant deserialize(String file) {
        Restaurant restaurant;
        try{
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            restaurant = (Restaurant) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
            System.out.println(restaurant);
            return restaurant;
        } catch (IOException i) {
            System.out.println(i);
            ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
            Restaurant restaurant1 = new Restaurant(menu);
            serialize(restaurant1);
            return  restaurant1;
        } catch (ClassNotFoundException c){
            System.out.println("Restaurant class doesn't exist!");
            c.printStackTrace();
            ArrayList<MenuItem> menu = new ArrayList<MenuItem>();
            return new Restaurant(menu);
        }

    }
}
