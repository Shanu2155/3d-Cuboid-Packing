package logic;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Shantanu on 22-06-2017.
 */
public class Service {

    public static ArrayList<Cuboid> getResult(String json ){
//        json="{\"vehicleName\":\"Medium Truck\",\"orders\":[{\"orderId\":1,\"itemName\":\"TV\",\"itemQuantity\":\"2\"},{\"orderId\":2,\"itemName\":\"Fridge\",\"itemQuantity\":\"2\"},{\"orderId\":2,\"itemName\":\"Mobile\",\"itemQuantity\":\"3\"},{\"orderId\":3,\"itemName\":\"Washing Machine\",\"itemQuantity\":\"2\"}]}";
        Gson gson=new Gson();
        Orders orders=gson.fromJson(json,Orders.class);
        System.out.println(orders);
        Vehicle vehicle=Vehicle.getVehicle(orders.vehicleName);
        Cuboid bin=new Cuboid(vehicle.length,vehicle.breadth,vehicle.height,vehicle.capacity);
        PackingAlgorithm packing=new PackingAlgorithm();
        ArrayList<Cuboid> cuboids=Item.getCuboids(orders.orders);
        ArrayList<Cuboid> drawCuboids=packing.fitCuboids(bin.length,bin.breadth,bin.height, bin.weight,cuboids);
//        System.out.println(cuboids);
//        System.out.println(drawCuboids);
//        RotationExample3D.runThis(drawCuboids);
        return drawCuboids;
    }
    
    public static ArrayList<Cuboid> getCuboids(Vehicle v, ArrayList<Item> items){
        ArrayList<Cuboid> cuboids=new ArrayList<>();
        System.out.println(items);
        for(Item i:items){
            Cuboid c=new Cuboid(i.id, i.length, i.breadth, i.height, i.weight, i.isFragile, i.thisSideUp);
            cuboids.add(c);
        }
        Cuboid bin=new Cuboid(v.length,v.breadth,v.height,v.capacity);
        PackingAlgorithm packing=new PackingAlgorithm();
//        ArrayList<Cuboid> drawCuboids=packing.fitCuboids(bin.length,bin.breadth,bin.height, bin.weight,cuboids);
        ArrayList<Cuboid> drawCuboids=packing.fitCuboids(bin,cuboids);
        return drawCuboids;
    
    }


    public static void main(String[] args) {
        getResult(null);
//        String json="{\"vehicleName\":\"Medium Truck\",\"orders\":[{\"orderId\":1,\"itemName\":\"TV\",\"itemQuantity\":\"2\"},{\"orderId\":2,\"itemName\":\"Fridge\",\"itemQuantity\":\"2\"},{\"orderId\":2,\"itemName\":\"Mobile\",\"itemQuantity\":\"3\"},{\"orderId\":3,\"itemName\":\"Washing Machine\",\"itemQuantity\":\"2\"}]}";
//        Gson gson=new Gson();
//        Orders orders=gson.fromJson(json,Orders.class);
//        System.out.println(orders);
//        Vehicle vehicle=Vehicle.getVehicle(orders.vehicleName);
//        Cuboid bin=new Cuboid(vehicle.length,vehicle.breadth,vehicle.height,vehicle.capacity);
//        PackingAlgorithm packing=new PackingAlgorithm();
//        ArrayList<Cuboid> cuboids=Item.getCuboids(orders.orders);
//        System.out.println("BIN= "+bin);
//        ArrayList<Cuboid> drawCuboids=packing.fitCuboids(bin.length,bin.breadth,bin.height, bin.weight,cuboids);
//        System.out.println(cuboids);
//        System.out.println(drawCuboids);
//        for(Cuboid c:cuboids)
//        {
////            System.out.println("id="+c.id+" Point="+c.bottomLeftRear);
//            System.out.println("c="+c);
//        }
    }
}
