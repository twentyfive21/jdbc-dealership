package com.pluralsight;

import com.pluralsight.daos.VehicleDao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
/*
This DealershipFileManager class facilitates reading dealership information
and writing it to a file. The getDealership() method reads from "inventory.csv"
and constructs a Dealership object along with its vehicles.
The saveDealership() writes the dealership's details back to the file.
Both methods handle file I/O (input/output) and ensure data integrity.
 */

public class DealershipFileManager {

    public Dealership getDealership(){
        DatabaseConnector databaseConnector = new DatabaseConnector();
        VehicleDao vehicleDao = new VehicleDao(databaseConnector.setUpConnection());
        Dealership dealership = new Dealership("Toyota","123 main st", "323-223-4324");

        ArrayList<Vehicle> currentVehicles = vehicleDao.getVehiclesFromDB();
        // currentVehicles.forEach(vehicle -> dealership.addVehicle(vehicle));
        currentVehicles.forEach(dealership::addVehicle);
        return dealership;
    }

    // overwrite the inventory file with current dealership information
    public void saveDealership(Dealership dealership){
     try{// create new buf writer to write to file
         BufferedWriter bufWriter = new BufferedWriter(new FileWriter("inventory.csv"));
         // write first line of dealership object
         bufWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
         // ship new line for vehicles
         bufWriter.newLine();
         String pipe = "|";
         // loop through array list and add each vehicle
         for(Vehicle car : dealership.getAllVehicles()){
             bufWriter.write(car.getVin()+ pipe +car.getYear()+ pipe + car.getMake() + pipe
                     +car.getModel()+ pipe + car.getVehicleType()+ pipe + car.getColor()+ pipe +
                     car.getOdometer()+ pipe + car.getPrice());
             // new line so vehicles are not stacked on top of each other
             bufWriter.newLine();
         }
         // close the writer
         bufWriter.flush();
         bufWriter.close();
     }catch (Exception e){
         System.out.println("Error saving new dealership info");
     }
    }
}
