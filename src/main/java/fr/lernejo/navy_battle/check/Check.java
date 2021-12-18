package fr.lernejo.navy_battle.check;

import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;

import java.io.IOException;

public class Check {

    public boolean isStringInt(String string){
        try{
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public void displayHelp(){
        System.out.println("Pour lancer le programme :");
        System.out.println("Joueur 1. Arg: <port>");
        System.out.println("Joueur 2. Args: <port> <url:port>");
    }

    public boolean validateJson(String jsonToCompare, String jsonMaster) throws IOException {
        try {
            final SchemaStore schemaStore = new SchemaStore();
            final Schema schema = schemaStore.loadSchemaJson(jsonMaster);
            final Validator validator = new Validator();
            validator.validateJson(schema, jsonToCompare);
            return true;
        } catch (SchemaException e) {
            return false;
        }
    }

    public String getCell(String url){
        return url.split("=")[1];
    }

    public int getIntFromString(String string){
        return string.charAt(0) % 65;
    }
}
