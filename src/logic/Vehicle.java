package logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Vehicle {
    public long id;
    public String name;
    public double length,breadth,height,capacity;
    public static Connection connection;
    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/test", "postgres", "lguru");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
    public static Vehicle getVehicle(String name){
        Vehicle vehicle=new Vehicle();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from vehicles where name='" +name + "';");
            while(resultSet.next()){
                vehicle.id=resultSet.getLong("id");
                vehicle.name=resultSet.getString("name");
                vehicle.length=resultSet.getDouble("length");
                vehicle.breadth=resultSet.getDouble("breadth");
                vehicle.height=resultSet.getDouble("height");
                vehicle.capacity=resultSet.getDouble("capacity");
            }
            statement.close();
            resultSet.close();
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        return vehicle;
    }
    public static ArrayList<Vehicle> getVehicles(){
        ArrayList<Vehicle> vehicles=new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery("select * from vehicles;");
            while(resultSet.next()){
                Vehicle vehicle=new Vehicle();
                vehicle.id=resultSet.getLong("id");
                vehicle.name=resultSet.getString("name");
                vehicle.length=resultSet.getDouble("length");
                vehicle.breadth=resultSet.getDouble("breadth");
                vehicle.height=resultSet.getDouble("height");
                vehicle.capacity=resultSet.getDouble("capacity");
                vehicles.add(vehicle);
            }
            statement.close();
            resultSet.close();
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
        return vehicles;
    }
}


