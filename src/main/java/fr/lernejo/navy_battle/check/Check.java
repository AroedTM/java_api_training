package fr.lernejo.navy_battle.check;

import java.util.Objects;

public class Check {

    public boolean isStringInt(String string){
        boolean is_Int;
        try{
            Integer.parseInt(string);
            is_Int = true;
        } catch(NumberFormatException e){
            is_Int = false;
        }
        return is_Int;
    }

    public String convertArgPort(String string){
        final String[] myport = new StringBuilder(string).reverse().toString().split("");
        final StringBuilder theport = new StringBuilder();
        int i = 0;
        while(!Objects.equals(myport[i], ":") && !Objects.equals(myport[i], "\0"))
        {
            theport.append(myport[i]);
            i++;
        }
        return theport.reverse().toString();
    }

    public void displayHelp(){
        System.out.println("Pour lancer le programme :");
        System.out.println("1. Arg: <port>");
        System.out.println("2. Args: <port> <url:port>");
    }
}
