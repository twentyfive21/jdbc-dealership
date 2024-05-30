package com.pluralsight;

// SalesContract class inherits from Contract class
public class SalesContract extends Contract{
    private double salesTax;
    private int recordingFee = 100;
    private int processingFee;
    private boolean finance;

    // constructor
    public SalesContract(String contractDate, String customerName, String email, Vehicle vehicleSold, boolean finance){
        super(contractDate,customerName,email,vehicleSold);
        this.salesTax = vehicleSold.getPrice() * 0.05;
        this.processingFee = vehicleSold.getPrice() < 10000 ? 295 : 495;
        this.finance = finance;
    }

    // getters and setters

    public double getSalesTax() {
        return Double.parseDouble(String.format("%.2f", salesTax));
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public int getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(int recordingFee){
        this.recordingFee = recordingFee;
    }

    public int getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(int processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    @Override
    public double getTotalPrice(){
        double salesTotal = getVehicleSold().getPrice() + salesTax + recordingFee + processingFee;
        return Double.parseDouble(String.format("%.2f", salesTotal));
    }

    @Override
    public double getMonthlyPayment(){

        if(finance){
            double totalPrice = getTotalPrice();
            double interestRate = totalPrice >= 10000 ? 4.25 : 5.25;
            int loanTerm = totalPrice >= 10000 ? 48 : 24;
            // P = (T × r) / (1 - (1 + r)^-n)
            // t = total price , r interest rate, n is loan term 36months
            double monthly = (totalPrice * interestRate) / (1 - Math.pow(1 + interestRate, -loanTerm));
            return Double.parseDouble(String.format("%.2f", monthly));
        }
        return 0;
    }

    @Override
    public String toString() {
        return "SalesContract{" +
                "salesTax=" + salesTax +
                ", recordingFee=" + recordingFee +
                ", processingFee=" + processingFee +
                ", finance=" + finance +
                '}';
    }
}
