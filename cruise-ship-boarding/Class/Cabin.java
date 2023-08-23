package com.company;

public class Cabin {

    int passengersCount;
    private int cabinExpenses;
    Passenger passengersInCabin[] ;

    public Cabin(int size){

    passengersCount = 0;
    cabinExpenses = 0;
    passengersInCabin = new Passenger[size];

    }
    public void displayPassengerCount(){

        System.out.println("Passenger count is: "+passengersCount);

    }
    public boolean addPassenger(Passenger p){

        if (passengersCount == passengersInCabin.length){

            System.out.println("Cabin is full!!! ");
            return false;
        }
        else{

            passengersInCabin[passengersCount] = p;
            passengersCount++;
            cabinExpenses += p.expenses;
            return true;
        }
    }
    public void displayPassengers(){

        for(int x = 0; x<passengersInCabin.length ; x++){
            if(passengersInCabin[x] != null){

                System.out.println(x+" : " + passengersInCabin[x].getName());
            }
        }
    }

    public boolean deletePassenger(int seat){

        if(passengersInCabin[seat] != null){
            cabinExpenses -= passengersInCabin[seat].expenses;
            passengersInCabin[seat] = null;
            passengersCount--;
            reSeatPassengers();
            return true;
        }
        else{
            System.out.println("Passenger not found!! ");
            return false;
        }
    }

    public boolean isPassengerExists(String fName, String lName){

        for (int x = 0; x < passengersInCabin.length; x++ ){

            if(passengersInCabin[x] != null && passengersInCabin[x].firstName.equals(fName) && passengersInCabin[x].surname.equals(lName)){

                return true;
            }
        }
        return false;
    }
    public String getStringToStore(){

        String output = "";
        for (int x = 0; x < passengersInCabin.length; x++ ) {

            if (passengersInCabin[x] != null) {
            output +=  passengersInCabin[x].firstName +"."+ passengersInCabin[x].surname+ "."+ passengersInCabin[x].expenses + ",";

            }
        }
        return output;
    }

    public int getPassengerExpenses(){

        return cabinExpenses;
    }
    public void displayPassengerExpenses(){
        for (int x = 0; x < passengersInCabin.length; x++ ) {

            if (passengersInCabin[x] != null) {
                System.out.println("Passenger name : "+ passengersInCabin[x].firstName + " " + passengersInCabin[x].surname +  " , expense is " + passengersInCabin[x].expenses + " ");
            }
        }
    }
    public void reSeatPassengers() {
        int count = 0;
        for (int x = 0; x < passengersInCabin.length; x++) {
            if (passengersInCabin[x] != null) {
                passengersInCabin[count] = passengersInCabin[x];
                if (count != x) {
                    passengersInCabin[x] = null;
                }
                count++;
            }
        }
    }
}




