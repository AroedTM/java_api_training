package fr.lernejo.navy_battle.check;

import com.sun.net.httpserver.HttpExchange;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;

import java.io.IOException;
import java.util.Objects;

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
        System.out.println("1. Arg: <port>");
        System.out.println("2. Args: <port> <url:port>");
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
        final String[] theUrl = new StringBuilder(url).reverse().toString().split("");
        final StringBuilder theCell = new StringBuilder();
        int i = 0;
        while(!Objects.equals(theUrl[i], "="))
        {
            theCell.append(theUrl[i]);
            i++;
        }
        return theCell.reverse().toString();
    }
}
