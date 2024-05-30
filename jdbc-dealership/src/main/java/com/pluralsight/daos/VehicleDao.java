package com.pluralsight.daos;

import com.pluralsight.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDao {

    private static DataSource dataSource;

    public VehicleDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ArrayList<Vehicle> getVehiclesFromDB(){
        ArrayList<Vehicle> allVehiclesFromDB = new ArrayList<>();
        try{
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carDealership.Vehicles WHERE SOLD = 0;")
                    ){
                // no need to set string since passed already with no parameters
                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                        ){
                    if (resultSet.next()) {
                        System.out.printf("\n************* All Vehicles ****************\n");
                        // if there are, you are already sitting on the first one so
                        // switch your loop to using a do/while
                        do {
                            int vin = resultSet.getInt("Vin");
                            int vehicleYear = resultSet.getInt("VehicleYear");
                            String make = resultSet.getString("Make");
                            String model = resultSet.getString("Model");
                            String vehicleType = resultSet.getString("VehicleType");
                            String color = resultSet.getString("Color");
                            int odometer = resultSet.getInt("Odometer");
                            double price = resultSet.getDouble("Price");
                            // Display the customer information
                            Vehicle vehicle = new Vehicle(vin,vehicleYear,make,model,vehicleType,color,odometer,price);
                            // add to array list
                            allVehiclesFromDB.add(vehicle);
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("Error getting vehicles from database");
                    }
                }
            }

        }catch (SQLException e){
            // This will catch all SQLExceptions occurring in the
            // block including those in nested try statements
            e.printStackTrace();
        }
        return allVehiclesFromDB;
    }

    public ArrayList<Vehicle> getVehiclesByPrice(double min, double max){
        ArrayList<Vehicle> allVehiclesFromDB = new ArrayList<>();
        try{
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM carDealership.Vehicles WHERE Price >= ? AND Price <= ?;")
            ){
                // no need to set string since passed already with no parameters
                preparedStatement.setDouble(1,min);
                preparedStatement.setDouble(2,max);

                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                ){
                    if (resultSet.next()) {
                        System.out.printf("\n************* All Vehicles ****************\n");
                        // if there are, you are already sitting on the first one so
                        // switch your loop to using a do/while
                        do {
                            int vin = resultSet.getInt("Vin");
                            int vehicleYear = resultSet.getInt("VehicleYear");
                            String make = resultSet.getString("Make");
                            String model = resultSet.getString("Model");
                            String vehicleType = resultSet.getString("VehicleType");
                            String color = resultSet.getString("Color");
                            int odometer = resultSet.getInt("Odometer");
                            double price = resultSet.getDouble("Price");
                            // Display the customer information
                            Vehicle vehicle = new Vehicle(vin,vehicleYear,make,model,vehicleType,color,odometer,price);
                            // add to array list
                            allVehiclesFromDB.add(vehicle);
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("Error getting vehicles from database");
                    }
                }
            }

        }catch (SQLException e){
            // This will catch all SQLExceptions occurring in the
            // block including those in nested try statements
            e.printStackTrace();
        }
        return allVehiclesFromDB;
    }

    public ArrayList<Vehicle> getVehiclesByMakeAndModel(String userMake, String userModel){
        ArrayList<Vehicle> allVehiclesFromDB = new ArrayList<>();
        try{
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicles WHERE Make LIKE ? AND Model LIKE ?;")
            ){
                // no need to set string since passed already with no parameters
                preparedStatement.setString(1, "%" + userMake + "%");
                preparedStatement.setString(2, "%" + userModel + "%");

                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                ){
                    if (resultSet.next()) {
                        System.out.printf("\n************* All Vehicles ****************\n");
                        // if there are, you are already sitting on the first one so
                        // switch your loop to using a do/while
                        do {
                            int vin = resultSet.getInt("Vin");
                            int vehicleYear = resultSet.getInt("VehicleYear");
                            String make = resultSet.getString("Make");
                            String model = resultSet.getString("Model");
                            String vehicleType = resultSet.getString("VehicleType");
                            String color = resultSet.getString("Color");
                            int odometer = resultSet.getInt("Odometer");
                            double price = resultSet.getDouble("Price");
                            // Display the customer information
                            Vehicle vehicle = new Vehicle(vin,vehicleYear,make,model,vehicleType,color,odometer,price);
                            // add to array list
                            allVehiclesFromDB.add(vehicle);
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("Error getting vehicles from database");
                    }
                }
            }

        }catch (SQLException e){
            // This will catch all SQLExceptions occurring in the
            // block including those in nested try statements
            e.printStackTrace();
        }
        return allVehiclesFromDB;
    }

    public ArrayList<Vehicle> getVehiclesByYear(int userYear){
        ArrayList<Vehicle> allVehiclesFromDB = new ArrayList<>();
        try{
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicles WHERE VehicleYear = ?;")
            ){
                // no need to set string since passed already with no parameters
                preparedStatement.setInt(1, userYear);

                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                ){
                    if (resultSet.next()) {
                        System.out.printf("\n************* All Vehicles ****************\n");
                        // if there are, you are already sitting on the first one so
                        // switch your loop to using a do/while
                        do {
                            int vin = resultSet.getInt("Vin");
                            int vehicleYear = resultSet.getInt("VehicleYear");
                            String make = resultSet.getString("Make");
                            String model = resultSet.getString("Model");
                            String vehicleType = resultSet.getString("VehicleType");
                            String color = resultSet.getString("Color");
                            int odometer = resultSet.getInt("Odometer");
                            double price = resultSet.getDouble("Price");
                            // Display the customer information
                            Vehicle vehicle = new Vehicle(vin,vehicleYear,make,model,vehicleType,color,odometer,price);
                            // add to array list
                            allVehiclesFromDB.add(vehicle);
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("Error getting vehicles from database");
                    }
                }
            }

        }catch (SQLException e){
            // This will catch all SQLExceptions occurring in the
            // block including those in nested try statements
            e.printStackTrace();
        }
        return allVehiclesFromDB;
    }

    public ArrayList<Vehicle> getVehiclesByColor(String userColor){
        ArrayList<Vehicle> allVehiclesFromDB = new ArrayList<>();
        try{
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicles WHERE Color LIKE ?;")
            ){
                // no need to set string since passed already with no parameters
                preparedStatement.setString(1, "%" + userColor + "%");

                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                ){
                    if (resultSet.next()) {
                        System.out.printf("\n************* All Vehicles ****************\n");
                        // if there are, you are already sitting on the first one so
                        // switch your loop to using a do/while
                        do {
                            int vin = resultSet.getInt("Vin");
                            int vehicleYear = resultSet.getInt("VehicleYear");
                            String make = resultSet.getString("Make");
                            String model = resultSet.getString("Model");
                            String vehicleType = resultSet.getString("VehicleType");
                            String color = resultSet.getString("Color");
                            int odometer = resultSet.getInt("Odometer");
                            double price = resultSet.getDouble("Price");
                            // Display the customer information
                            Vehicle vehicle = new Vehicle(vin,vehicleYear,make,model,vehicleType,color,odometer,price);
                            // add to array list
                            allVehiclesFromDB.add(vehicle);
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("Error getting vehicles from database");
                    }
                }
            }

        }catch (SQLException e){
            // This will catch all SQLExceptions occurring in the
            // block including those in nested try statements
            e.printStackTrace();
        }
        return allVehiclesFromDB;
    }

    public ArrayList<Vehicle> getVehiclesByMileage(double minMiles, double maxMiles){
        ArrayList<Vehicle> allVehiclesFromDB = new ArrayList<>();
        try{
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicles WHERE Odometer >= ? AND Odometer <= ?;")
            ){
                // no need to set string since passed already with no parameters
                preparedStatement.setDouble(1, minMiles);
                preparedStatement.setDouble(2, maxMiles);

                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                ){
                    if (resultSet.next()) {
                        System.out.printf("\n************* All Vehicles ****************\n");
                        // if there are, you are already sitting on the first one so
                        // switch your loop to using a do/while
                        do {
                            int vin = resultSet.getInt("Vin");
                            int vehicleYear = resultSet.getInt("VehicleYear");
                            String make = resultSet.getString("Make");
                            String model = resultSet.getString("Model");
                            String vehicleType = resultSet.getString("VehicleType");
                            String color = resultSet.getString("Color");
                            int odometer = resultSet.getInt("Odometer");
                            double price = resultSet.getDouble("Price");
                            // Display the customer information
                            Vehicle vehicle = new Vehicle(vin,vehicleYear,make,model,vehicleType,color,odometer,price);
                            // add to array list
                            allVehiclesFromDB.add(vehicle);
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("Error getting vehicles from database");
                    }
                }
            }

        }catch (SQLException e){
            // This will catch all SQLExceptions occurring in the
            // block including those in nested try statements
            e.printStackTrace();
        }
        return allVehiclesFromDB;
    }

    public ArrayList<Vehicle> getVehiclesByType(String userType){
        ArrayList<Vehicle> allVehiclesFromDB = new ArrayList<>();
        try{
            try(
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Vehicles WHERE VehicleType LIKE ?;")
            ){
                // no need to set string since passed already with no parameters
                preparedStatement.setString(1, "%" + userType + "%");

                try(
                        ResultSet resultSet = preparedStatement.executeQuery();
                ){
                    if (resultSet.next()) {
                        System.out.printf("\n************* All Vehicles ****************\n");
                        // if there are, you are already sitting on the first one so
                        // switch your loop to using a do/while
                        do {
                            int vin = resultSet.getInt("Vin");
                            int vehicleYear = resultSet.getInt("VehicleYear");
                            String make = resultSet.getString("Make");
                            String model = resultSet.getString("Model");
                            String vehicleType = resultSet.getString("VehicleType");
                            String color = resultSet.getString("Color");
                            int odometer = resultSet.getInt("Odometer");
                            double price = resultSet.getDouble("Price");
                            // Display the customer information
                            Vehicle vehicle = new Vehicle(vin,vehicleYear,make,model,vehicleType,color,odometer,price);
                            // add to array list
                            allVehiclesFromDB.add(vehicle);
                        } while (resultSet.next());
                    }
                    else {
                        System.out.println("Error getting vehicles from database");
                    }
                }
            }

        }catch (SQLException e){
            // This will catch all SQLExceptions occurring in the
            // block including those in nested try statements
            e.printStackTrace();
        }
        return allVehiclesFromDB;
    }
}
