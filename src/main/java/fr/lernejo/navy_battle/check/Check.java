package fr.lernejo.navy_battle.check;

import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;

public class Check {

    public boolean isStringInt(String string){
        try{
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    public boolean validateJson(String jsonToCompare, String jsonMaster) {
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
