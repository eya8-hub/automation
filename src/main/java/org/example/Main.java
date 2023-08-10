package org.example;

import net.minidev.json.parser.ParseException;
import org.example.models.Step;
import org.example.service.InstallService;
import org.example.service.impl.InstallServiceImpl;
import org.example.util.Parse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        InstallService installService = new InstallServiceImpl();
        String path = "automation/src/main/java/org/example/steps.json";
        ArrayList<Step> steps = Parse.getJsonData(path);
        Iterator<Step> iterator = steps.iterator();
        while (iterator.hasNext()) {
            Step step = iterator.next();
            switch (step.action.type) {
                case "copy_to_folder":

                    installService.copy_to_folder(step.action.source, step.action.destination, step.step);
                    break;

                case "copy_file_to_folder":
                    installService.copy_file_to_folder(step.action.source, step.action.destination, step.step);

                    break;

                case "set_property_configuration":
                    installService.set_property_configuration(step.action.category, step.action.propertyName, step.action.propertyValue, step.step);
                    break;


                case "tache_manuelle":
                    installService.tache_manuelle(step.step);
                    break;
                case "getVaultSecret":
                    //installService.getVaultSecret(step.action.vaultUrl, step.action.vaultToken, step.action.secretPath, step.action.propertyValue);
                    break;
                case "updateConfigValue":
                    installService.updateConfigValue(step.action.source, step.action.propertyName, step.action.propertyValue);
                    break;


                default:
                    System.out.println("operation n'existe pas");
                    break;
            }
        }

    }
}