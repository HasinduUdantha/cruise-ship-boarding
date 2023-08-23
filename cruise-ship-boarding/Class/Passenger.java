package com.company;

public class Passenger {

    String firstName;
    String surname;
    int expenses;

    public Passenger(String fName,String lName,int cost){

        firstName = fName;
        surname = lName;
        expenses = cost;
    }
    public String getName(){
        return firstName+" "+surname;
    }

    public void displayName(){

        System.out.println(firstName+" "+surname+" "+ expenses);
    }
}