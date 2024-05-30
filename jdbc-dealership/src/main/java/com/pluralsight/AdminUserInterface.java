package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class AdminUserInterface {

    public void display(){
        Scanner scanner = new Scanner(System.in);
        boolean all = false;

        String password = "123";
        System.out.println("~~~~ Welcome to the Admin Screen ~~~~");
        System.out.print("Password: ");
        String attempt = scanner.nextLine().trim();


        if(password.equals(attempt)){
            System.out.println("Please choose a selection");
            System.out.println("(1) List all contracts\n(2) List last 10 contracts");
            System.out.print("Selection: ");
            String choice = scanner.nextLine().trim();
            switch (choice){
                case "1" : all = true;
                    break;
                case "2": all = false;
                    break;
                default: System.out.println("\n****Invalid choice ****");
                        break;
            }

        }

        if(password.equals(attempt)){
            try{
                int lineCount = 0;

                BufferedReader bufferedReader =  new BufferedReader(new FileReader("contract.csv"));

                String input;
                while ((input = bufferedReader.readLine()) != null ){
                    lineCount++;
                }
                bufferedReader.close(); // Close the file after reading for resetting the file pointer

                bufferedReader = new BufferedReader(new FileReader("contract.csv")); // Reopen the file

                // user chose to select all contracts
                if(all){
                    System.out.println("~~~~ All Contracts ~~~~");
                    lineCount = 1;
                    while ((input = bufferedReader.readLine()) != null ){
                        System.out.printf("Line %d : %s\n",lineCount,input);
                        lineCount++;
                    }
                }

                // if user chose to only display last 10 contracts
                if (!all){
                    System.out.println("~~~~ Last 10 Contracts ~~~~");
                    int lastTen = lineCount - 10;
                    int currentCount = 0;
                    while ((input = bufferedReader.readLine()) != null ){
                        if( currentCount >= lastTen ){
                            System.out.printf("Line %d %s\n",lastTen, input);
                            lastTen++;
                        }
                        currentCount++;
                    }
                }
                // close the reader
                bufferedReader.close();
            }catch (Exception e){
                System.out.println("Error logging in ");
            }
        } else {
            System.out.println("\n **** Incorrect password! ****");
        }

    }

}
