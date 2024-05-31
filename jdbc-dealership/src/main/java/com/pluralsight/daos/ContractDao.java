package com.pluralsight.daos;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ContractDao {
    private static DataSource dataSource;

    public ContractDao(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void addVehicleForLease(int userLeaseVin) {
        try {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "INSERT INTO LeaseContracts (Vin) VALUES (?)");

            ) {
                preparedStatement1.setInt(1, userLeaseVin);
                preparedStatement1.executeUpdate();

            }
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("\n~~~~ Cannot add Vehicle to be leased ~~~~");
        }
    }

    public void addVehicleForSale(int userSaleVin) {
        try {
            try (
                    Connection connection = dataSource.getConnection();
                    PreparedStatement preparedStatement1 = connection.prepareStatement(
                            "INSERT INTO SalesContracts (Vin) VALUES (?)");
                    PreparedStatement preparedStatement2 =
                            connection.prepareStatement("UPDATE Vehicles SET Sold = TRUE WHERE Vin = ?;")

            ) {
                preparedStatement1.setInt(1, userSaleVin);
                preparedStatement1.executeUpdate();
                preparedStatement2.setInt(1, userSaleVin);
                preparedStatement2.executeUpdate();

            }
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println("\n~~~~ Cannot add Vehicle for sale! ~~~~");
        }
    }

}
