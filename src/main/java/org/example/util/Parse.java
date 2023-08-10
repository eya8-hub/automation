package org.example.util;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.example.models.Action;
import org.example.models.Step;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class Parse {
    public static ArrayList<Step> getJsonData(String path) throws FileNotFoundException, ParseException {
        ArrayList<Step> steps = new ArrayList<>();
        JSONArray jsonArray = (JSONArray) new JSONParser().parse(new FileReader(path));
        for (Object element : jsonArray) {
            JSONObject el = (JSONObject) element;
            JSONObject act = (JSONObject) el.get("action");
            Action action = new Action((String) act.get("type"), (String) act.get("source"), (String) act.get("destination"),(String) act.get("category"),(String) act.get("propertyName"),(String) act.get("propertyValue"),(String) act.get("vaultUrl"),(String) act.get("vaultToken"),(String) act.get("secretPath"));
            Step step = new Step((int) el.get("step"), action);
            steps.add(step);
        }
        return steps;
    }
}
