package com.company;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
public class Main {


    public void printHeader() {
        System.out.println("----------------------------");
        System.out.println("|   Welcome to Our Ship    |");
        System.out.println("|        Selection         |");
        System.out.println("----------------------------");
        System.out.println(" ");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int cabinNum = 0; // Cabin number
        Cabin[] cabins = new Cabin[13]; // Main Array
        //for (int x = 0; x < 6; x++ ) cabins[x] = "";
        //initialise
        Main menu = new Main();
        menu.printHeader();

        initialise(cabins); //better to initialise in a procedure
        while (cabinNum < 13) { // Start while loop
            System.out.println("----- Enter 'A' for Add customers to a cabins : ");
            System.out.println("----- Enter 'V' for View all cabins : ");
            System.out.println("----- Enter 'E' for Display empty cabins : ");
            System.out.println("----- Enter 'D' for Delete from cabins : ");
            System.out.println("----- Enter 'F' for Find cabins from customer name : ");
            System.out.println("----- Enter 'S' for enter program data to file : ");
            System.out.println("----- Enter 'L' for Load program data from the file : ");
            System.out.println("----- Enter 'O' for View passengers Ordered alphabatically : ");
            System.out.println("----- Enter 'T' for view total expenses : ");
            System.out.println("----- Enter '0' for Exit the program : ");
            String user_input = input.next();
            if (user_input.equals("A") || user_input.equals("a")) {
                addToCabin(cabins);
            } else if (user_input.equals("V") || user_input.equals("v")) {
                viewCabinMethod(cabins);
            } else if (user_input.equals("E") || user_input.equals("e")) {
                emptyCabinMethod(cabins);
            } else if (user_input.equals("D") || user_input.equals("d")) {
                deleteFromCabinMethod(cabins);
            } else if (user_input.equals("F") || user_input.equals("f")) {
                findCabin(cabins);
            } else if (user_input.equals("S") || user_input.equals("s")) {
                storeIntoFile(cabins);
            } else if (user_input.equals("L") || user_input.equals("l")) {
                readFromFile(cabins);
            } else if (user_input.equals("O") || user_input.equals("o")) {
                orderSort(cabins);
            } else if (user_input.equals("T") || user_input.equals("t")){
                totalExpenses(cabins);
            } else if (user_input.equals("0")) {
                break;
            } else {
                System.out.println("Wrong input !");
            }
        }
    }

    private static void initialise(Cabin[] cabinRef) { // Set "e for all cabins"
        for (int x = 0; x < cabinRef.length; x++) {
            Cabin cabin = new Cabin(3);
            cabinRef[x] = cabin;
        }
    }

    public static void viewCabinMethod(Cabin[] cabinRef) { // view all cabins methods
        System.out.println(" ");
        System.out.println("All the cabins ");
        for (int x = 1; x < cabinRef.length; x++) { // cabin owner will be displayed
            if (cabinRef[x].passengersCount == 0) {
                System.out.println("Cabin " + x + " is Empty ");
            } else {
                System.out.println("Cabin " + x + " occupied by " + cabinRef[x].passengersCount + " Passengers");
            }
        }
        System.out.println("");
    }

    public static int addToCabin(Cabin[] cabinRef) { // Add to cabin method
        Scanner input = new Scanner(System.in);
        while (true) { // to add customers over and over again
            System.out.println("Enter cabin number (1-12) or 0 to stop :");
            int cabinNum = input.nextInt();
            if (cabinNum > 12) { // Checking the input number
                System.out.println("Invalid cabin number!");
                continue;
            } else if (cabinNum == 0) {
                break;
            }
            System.out.println("Enter passengers details for: " + cabinNum);
            System.out.println("First name : ");
            String firstName = input.next();
            System.out.println("Surname : ");
            String surName = input.next();
            System.out.println("Expenses : ");
            int expense = input.nextInt();

            Passenger passenger = new Passenger(firstName, surName, expense);
            if (cabinRef[cabinNum].addPassenger(passenger)) {
                System.out.println(passenger.getName() + " Successfully added to cabin number " + cabinNum);
            }
        }
        return 0;
    }

