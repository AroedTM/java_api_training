package fr.lernejo.navy_battle.check;

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
