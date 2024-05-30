package com.pluralsight;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ContractDataManager {

    public void saveContract(Contract contract){

        try{ // set pipe variable for writing
            final String pipe = "|";

            // get vehicle data to have buf write readable when formatting
            int vin = contract.getVehicleSold().getVin();
            String customerName = contract.getCustomerName();
            String customerEmail = contract.getEmail();
            int odometer = contract.getVehicleSold().getOdometer();
            int year = contract.getVehicleSold().getYear();
            String make = contract.getVehicleSold().getMake();
            String model = contract.getVehicleSold().getModel();
            String type = contract.getVehicleSold().getVehicleType();
            String color = contract.getVehicleSold().getColor();
            double total = contract.getTotalPrice();
            double payment = contract.getMonthlyPayment();
            String contractDate = contract.getContractDate();
            double originalPrice = contract.getVehicleSold().getPrice();


            // create writer and file writer
            BufferedWriter bufWriter = new BufferedWriter(new FileWriter("contract.csv", true));
            // new line for next contract that is written
            bufWriter.newLine();
            // if lease instance
            if(contract instanceof LeaseContract){
                // downcast
                LeaseContract lease = (LeaseContract) contract;
                double expected = lease.getExpectedValue();
                double leaseFee = lease.getLeaseFee();
            bufWriter.write("LEASE" + pipe + contractDate + pipe + customerName + pipe +
                    customerEmail + pipe + vin + pipe + year + pipe + make + pipe + model
                    + pipe + type + pipe + color + pipe + odometer + pipe + originalPrice + pipe
                    + total + pipe + expected + pipe + leaseFee + pipe + payment);
            }

            // if sales instance
            if (contract instanceof SalesContract){
                // check if they chose to finance or not
                SalesContract sales = (SalesContract) contract;
                boolean financeOption = sales.isFinance();
                double salesTax = sales.getSalesTax();
                int processFee = sales.getProcessingFee();
                int recordingFee = sales.getRecordingFee();
                bufWriter.write("SALE" + pipe + contractDate + pipe + customerName + pipe + customerEmail
                        + pipe + vin + pipe + year + pipe + make + pipe + model
                        + pipe + type + pipe + color + pipe + odometer + pipe + originalPrice + pipe
                        + salesTax + pipe + recordingFee + pipe + processFee + pipe + total + pipe +
                        financeOption + pipe + payment);

            }
            // close writer
            bufWriter.flush();
            bufWriter.close();
        } catch(Exception e){
            System.out.println("Error writing to contract file");
            e.printStackTrace();
        }

    }

}
