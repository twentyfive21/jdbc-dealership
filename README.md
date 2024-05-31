
<p align="center">
  <img src="https://github.com/twentyfive21/car-dealership/assets/107441301/c94a5a62-eed2-437a-b95e-42babd8c5e8c" height="250">
</p>

<h1 align="center"> JDBC Java Car Dealership Application Workshop </h1> 
<h1 align="center"> Welcome to the JDBC Application repository! </h1>

### [View Part 1 Here](https://github.com/twentyfive21/car-dealership)
### [View Part 2 Here](https://github.com/twentyfive21/adv-dealership)

## Welcome to the Advanced Java Car Dealership project! This is designed to optimize the management of a car dealership using a Java-based application. With the recent integration of MySQL, the application now provides improved data management capabilities and scalability.

## Key Features:
- **Efficiency:** The intuitive interface ensures that navigating through the program is straightforward and enjoyable.
- **Functionality:** Users can effortlessly add, view, delete, and search through the inventory.
- **Data Management:** Enhanced data management capabilities with MySQL integration for robust and scalable performance.
- **User Access Control:** Secure login and role-based access to ensure data integrity and confidentiality.
- **Reporting:** Generate comprehensive reports on sales, inventory, and customer data for better decision-making.
  
---

# Folder Structure : 
![Screenshot 2024-05-30 at 11 02 45 PM](https://github.com/twentyfive21/jdbc-dealership/assets/107441301/ea7d0ee4-4f38-474d-9c43-5de759793349)


# Homescreen : This is where the main class is and the start of the application
![Screenshot 2024-05-30 at 11 03 12 PM](https://github.com/twentyfive21/jdbc-dealership/assets/107441301/4c91d803-4336-422c-8943-4960361182c9)


# Interesting piece of code :
![Screenshot 2024-05-30 at 11 04 55 PM](https://github.com/twentyfive21/jdbc-dealership/assets/107441301/b1a871c6-4513-42eb-b0fb-bef4c57059f4)

### I found this piece of code particularly interesting due to its efficient retrieval of unsold vehicles from a database using JDBC. The use of try-with-resources ensures proper management of database resources. The PreparedStatement and ResultSet objects effectively execute and process the query. The do/while loop iterates through the result set, mapping database columns to a Vehicle object. This method demonstrates good practices in database interaction and object-oriented programming. Additionally, the error handling mechanism provides robustness by alerting if no results are found.

# Database Tables and Vehicles table : 
<img width="1043" alt="Screenshot 2024-05-30 at 11 05 47 PM" src="https://github.com/twentyfive21/jdbc-dealership/assets/107441301/e02a9600-fb7a-4a01-bbcf-17d64a51cc11">

# Inventory table : 
<img width="1043" alt="Screenshot 2024-05-30 at 11 08 48 PM" src="https://github.com/twentyfive21/jdbc-dealership/assets/107441301/0713b8cc-9018-451e-a3e2-dc57feaaa3b2">

# SQL Code to create tables : 
![Screenshot 2024-05-30 at 11 09 45 PM](https://github.com/twentyfive21/jdbc-dealership/assets/107441301/d1bcb55e-be10-4c64-83b7-80da48c185b9)
<img width="1043" alt="Screenshot 2024-05-30 at 11 10 21 PM" src="https://github.com/twentyfive21/jdbc-dealership/assets/107441301/428efbc6-fd5d-4b08-b2d9-634862c3c344">

# SQL Code to Insert into vehicle/dealership tables : 
<img width="1043" alt="Screenshot 2024-05-30 at 11 10 34 PM" src="https://github.com/twentyfive21/jdbc-dealership/assets/107441301/50c8400b-3ddb-426d-98ba-287c6d81d46c">

# SQL Code to Insert into lease/sale tables : 
<img width="1043" alt="Screenshot 2024-05-30 at 11 11 05 PM" src="https://github.com/twentyfive21/jdbc-dealership/assets/107441301/b2e19f5a-6174-40e7-ba6b-9f01f6969290">
