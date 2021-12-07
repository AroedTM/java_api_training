package fr.lernejo.navy_battle;

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

}
