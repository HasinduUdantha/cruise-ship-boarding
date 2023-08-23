import java.io.FileNotFoundException;
import java.util.Arrays;
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
    String[] cabin = new String[13]; // Main Array
    Main menu = new Main();
    menu.printHeader();

    initialise(cabin); //better to initialise in a procedure
    while (cabinNum < 13 ) { // Start while loop
        System.out.println("----- Enter 'A' for Add customers to a cabin : ");
        System.out.println("----- Enter 'V' for View all cabins : ");
        System.out.println("----- Enter 'E' for Display empty cabins : ");
        System.out.println("----- Enter 'D' for Delete from cabins : ");
        System.out.println("----- Enter 'F' for Find cabin from customer name : ");
        System.out.println("----- Enter 'S' for enter program data to file : ");
        System.out.println("----- Enter 'L' for Load program data from the file : ");
        System.out.println("----- Enter 'O' for View passengers Ordered alphabetically : ");
        System.out.println("----- Enter '0' for Exit the program : ");
        String user_input = input.next();
        if (user_input.equals("A") || user_input.equals("a")) {
            addToCabin(cabin);
        }
        else if (user_input.equals("V") || user_input.equals("v")){
            viewCabinMethod(cabin);
        }
        else  if (user_input.equals("E") || user_input.equals("e")){
            emptyCabinMethod(cabin);
        }
        else  if (user_input.equals("D") || user_input.equals("d")){
            deleteFromCabinMethod(cabin);
        }
        else  if (user_input.equals("F") || user_input.equals("f")){
            findCabin(cabin);
        }
        else  if (user_input.equals("S") || user_input.equals("s")){
            storeIntoFile(cabin);
        }
        else  if (user_input.equals("L") || user_input.equals("l")){
            readFromFile(cabin);
        }
        else if (user_input.equals("O") || user_input.equals("o")){
            orderSort(cabin);
        }
        else if (user_input.equals("0")){
            break;
        }
        else{
            System.out.println("Wrong input !");
        }
    }
}

    private static void initialise( String[] cabinRef) { // Set "e for all cabins"
        for (int x = 0; x < cabinRef.length; x++ )
            cabinRef[x] = "e";
    }

    public static void viewCabinMethod(String[] cabinRef){ // view all cabins methods
        System.out.println(" ");

        for (int x = 1; x < cabinRef.length; x++ ) { // cabin owner will be displayed

            if(cabinRef[x].equals("e")){
                System.out.println("room " + x + " is Empty ");
            }
            else{
                System.out.println("room " + x + " occupied by " + cabinRef[x]);
            }
        }
        System.out.println("");
    }

    public static int addToCabin(String[] cabinRef){ // Add to cabin method
        Scanner input = new Scanner(System.in);
        while (true) { // to add customers over and over again
            System.out.println("Enter room number (1-12) or 0 to stop :");
            int cabinNum = input.nextInt();
            if (cabinNum > 13) { // Checking the input number
                System.out.println("Invalid room Number!");
                continue;
            }
            else  if (cabinNum == 0){
                break;
            }
            System.out.println("Enter name for room " + cabinNum + " :");
            String cabinName = input.next();
            cabinRef[cabinNum] = cabinName; // Add name to the cabin number
            System.out.println(cabinName + " Successfully added to cabin number " + cabinNum);
        }
        return 0;
    }

    public  static  void emptyCabinMethod(String[] cabinRef){ // To show empty cabins

        for (int x = 1; x < cabinRef.length; x++ ) {
            if (cabinRef[x].equals("e")) { // Except the taken cabins other cabins will display as empty
                System.out.println("room " + x + " is empty");
            }
        }
        System.out.println(" ");
    }

    public static void deleteFromCabinMethod(String[] cabinRef){ // delete values from array
        Scanner input = new Scanner(System.in);
        for (int x = 1; x < cabinRef.length; x++ ) {
            System.out.println("Enter cabin number to delete customer or 0 to exit! :");
            int delete_input = input.nextInt();
            if (delete_input == 0){
                break;
            }
            else if (delete_input < 13) {
                cabinRef[delete_input] = "e"; // This will replace value as an empty String
                System.out.println(delete_input + " Successfully deleted! ");
                System.out.println(" ");
            }
            else {
                System.out.println("Wrong input!");
            }
        }
    }

    public static void findCabin(String[] cabinRef) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter customer name to find the cabin :");
        String name_input = input.next();
        System.out.println(name_input);
        for (int x = 1; x < cabinRef.length; x++)
            if (name_input.equals(cabinRef[x])) {
                System.out.println(cabinRef[x]+ " is in cabin "+x);
                return;
            }
        System.out.println(name_input+" is not in any cabin ");
    }
    public static void storeIntoFile(String[] cabinRef){

        File file = new File("cabinData.txt");//
        file.delete();//Delete existing file

        try {

            FileWriter myWriter = new FileWriter("cabinData.txt",true);
            for(int x = 1; x < cabinRef.length; x++ ){

                myWriter.write(cabinRef[x]+"\n");
              /*  if(cabinRef[x].equals("e")){
                    myWriter.write("room " + x + " is Empty \n");
                }
                else{
                    myWriter.write("room " + x + " occupied by " + cabinRef[x]+"\n");
                }*/
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public  static void readFromFile(String[] cabinRef) {

        try {
            File file = new File("cabinData.txt");
            Scanner myReader = new Scanner(file);
            int count = 1;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                cabinRef[count] = data;
                count++;
            }
            myReader.close();
            System.out.println("Data has loaded from file successfully");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }
    public static void orderSort(String[] cabinRef){
        String[] sortingArray = new String[cabinRef.length];
        for (int a=0;a< cabinRef.length;a++){
            sortingArray[a]=cabinRef[a];
        }
        String temp;
        System.out.println("Passengers names according to alphabetical order...");
        for (int j = 0; j < sortingArray.length; j++) {
            for (int i = j + 1; i < sortingArray.length; i++) {
                // comparing strings
                if (sortingArray[i].compareTo(sortingArray[j]) < 0) {
                    temp = sortingArray[j];
                    sortingArray[j] = sortingArray[i];
                    sortingArray[i] = temp;
                }
            }
            if(!sortingArray[j].equals("e")){
                System.out.println(sortingArray[j]);
            }
        }
    }
}

