package org.example.models;

public class Action {

    public String type;
    public String source;
    public String destination;
    public String category;
    public String propertyName;
    public String propertyValue;

    public String vaultUrl;
    public String vaultToken;
    public String secretPath;


    public Action() {
    }

    public Action(String type, String source, String destination, String category, String propertyName, String propertyValue, String vaultUrl, String vaultToken, String secretPath) {
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.category = category;
        this.propertyName = propertyName;
        this.propertyValue = propertyValue;
        this.vaultUrl = vaultUrl;
        this.vaultToken = vaultToken;
        this.secretPath = secretPath;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public void setPropertyValue(String propertyValue) {
        this.propertyValue = propertyValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public String getPropertyValue() {
        return propertyValue;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getVaultUrl() {
        return vaultUrl;
    }

    public void setVaultUrl(String vaultUrl) {
        this.vaultUrl = vaultUrl;
    }

    public String getVaultToken() {
        return vaultToken;
    }

    public void setVaultToken(String vaultToken) {
        this.vaultToken = vaultToken;
    }

    public String getSecretPath() {
        return secretPath;
    }

    public void setSecretPath(String secretPath) {
        this.secretPath = secretPath;
    }
}
