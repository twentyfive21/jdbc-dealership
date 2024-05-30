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
    /* ------------------ deleting vehicle -----------------------

    // when deleting a vehicle delete from contracts first, inventory, then lastly vehicle
    // DELETE FROM carDealership.LeaseContracts WHERE Vin = 1006;
    // DELETE FROM carDealership.SalesContracts WHERE Vin = 1006;
    // DELETE FROM carDealership.Inventory WHERE Vin = 1006;
    // DELETE FROM carDealership.Vehicles WHERE Vin = 1006;

    */



 /* ------------------ VEHICLE SALE -----------------------
    // when doing a sale add to sales contract and set vehicle tables sold value to true
     SELECT Vin FROM carDealership.SalesContracts; check for vins that do not match these
     if not matching

    INSERT INTO SalesContracts (Vin)
    VALUES (1001);

  */


    /* ------------------ VEHICLE LEASE  -----------------------
    // when leasing a vehicle check if in lease table if not allow to be leased
    // show vehicles not leased

        SELECT *
        FROM carDealership.Vehicles
        LEFT JOIN LeaseContracts ON Vehicles.Vin = LeaseContracts.Vin
        WHERE LeaseContracts.Vin IS NULL;

    // check if users vin is in lease if not add it use this to check
     SELECT Vin FROM carDealership.LeaseContracts;

    // then insert if doesnt match
     INSERT INTO LeaseContracts (Vin)
    VALUES (1001);

     */
}
