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

   /*   ------------------ SEARCH QUERIES  -----------------------

     SELECT * FROM carDealership.Vehicles WHERE Price <= 30000 AND Price >= 27000;
     SELECT * FROM carDealership.Vehicles WHERE Make LIKE '%Toyota%' AND Model LIKE '%Camry%';
     SELECT * FROM carDealership.Vehicles WHERE VehicleYear = 2020;
     SELECT * FROM carDealership.Vehicles WHERE Color LIKE '%blue%';
      SELECT * FROM carDealership.Vehicles WHERE Odometer >= 4000 AND Odometer <= 18000;
     SELECT * FROM carDealership.Vehicles WHERE VehicleType LIKE '%sedan%';


     */


    /* ------------------ deleting vehicle -----------------------

    // when deleting a vehicle delete from contracts first, inventory, then lastly vehicle
    // DELETE FROM carDealership.LeaseContracts WHERE Vin = 1006;
    // DELETE FROM carDealership.SalesContracts WHERE Vin = 1006;
    // DELETE FROM carDealership.Inventory WHERE Vin = 1006;
    // DELETE FROM carDealership.Vehicles WHERE Vin = 1006;

    */


    /* ------------------ ADDING vehicle -----------------------

    // when adding a vehicle add to the vehicle table first then inventory table

    INSERT INTO Vehicles (Vin, VehicleYear, Make, Model, VehicleType, Color, Odometer, Price, Sold) VALUES
    (1001, 2020, 'Toyota', 'Camry', 'Sedan', 'Blue', 15000, 22000.00, FALSE),

    INSERT INTO Inventory (Dealership_id,Vin)
    VALUES (1, 1001);

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
