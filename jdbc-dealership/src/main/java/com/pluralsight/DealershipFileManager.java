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

}
