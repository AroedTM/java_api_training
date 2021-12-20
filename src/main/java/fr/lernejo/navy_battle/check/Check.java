package fr.lernejo.navy_battle.check;

import fr.lernejo.navy_battle.game.Game;
import net.jimblackler.jsonschemafriend.Schema;
import net.jimblackler.jsonschemafriend.SchemaException;
import net.jimblackler.jsonschemafriend.SchemaStore;
import net.jimblackler.jsonschemafriend.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public boolean dest(Game game, String message){
        Pattern pattern = Pattern.compile("(http://localhost:[0-9]*)");
        Matcher matcher = pattern.matcher(message);
        if (matcher.find())
        {
            game.destination.add(matcher.group(1));
            return true;
        }
        return false;
    }
}