    public static void emptyCabinMethod(Cabin[] cabinRef) { // To show empty cabins
        System.out.println("Empty cabins ");
        for (int x = 1; x < cabinRef.length; x++) {
            if (cabinRef[x].passengersCount == 0) { // Except the taken cabins other cabins will display as empty
                System.out.println("cabin " + x + " is empty");
            }
        }
        System.out.println(" ");
    }

    public static void deleteFromCabinMethod(Cabin[] cabinRef) { // delete values from array
        Scanner input = new Scanner(System.in);
        for (int x = 1; x < cabinRef.length; x++) {
            System.out.println("Enter cabin number to delete customer or 0 to exit! :");
            int cabin = input.nextInt();
            if (cabin == 0) {
                break;
            } else if (cabin < 13) {
                System.out.println("Which passenger you need to delete from cabin? : ");
                cabinRef[cabin].displayPassengers();
                System.out.println("Enter number : ");
                int seat = input.nextInt();

                if (cabinRef[cabin].deletePassenger(seat)){
                    System.out.println("Successfully deleted ");
                }
            } else {
                System.out.println("Wrong input!");
            }
        }
    }

    public static void findCabin(Cabin[] cabinRef) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter passenger details to find the cabin ");
        System.out.println("Enter firstname : ");
        String firstname = input.next();
        System.out.println("Enter surname : ");
        String lastname = input.next();
        for (int x = 1; x < cabinRef.length; x++)
            if (cabinRef[x].isPassengerExists(firstname, lastname)) {
                System.out.println(firstname + " " + lastname + " is in cabin " + x);
                return;
            }
        System.out.println(firstname + " " + lastname + " is not in any cabin ");
    }

    public static void storeIntoFile(Cabin[] cabinRef) {
        File file = new File("cabinData.txt");//
        file.delete();//Delete existing file

        try {

            FileWriter myWriter = new FileWriter("cabinData.txt", true);
            for (int x = 1; x < cabinRef.length; x++) {
                if (cabinRef[x].passengersCount == 0) {
                    myWriter.write("e\n");
                } else {
                    myWriter.write(cabinRef[x].getStringToStore() + "\n");
                }
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void readFromFile(Cabin[] cabinRef) {
        try {
            File file = new File("cabinData.txt");
            Scanner myReader = new Scanner(file);
            int count = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if (data.equals("e")) {
                    Cabin cabin = new Cabin(3);
                    cabinRef[count] = cabin;
                } else {
                    Cabin cabin = new Cabin(3);
                    String[] str = data.split("[,]", 0);
                    for (String userString : str) {
                        String fName = userString.split("[.]", 0)[0];
                        String lName = userString.split("[.]", 0)[1];
                        int expense = Integer.parseInt(userString.split("[.]", 0)[2]);
                        Passenger passenger = new Passenger(fName, lName, expense);
                        cabin.addPassenger(passenger);
                        System.out.println(passenger.getName() + " is added to cabin " + count);
                    }
                    cabinRef[count] = cabin;
                }
                count++;
            }
            myReader.close();
            System.out.println("Data has loaded from file successfully");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

        public static void orderSort(Cabin[] cabinRef) {
            ArrayList<String> sortingArray = new ArrayList<String>();
            for (int a = 0; a < cabinRef.length; a++) {
                for (int p = 0; p < cabinRef[a].passengersInCabin.length; p++) {
                    if (cabinRef[a].passengersInCabin[p] != null) {
                        sortingArray.add(cabinRef[a].passengersInCabin[p].getName());
                    }
                }
            }
            String temp;
            System.out.println("Passengers names according to alphabetical order...");
            for (int j = 0; j < sortingArray.size(); j++) {
                for (int i = j + 1; i < sortingArray.size(); i++) {

                    if (sortingArray.get(i).compareTo(sortingArray.get(j)) < 0) {
                        temp = sortingArray.get(j);
                        sortingArray.add(j, sortingArray.get(i));
                        sortingArray.add(i, temp);
                    }
                }
                System.out.println(sortingArray.get(j));

            }
        }



    public static void totalExpenses(Cabin[] cabinRef) {
        System.out.println("Total expenses ");
        int total = 0;
        for (int x=0;x< cabinRef.length;x++){
            if(cabinRef[x].passengersCount != 0){
                total += cabinRef[x].getPassengerExpenses();
                cabinRef[x].displayPassengerExpenses();
            }
        }
        System.out.println("Total expenses is : "+total);
    }
}