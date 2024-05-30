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

    public void addVehicleToDB(Vehicle vehicle) {
        try {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "INSERT INTO Vehicles (Vin, VehicleYear, Make, Model, VehicleType, Color, Odometer, Price, Sold) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, FALSE)");
                    PreparedStatement preparedStatement2 = connection.prepareStatement(
                            "INSERT INTO Inventory (Dealership_id, Vin) VALUES (1, ?)")
            ) {
                // Insert into Vehicles table
                preparedStatement1.setInt(1, vehicle.getVin());
                preparedStatement1.setInt(2, vehicle.getYear());
                preparedStatement1.setString(3, vehicle.getMake());
                preparedStatement1.setString(4, vehicle.getModel());
                preparedStatement1.setString(5, vehicle.getVehicleType());
                preparedStatement1.setString(6, vehicle.getColor());
                preparedStatement1.setInt(7, vehicle.getOdometer());
                preparedStatement1.setDouble(8, vehicle.getPrice());
                preparedStatement1.executeUpdate();

                // Insert into Inventory table
                preparedStatement2.setInt(1, vehicle.getVin());
                preparedStatement2.executeUpdate();

                System.out.println("Vehicle added to Vehicles and Inventory tables successfully");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicleToDB(int userVin) {
        try {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "DELETE FROM LeaseContracts WHERE Vin = ?");
                    PreparedStatement preparedStatement2 = connection.prepareStatement(
                            "DELETE FROM SalesContracts WHERE Vin = ?");
                    PreparedStatement preparedStatement3 = connection.prepareStatement(
                            "DELETE FROM Inventory WHERE Vin = ?");
                    PreparedStatement preparedStatement4 = connection.prepareStatement(
                            "DELETE FROM Vehicles WHERE Vin = ?");
            ) {
                preparedStatement1.setInt(1, userVin);
                preparedStatement1.executeUpdate();
                preparedStatement2.setInt(1, userVin);
                preparedStatement2.executeUpdate();
                preparedStatement3.setInt(1, userVin);
                preparedStatement3.executeUpdate();
                preparedStatement4.setInt(1, userVin);
                preparedStatement4.executeUpdate();

                System.out.println("Vehicle deleted from Vehicles and Inventory tables successfully");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
