package com.pluralsight;

import com.pluralsight.daos.VehicleDao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    // DeclareDealership object to manage dealership data
    private Dealership dealership;
    private VehicleDao vehicleDao = new VehicleDao(DatabaseConnector.setUpConnection());
    // Initialization of a Scanner object for user input
    Scanner scanner = new Scanner(System.in);
    ContractDataManager contractDataManager = new ContractDataManager();

    public UserInterface() {
        // empty constructor
    }

    private void init() {
        // Instantiate a DealershipFileManager object to manage file operations
        DealershipFileManager manager = new DealershipFileManager();
        // Retrieve dealership information from the file
        this.dealership = manager.getDealership();
    }

    // Display details of vehicles in the provided ArrayList
    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle item : vehicles) {
            System.out.println(item);
        }
    }

    public void display() {
        // Initialize the user interface
        init();
        boolean running = true;
        while (running) {
            // Display available choices for user interaction
            displayChoices();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                // cases for user's choice
                case "1":
                    processGetByPriceRequest();
                    break;
                case "2":
                    processGetByMakeModelRequest();
                    break;
                case "3":
                    processGetByYearRequest();
                    break;
                case "4":
                    processGetByColorRequest();
                    break;
                case "5":
                    processGetByMileageRequest();
                    break;
                case "6":
                    processGetByVehicleTypeRequest();
                    break;
                case "7":
                    processGetAllVehiclesRequest();
                    break;
                case "8":
                    processAddVehicleRequest();
                    break;
                case "9":
                    processRemoveVehicleRequest();
                    break;
                case "10":
                    processSellOrLeaseRequest();
                    break;
                case "11":
                    processAdmin();
                    break;
                case "0":
                    System.out.println("\nYou have chosen to leave! Have a nice day and come again!");
                    running = false;
                    break;
                default:
                    System.out.println("\n**** Error invalid choice! Please choose a valid option. ****\n");
                    break;
            }
        }

    }

    public void processAdmin(){
        AdminUserInterface adminUserInterface = new AdminUserInterface();
        // allow admin to login and display contracts password is 123
        adminUserInterface.display();
    }

    public void displayChoices() {
        // Display the available choices for user interaction
        System.out.println("\n---- Welcome to the dealership! Please select one of the following. ----\n");
        System.out.println("(1) Find vehicles within a price range");
        System.out.println("(2) Find vehicles by make / model");
        System.out.println("(3) Find vehicles by year range");
        System.out.println("(4) Find vehicles by color");
        System.out.println("(5) Find vehicles by mileage range");
        System.out.println("(6) Find vehicles by type(car,truck,suv,van)");
        System.out.println("(7) List all vehicles");
        System.out.println("(8) Add a vehicle");
        System.out.println("(9) Remove a vehicle");
        System.out.println("(10) Sale or Lease a vehicle");
        System.out.println("(11) Admin");
        // did 0 instead of 99 for user to type less. Choice 0 is used for quitting
        System.out.println("(0) Quit");
        System.out.print("Selection: ");
    }

    public void processSellOrLeaseRequest(){
        System.out.println("~~~~ You have chosen sale or lease a vehicle ~~~~");
        System.out.println("(1) Sale");
        System.out.println("(2) Lease");
        System.out.print("Selection: ");
        String choice = scanner.nextLine();
        switch (choice){
            case "1" : processContractType("sale");
                break;
            case "2" : processContractType("lease");
                break;
            default: System.out.println("Error please pick 1 or 2");
                    processSellOrLeaseRequest();
                    break;
        }
    }

    public Vehicle processContractType(String type) {
        System.out.printf("\nYou have chosen %s contract\n", type);

        // ask for choice if sale contract
        boolean userChoice = false;
        if(type.equals("sale")){
            System.out.println("Would you like to finance ? y(yes) or n(no)");
            System.out.print("Selection: ");
            String choice = scanner.nextLine().toLowerCase().trim();
            switch (choice) {
                case "y":
                    userChoice = true;
                    break;
                case "n":
                    userChoice = false;
                    break;
                default:
                    System.out.println("Please pick yes or no!");
                    processSellOrLeaseRequest();
                    break;
            }
        }

        // get contract data for csv
        String contractDate = LocalDate.now().getYear() + "" + LocalDate.now().getDayOfMonth() + "" + LocalDate.now().getMonthValue();
        System.out.print("Please provide your name: ");
        String customerName = scanner.nextLine().trim();
        System.out.print("Please provide your email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Please provide vin number of the car you want: ");
        int vinNum = scanner.nextInt();
        // clear buffer
        scanner.nextLine();

        // set to null unless match is found
        Vehicle vehicleSold = null;
        // check for matching vin for contract
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vinNum == vehicle.getVin()) {
                vehicleSold = vehicle;
            }
        }

        // create new object or print error based on search
        if(vehicleSold == null){
            System.out.println("*** Error no matching vehicle with that vin ***");
        } else if (type.equals("sale")){
            SalesContract sale = new SalesContract(contractDate,customerName,email,vehicleSold,userChoice);
            // pass contract to be written
            contractDataManager.saveContract(sale);
        } else {
            LeaseContract lease = new LeaseContract(contractDate,customerName,email,vehicleSold);
            // pass contract to be written
            contractDataManager.saveContract(lease);
        }
        // display removed vehicle
        System.out.printf("\n~~~~ Vehicle %s contract for %s ~~~~\n",type,customerName);
        System.out.println(vehicleSold);
        // delete from inventory
        dealership.removeVehicle(vehicleSold);
        return vehicleSold;
    }

    public void processGetByPriceRequest() {
        try { // Process user's request to find vehicles within a price range
            System.out.println("\n~~~~ You have chosen to search by Price ~~~~");
            System.out.print("Max : ");
            double max = scanner.nextDouble();
            System.out.print("Min : ");
            double min = scanner.nextDouble();
            scanner.nextLine();
            ArrayList<Vehicle> inRange = vehicleDao.getVehiclesByPrice(min, max);
            // Create a new ArrayList to store vehicles that fall within the specified price range
            // boolean for matching result
            boolean match = false;

            if(!inRange.isEmpty()){
                match = true;
            }

            // Display vehicles in the price range
            displayVehicles(inRange);
            // display message for match result
            if (!match) {
                System.out.println("\n **** No matching cars found in price range ****");
            } else {
                System.out.println("++++++++++ End of matching price results ++++++++++");
            }
        } catch (Exception e) {
            // clear buffer if try fails
            scanner.nextLine();
            System.out.println("!! Error please provide a valid price !!");
            // re-run program
            processGetByPriceRequest();
        }
    }

    public void processGetByMakeModelRequest() {
        // Process user's request to find vehicles by make/model
        System.out.println("~~~~ You have chosen to search by make/model ~~~~");
        System.out.print("Please provide the make: ");
        String makeInput = scanner.nextLine().toLowerCase().trim();
        System.out.print("Please provide the model: ");
        String modelInput = scanner.nextLine().toLowerCase().trim();
        // boolean for matching result
        boolean match = false;
        // Create a new ArrayList to store vehicles that fall within the specified price range
        ArrayList<Vehicle> matchingMakeModel = vehicleDao.getVehiclesByMakeAndModel(makeInput, modelInput);

        if(!matchingMakeModel.isEmpty()){
            match = true;
        }

        // pass matching vehicles to display
        displayVehicles(matchingMakeModel);
        // display message for match result
        if (!match) {
            System.out.println("\n**** No matching cars found with make and model provided ****");
        } else {
            System.out.println("++++++++++ End of matching make/model results ++++++++++");
        }
    }

    public void processGetByYearRequest() {
        try {
            System.out.println("\n~~~~ You have chosen to search by year ~~~~");
            System.out.print("Year: ");
            int year = scanner.nextInt();
            // clear buffer
            scanner.nextLine();

            // create new arraylist to hold matching vehicles
            ArrayList<Vehicle> yearMatch =vehicleDao.getVehiclesByYear(year);
            // set match to false until match is founf
            boolean match = yearMatch.isEmpty() ? false : true;
            // send matching vehicles to be displayed
            displayVehicles(yearMatch);
            // display match results
            if (!match) {
                System.out.println("\n**** No matching cars found with year provided ****");
            } else {
                System.out.println("++++++++++ End of matching year results ++++++++++");
            }
        } catch (Exception e) {
            // clear buffer if string is given causing error
            scanner.nextLine();
            System.out.println("Please provide a valid number!");
            // re-run program
            processGetByYearRequest();
        }
    }

    public void processGetByColorRequest() {
        System.out.println("**** You have chosen to search by color! ****");
        System.out.print("Please provide the color: ");
        String color = scanner.nextLine().trim().toLowerCase();

        // create array list for matching color vehicles
        ArrayList<Vehicle> matchingColor = vehicleDao.getVehiclesByColor(color);
        boolean match = matchingColor.isEmpty() ? false : true;
        // call display method
        displayVehicles(matchingColor);
        if (!match) {
            System.out.println("\n**** No matching cars found with color provided ****");
        } else {
            System.out.println("++++++++++ End of matching color results ++++++++++");
        }
    }

    public void processGetByMileageRequest() {
        try {
            System.out.println("You have chosen to search by mileage");
            System.out.println("Please provide the mileage ");
            System.out.print("Max : ");
            double max = scanner.nextDouble();
            System.out.print("Min : ");
            double min = scanner.nextDouble();
            scanner.nextLine();
            // Create a new ArrayList to store vehicles that fall within the specified price range
            ArrayList<Vehicle> mileageMatch = vehicleDao.getVehiclesByMileage(min, max);
            // boolean for matching result
            boolean match = mileageMatch.isEmpty() ? false : true;

            // Display vehicles in the price range
            displayVehicles(mileageMatch);
            // display message for match result
            if (!match) {
                System.out.println("\n **** No matching cars found in mileage range ****");
            } else {
                System.out.println("++++++++++ End of matching mileage results ++++++++++");
            }
        } catch (Exception e) {
            // clear buffer if try fails
            scanner.nextLine();
            System.out.println("!! Error please provide a valid mileage !!");
            // re-run program
            processGetByMileageRequest();
        }
    }

    public void processGetByVehicleTypeRequest() {
        System.out.println("**** You have chosen to search vehicle by type(car,suv,truck,van,ect) ****");
        System.out.print("Selection: ");
        String carType = scanner.nextLine().trim().toLowerCase();

        ArrayList<Vehicle> matchingType = vehicleDao.getVehiclesByType(carType);

        boolean match = matchingType.isEmpty() ? false : true;
        // call display method
        displayVehicles(matchingType);
        // display message for match result
        if (!match) {
            System.out.println("\n **** No matching cars found by type ****");
        } else {
            System.out.println("++++++++++ End of matching car type results ++++++++++");
        }
    }

    public void processGetAllVehiclesRequest() {
        System.out.println("\n~~~~~~~ Start of all vehicles ~~~~~~~ ");
        displayVehicles(dealership.getAllVehicles());
        System.out.println("~~~~~~~ End of all vehicles ~~~~~~~\n");
    }

    public void processAddVehicleRequest() {
        try {// get input to add vehicle to csv and array list
            System.out.println("**** You have chosen to add a vehicle ****");
            System.out.print("Please provide Vin: ");
            int vin = scanner.nextInt();
            System.out.print("Please provide Year: ");
            int year = scanner.nextInt();
            System.out.print("Please provide Odometer: ");
            int odometer = scanner.nextInt();
            System.out.print("Please provide Price: ");
            double price = scanner.nextInt();
            // clear leftover in buffer
            scanner.nextLine();
            System.out.print("Please provide Make: ");
            String make = scanner.nextLine().trim();
            System.out.print("Please provide Model: ");
            String model = scanner.nextLine().trim();
            System.out.print("Please provide Vehicle Type(car,suv,truck): ");
            String vehicleType = scanner.nextLine().trim();
            System.out.print("Please provide Color: ");
            String color = scanner.nextLine().trim();
            // use constructor to instantiate a new Vehicle object
            Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
            // add new vehicle to arraylist
            dealership.addVehicle(vehicle);
            System.out.println("\n**** Vehicle had been added ****");
            System.out.println(vehicle);
            System.out.println("\n**** Thank you for registering you vehicle today!  ****");
        } catch (Exception e) {
            // catch error from int or double
            scanner.nextLine();
            System.out.println("**** Error please provide a valid number ****");
            // re-run method
            processAddVehicleRequest();
        }
    }

    public void processRemoveVehicleRequest() {
        try{
            System.out.println("**** You have chosen to remove a vehicle ****");
            System.out.print("Please provide vin number for removal: ");
            int vin = scanner.nextInt();
            scanner.nextLine();
            // Iterate over the inventory and mark vehicles for removal
            boolean found = false;
            // vehicle saved here to display item found for deletion
            Vehicle tempVehicle = null;
            for (Vehicle vehicle : dealership.getAllVehicles()) {
                if (vehicle.getVin() == vin) {
                    dealership.removeVehicle(vehicle);
                    tempVehicle = vehicle;
                    found = true;
                    /* must break when finding a match! else
                    ConcurrentModificationException occurs
                    since loop has a missing line and has no clue
                    where to loop
                    */
                    break;
                }
            }
            if (!found) {
                System.out.println("\n**** Car not found with matching vin number! ****");
            } else {
                System.out.println("\n**** Vehicle(s) below has been deleted! ****");
                System.out.println(tempVehicle);
                System.out.println("\n**** Thank you for removing your unused vehicle(s)! ****");
            }
        }catch (Exception e){
        // clear buffer for error if given string
            scanner.nextLine();
            System.out.println("\n**** Error please provide a number! ****");
            // take user back to home screen
            processRemoveVehicleRequest();
        }
    }
}