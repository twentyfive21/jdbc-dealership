package com.pluralsight;

import org.apache.commons.dbcp2.BasicDataSource;

public class DatabaseConnector {

    public static BasicDataSource setUpConnection(){
        // data source to generate connections instead of drive manager
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/carDealership");
        dataSource.setUsername("root");
        dataSource.setPassword("password");
        return dataSource;
    }

}
