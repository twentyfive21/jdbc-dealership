package com.pluralsight;

public abstract class Contract {

    // private members
    private String contractDate;
    private String customerName;
    private String email;
    private Vehicle vehicleSold;
    private double total;
    private double monthlyPayment;

    // constructor
    public Contract(String contractDate, String customerName, String email, Vehicle vehicleSold) {
        this.contractDate = contractDate;
        this.customerName = customerName;
        this.email = email;
        this.vehicleSold = vehicleSold;
    }

    // getters and setters
    public String getContractDate() {
        return contractDate;
    }

    public void setContractDate(String contractDate) {
        this.contractDate = contractDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Vehicle getVehicleSold() {
        return vehicleSold;
    }

    public void setVehicleSold(Vehicle vehicleSold) {
        this.vehicleSold = vehicleSold;
    }

    // abs methods
    public abstract double getTotalPrice();

    public abstract double getMonthlyPayment();

}
