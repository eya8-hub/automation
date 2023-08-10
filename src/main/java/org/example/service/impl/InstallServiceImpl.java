package org.example.service.impl;

import org.example.service.InstallService;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.util.Properties;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;


public class InstallServiceImpl implements InstallService {

    public void copy_to_folder(String src, String dest, int stepNum) throws IOException {
        Path sourcePath = Paths.get(src);
        Path destinationPath = Paths.get(dest);
        // Copy the files to the destination directory, keeping existing files
        Files.walk(sourcePath).forEach(source -> {
            try {

                Path destination = destinationPath.resolve(sourcePath.relativize(source));
                if (Files.exists(destination) && Files.isDirectory(destination)) {
                    return; // Skip if the destination is an existing directory
                }
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);


            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        System.out.println("The copy related to step " + stepNum + " has been completed successfully.");
    }

    public void copy_file_to_folder(String src, String dest, int stepNum) throws IOException {
        Path sourcePath = Paths.get(src);
        Path destinationPath = Paths.get(dest);

        // Create the destination directory if it doesn't exist
        if (!Files.exists(destinationPath)) {
            try {
                Files.createDirectories(destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Copy the source file to the destination folder
        Path destination = destinationPath.resolve(sourcePath.getFileName());
        Files.copy(sourcePath, destination, StandardCopyOption.REPLACE_EXISTING);

        System.out.println("The file related to step " + stepNum + " has been copied to the destination successfully.");
    }

    public void set_property_configuration(String category, String propertyName, String propertyValue, int stepNum) {

        try {

            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/deltaemr", "deltaservices", "deltaservices");

            String query = "INSERT INTO configuration (category, propertyName, propertyValue) VALUES (?, ?, ?) " + "ON DUPLICATE KEY UPDATE propertyValue = ?";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, category);
            statement.setString(2, propertyName);
            statement.setString(3, propertyValue);
            statement.setString(4, propertyValue);

            // Execute the SQL query
            int rowsAffected = statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            connection.close();

            System.out.println("the copy from table related to step " + stepNum + ": " + rowsAffected + " row(s) affected. Key copied to the configuration table.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tache_manuelle(int stepNum) {

        System.out.println("this task releated to step " + stepNum + " is Auto");
    }

    public String getVaultSecret(String vaultUrl, String vaultToken, String secretPath, String propertyValue) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(vaultUrl + "/v1/" + secretPath);
        httpGet.addHeader("X-Vault-Token", vaultToken);

        HttpResponse response = httpClient.execute(httpGet);

        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder responseBody = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            responseBody.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody.toString());

        return jsonNode.get("data").get(propertyValue).asText();
    }


    public  void updateConfigValue(String source, String propertyName, String propertyValue) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(source);
        try {
            // Load the properties from the file
            properties.load(fileInputStream);

            // Close the file input stream
            fileInputStream.close();

            // Update the value for the specified key
            properties.setProperty(propertyName, propertyValue);

            FileOutputStream fileOutputStream = new FileOutputStream(source);

            // Save the updated properties to the file
            properties.store(fileOutputStream, null);
            System.out.println("Value for key " + propertyName + " updated successfully to :"+source);

            // Close the file output stream
            fileOutputStream.close();
        } catch (IOException e){
            System.err.println("Error updating the configuration file: " + e.getMessage());
        }
    }





}






