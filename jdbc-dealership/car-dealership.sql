-- DROP to start with a new DB
DROP DATABASE carDealership;

-- CREATE NEW TABLE
CREATE DATABASE carDealership;

-- SELECT DB TO USE
USE carDealership;

-- CREATE dealership table
CREATE TABLE Dealerships (
Dealership_id int AUTO_INCREMENT,
DealershipName varchar(50),
Address varchar(50),
Phone varchar(20),
PRIMARY KEY(Dealership_id)
);

-- CREATE vehicles table
CREATE TABLE Vehicles (
Vin int,
VehicleYear int,
Make varchar(50),
Model varchar(50),
VehicleType varchar(50),
Color varchar(25),
Odometer int,
Price double,
Sold boolean,
PRIMARY KEY(Vin)
);

-- CREATE inventory table

CREATE TABLE Inventory(
Dealership_id int,
Vin int,
PRIMARY KEY(Dealership_id,VIN),
FOREIGN KEY(Vin) REFERENCES Vehicles (Vin),
FOREIGN KEY(Dealership_id) REFERENCES Dealerships (Dealership_id)
);

-- CREATE sales contract table
CREATE TABLE SalesContracts(
SaleId int AUTO_INCREMENT,
Vin int UNIQUE,
PRIMARY KEY(SaleId),
FOREIGN KEY(Vin) REFERENCES Vehicles (Vin)
);

-- CREATE lease contracts table
CREATE TABLE LeaseContracts(
LeaseId int AUTO_INCREMENT,
Vin int UNIQUE,
PRIMARY KEY(LeaseId),
FOREIGN KEY(Vin) REFERENCES Vehicles (Vin)
);

-- INSERT INTO TABLES VALUES
INSERT INTO Dealerships (DealershipName, Address, Phone) VALUES
('AutoNation Toyota', '123 Main St, Anytown, TX 12345', '123-456-7890'),
('Big City Honda', '456 Elm St, Big City, NY 67890', '234-567-8901'),
('Luxury Cars of LA', '789 Sunset Blvd, Los Angeles, CA 90001', '345-678-9012'),
('Midwest Motors', '1011 Oak St, Springfield, IL 62704', '456-789-0123'),
('Sunshine State Ford', '1213 Palm Dr, Miami, FL 33101', '567-890-1234');

INSERT INTO Vehicles (Vin, VehicleYear, Make, Model, VehicleType, Color, Odometer, Price, Sold) VALUES
(1001, 2020, 'Toyota', 'Camry', 'Sedan', 'Blue', 15000, 22000.00, FALSE),
(1002, 2019, 'Honda', 'Civic', 'Sedan', 'Black', 30000, 18000.00, TRUE),
(1003, 2021, 'Ford', 'F-150', 'Truck', 'Red', 10000, 35000.00, FALSE),
(1004, 2018, 'Chevrolet', 'Equinox', 'SUV', 'White', 45000, 16000.00, TRUE),
(1005, 2022, 'Tesla', 'Model 3', 'Sedan', 'Silver', 5000, 45000.00, FALSE),
(1006, 2020, 'BMW', 'X5', 'SUV', 'Blue', 20000, 55000.00, TRUE),
(1007, 2019, 'Audi', 'A4', 'Sedan', 'Gray', 25000, 30000.00, FALSE),
(1008, 2021, 'Mercedes-Benz', 'C-Class', 'Sedan', 'Black', 12000, 42000.00, TRUE),
(1009, 2020, 'Hyundai', 'Santa Fe', 'SUV', 'Red', 18000, 27000.00, FALSE),
(1010, 2019, 'Kia', 'Sorento', 'SUV', 'Green', 35000, 23000.00, TRUE);

INSERT INTO Inventory (Dealership_id,Vin)
VALUES
(1, 1001),
(1, 1002),
(2, 1003),
(2, 1004),
(3, 1005),
(3, 1006),
(4, 1007),
(4, 1008),
(5, 1009),
(5, 1010);

INSERT INTO SalesContracts (Vin)
Values
(1002),
(1004),
(1006),
(1008),
(1010);

INSERT INTO LeaseContracts (Vin)
VALUES
(1001),
(1003),
(1005);