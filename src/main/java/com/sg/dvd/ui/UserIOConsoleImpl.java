package com.sg.dvd.ui;
import java.util.ArrayList;
import java.util.Scanner;

/*  Part of View
   -View->Interacts with user
   -Impl naming convention-> Named so because we are implementing the DVDDao interface
 */

public class UserIOConsoleImpl implements UserIO {

    // constructor
    public UserIOConsoleImpl(){

    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {
        System.out.println(prompt);
        Scanner scn= new Scanner(System.in);
        String ret= scn.nextLine();
        return ret;
    }

    @Override
    public int readInt(String prompt) {
        System.out.println(prompt);
        Scanner scn= new Scanner(System.in);
        String ret= scn.nextLine();
        int retInt= Integer.parseInt(ret);
        return retInt;
    }

    //method overload
    @Override
    public int readInt(String prompt, int min, int max) {
        int retInt;
        do{
            System.out.println(prompt);
            Scanner scn= new Scanner(System.in);
            String ret= scn.nextLine();
            retInt= Integer.parseInt(ret);

        }while(retInt<min || retInt>max);
        return retInt;
    }

    @Override
    public double readDouble(String prompt) {
        System.out.println(prompt);
        Scanner scn= new Scanner(System.in);
        String ret= scn.nextLine();
        double retDouble= Double.parseDouble(ret);
        return retDouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double retDouble;
        do{
            System.out.println(prompt);
            Scanner scn= new Scanner(System.in);
            String ret= scn.nextLine();
            retDouble = Double.parseDouble(ret);

        }while(retDouble <min || retDouble >max);
        return retDouble;
    }

    @Override
    public float readFloat(String prompt) {
        System.out.println(prompt);
        Scanner scn= new Scanner(System.in);
        String ret= scn.nextLine();
        float retFloat = Float.parseFloat(ret);
        return retFloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float retFloat;
        do{
            System.out.println(prompt);
            Scanner scn= new Scanner(System.in);
            String ret= scn.nextLine();
            retFloat = Float.parseFloat(ret);

        }while(retFloat <min || retFloat >max);
        return retFloat;
    }

    @Override
    public long readLong(String prompt) {
        System.out.println(prompt);
        Scanner scn= new Scanner(System.in);
        String ret= scn.nextLine();
        long retLong = Long.parseLong(ret);
        return retLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long retLong;
        do{
            System.out.println(prompt);
            Scanner scn= new Scanner(System.in);
            String ret= scn.nextLine();
            retLong = Long.parseLong(ret);

        }while(retLong <min || retLong >max);
        return retLong;
    }

    public ArrayList<Integer> readArrayList(String prompt){
        System.out.println(prompt);
        Scanner scn= new Scanner(System.in);
        ArrayList<Integer> forReturn= new ArrayList<>();
        int num=5;
        do {
            String ret = scn.nextLine();
            int retInt = Integer.parseInt(ret);
            forReturn.add(retInt);
            num--;
        }while(num>0);
        return forReturn;

    }
}
